package com.aston.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.exceptions.NotFoundException;
import com.aston.models.Assister;
import com.aston.models.Client;
import com.aston.models.Film;
import com.aston.models.Salle;
import com.aston.models.Seance;
import com.aston.repositories.SeanceRepository;
import com.aston.services.AssisterService;
import com.aston.services.ClientService;
import com.aston.services.FilmService;
import com.aston.services.SalleService;
import com.aston.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired	private SeanceRepository seanceRepository;
	@Autowired	private ClientService clientService;
	@Autowired	private SalleService salleService;
	@Autowired	private FilmService filmService;
	@Autowired	private AssisterService assisterService;
	
	@Override
	public Seance save(Seance s) {
		// TODO fonction et exception pour la vérification des champs
		// Check si les champs requis sont null
		if (s.getSalle() == null)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez sélectionner une salle pour la séance");
		if (s.getFilm() == null)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez sélectionner un film pour la séance");
		if (s.getDate() == null || s.getDate().isBefore(LocalDateTime.now()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez sélectionner un horaire valide pour la séance");
	
		// Check si le champ type a bien été rempli
		if (!s.getType().equals("2D")
				&& !s.getType().equals("3D")
				&& !s.getType().equals("IMAX") 
				&& !s.getType().equals("4DX")
			)
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Erreur dans le champ type : le type " + s.getType() + " n'existe pas");

		// Récupère la salle et le film choisis s'ils existent bien
		Salle salleAReserver =  this.salleService.findById(s.getSalle().getId());
		Film filmAProgrammer = this.filmService.findById(s.getFilm().getId());
		
		// Check s'il y a déjà une séance de prévue dans la même plage horaire et dans la même salle
		LocalDateTime min = s.getDate();
		LocalDateTime max = s.getDate().plusMinutes(filmAProgrammer.getDuree());
		List<Seance> seances = this.findSeanceByDateBetween(min, max);
		if (!seances.isEmpty()) {
			for (Seance seance : seances) {
				if (seance.getSalle().getId().equals(salleAReserver.getId()))
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Une autre séance (" + seance.getId() + ") est déjà programmée pour la même plage horaire dans cette salle");
			}
		}

		// Check s'il y a suffisament de places dans cette séance par rapport au nombre de clients
		int nbPlacesRestantes;
		if (s.getId() != null)
			nbPlacesRestantes = this.getPlacesRestantes(s.getId());
		else
			nbPlacesRestantes = salleAReserver.getPlace();
		
		int nbClients = s.getClients().size();
		if (nbPlacesRestantes < nbClients)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Trop de clients par rapport au nombre de places restantes. Il reste " + nbPlacesRestantes + " places");

		return this.seanceRepository.save(s);
	}

	@Override
	public List<Seance> findAll() {
		return this.seanceRepository.findAll();
	}

	@Override
	public Seance findById(String id) {
		Optional<Seance> s = this.seanceRepository.findById(id);
		if (!s.isPresent())
			throw new NotFoundException(id, Seance.class.getSimpleName());
		
		return s.get();
	}

	@Override
	public Seance update(Seance s) {
		// Check si les champs requis sont null
		if (s.getSalle() == null)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez sélectionner une salle pour la séance");
		if (s.getFilm() == null)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez sélectionner un film pour la séance");
		if (s.getDate() == null || s.getDate().isBefore(LocalDateTime.now()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez sélectionner un horaire valide pour la séance");
	
		// Check si le champ type a bien été rempli
		if (!s.getType().equals("2D")
				&& !s.getType().equals("3D")
				&& !s.getType().equals("IMAX") 
				&& !s.getType().equals("4DX")
			)
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Erreur dans le champ type : le type " + s.getType() + " n'existe pas");

		// Récupère la salle et le film choisis s'ils existent bien
		Salle salleAReserver =  this.salleService.findById(s.getSalle().getId());
		this.filmService.findById(s.getFilm().getId());

		// Check s'il y a suffisament de places dans cette séance par rapport au nombre de clients
		int nbPlacesRestantes;
		if (s.getId() != null)
			nbPlacesRestantes = this.getPlacesRestantes(s.getId());
		else
			nbPlacesRestantes = salleAReserver.getPlace();
		
		int nbClients = s.getClients().size();
		if (nbPlacesRestantes < nbClients)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Trop de clients par rapport au nombre de places restantes. Il reste " + nbPlacesRestantes + " places");

		return this.seanceRepository.save(s);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check si l'id existe
		this.seanceRepository.deleteById(id);
	}

	/**
	 * Ajoute un client à une séance
	 * @param sId l'id de la séance
	 * @param cId l'id du client à ajouter
	 * @return la séance mise à jour
	 */
	@Override
	public Seance addClient(String sId, String cId) {
		Seance s = this.findById(sId);
		System.out.println("seance " + s);
		Client c = this.clientService.findById(cId);
		System.out.println("client  = " + c);
		
		// Check si le client est déjà inscrit à la séance
		List<Assister> clients = s.getClients();
		Assister a = new Assister();
		for (Assister a2 : clients) {
			if (a2.getClient().getId().equals(cId))
				a = a2;
		}
		if (s.getClients().contains(a))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le client " + cId + " est déjà inscrit à la séance " + sId);
		
		// Check si le client a l'âge limite pour accéder à la séance
		int ageLimite = s.getFilm().getAgeLimite();
		int age = this.clientService.getAge(c);
		if (age < ageLimite)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le client " + cId + " (" + age + " ans) n'a pas l'âge minimum requis de " + ageLimite); 
		
		a.setPrix(this.calculerPrix(sId, cId));
		a.setClient(c);
		s.getClients().add(this.assisterService.save(a));

		return this.update(s);
	}

	/**
	 * Cherche les séances par film
	 * @param f le film par lequel rechercher les séances
	 * @return les séances qui comportent ce film
	 */
	@Override
	public List<Seance> findAllByFilm(Film f) {
		return this.seanceRepository.findAllByFilm(f);
	}

	/**
	 * Calcule la recette d'une séance
	 * @param id l'id de la séance
	 * @return la recette de la séance
	 */
	@Override
	public float getRecette(String id) {
		float prix = 0f;
		Seance s = this.findById(id);
		List<Assister> clients = s.getClients();
		for (Assister c: clients)
			prix += c.getPrix();
		
		return prix;
	}

	/**
	 * Calcule le nombre de places restantes pour une séance
	 * @param id l'id de la séance
	 * @return le nombre de places restantes
	 */
	@Override
	public int getPlacesRestantes(String id) {
		Seance s = this.findById(id);
		int totalPlaces = s.getSalle().getPlace();
		int placesTaken = s.getClients().size();
		
		return totalPlaces - placesTaken;
	}
	
	/**
	 * Cherche les séances dans une plage horaire
	 * @param min première date de la plage horaire
	 * @param max deuxième date (doit être postérieure à la première)
	 * @return les séances comprises dans la plage horaire sélectionnée
	 */
	@Override
	public List<Seance> findSeanceByDateBetween(String min, String max) {
		// Format de la date qui doit être entrée en paramètre :
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		
		// Check si la date est au bon format et la convertit en LocalDateTime 
		// Renvoie une exception si la date n'est pas au bon format
		LocalDateTime minDate = null;
		LocalDateTime maxDate = null;
		try {
			minDate = LocalDateTime.parse(min, dateTimeFormat);
			maxDate = LocalDateTime.parse(max, dateTimeFormat);
		} catch (RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La date doit être au format 'yyyy-MM-dd-HH:mm'");
		}
		
		return this.findSeanceByDateBetween(minDate, maxDate);
	}
	
	/**
	 * Cherche les séances dans une plage horaire
	 * @param min première date de la plage horaire
	 * @param max deuxième date (doit être postérieure à la première)
	 * @return les séances comprises dans la plage horaire sélectionnée
	 */
	@Override
	public List<Seance> findSeanceByDateBetween(LocalDateTime min, LocalDateTime max) {
		// Check si la deuxième date est bien postérieure à la première
		if (max.isBefore(min) || max.isEqual(min))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le deuxième horaire doit être postérieur au premier horaire");
		
		// min -1 minute pour prendre en compte l'heure exacte de min
		return this.seanceRepository.findSeanceByDateBetween(min.minusMinutes(1), max);
	}

	/**
	 * Cherche les séances par titre de film
	 * @param titre le titre du film à rechercher
	 * @return les séances qui comportent ce film
	 */
	@Override
	public List<Seance> findSeanceByFilmTitre(String titre) {
		return this.seanceRepository.findSeanceByFilmTitre(titre);
	}

	/**
	 * Calcule le prix d'une séance pour un client (Assister)
	 * prix de base: 10 euros
		remise enfant (-10 ans): -4 euros
		remise etudiant : -2 euros
		seance 3D: +3 euros
		seance IMAX: + 6 euros
		seance 4DX: + 8 euros
	 * @param sId id de la séance
	 * @param aId id du client
	 * @return le prix de la séance pour le client
	 */
	public float calculerPrix(String sId, String cId) {
		float prix = 10; // prix de base: 10 euros
		Seance s = this.findById(sId);
		Client c = this.clientService.findById(cId);
		
		// Ajoute le surplus selon le type de la séance
		switch (s.getType()) {
		case "3D": // seance 3D: +3 euros
			prix += 3; 
			break;
			
		case "IMAX": // seance IMAX: + 6 euros
			prix += 6; 
			break;
			
		case "4DX": // seance 4DX: + 8 euros
			prix += 8; 
			break;
			
		default: // séance 2D: valeur par défaut
			break;
		}
		
		// Applique la remise enfant (-10 ans): -4 euros
		int age = this.clientService.getAge(c);
		if (age < 10) 
			prix -= 4;
			
		// Applique la remise etudiant : -2 euros
		if (c.isEtudiant())	
			prix -= 2;
		
		return prix;
	}

	/**
	 * Cherche les séances par type
	 * @param type le type de séance à rechercher
	 * @return les séances qui correspondent au type sélectionné
	 */
	@Override
	public List<Seance> findSeanceByType(String type) {
		return this.seanceRepository.findSeanceByType(type);
	}



}
