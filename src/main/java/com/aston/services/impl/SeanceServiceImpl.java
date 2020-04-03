package com.aston.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.exceptions.NullValueException;
import com.aston.models.Assister;
import com.aston.models.Client;
import com.aston.models.Film;
import com.aston.models.Seance;
import com.aston.repositories.SeanceRepository;
import com.aston.services.AssisterService;
import com.aston.services.ClientService;
import com.aston.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired	private SeanceRepository seanceRepository;
	@Autowired	private ClientService clientService;
	@Autowired	private AssisterService assisterService;
	
	@Override
	public Seance save(Seance s) {
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
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Seance.class.getName());
		return s.get();
	}

	@Override
	public Seance update(Seance s) {
		return this.save(s);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check if the id exists
		this.seanceRepository.deleteById(id);
	}

	@Override
	public Seance addClient(String sId, String cId) {
		Seance s = this.findById(sId);
		Client c = this.clientService.findById(cId);
		
		s.getClients().add(new Assister(this.calculerPrix(sId, cId), c));
		return this.update(s);
	}

	@Override
	public List<Seance> findAllByFilm(Film f) {
		return this.seanceRepository.findAllByFilm(f);
	}

	// Calcule la recette d'une séance
	@Override
	public float getRecette(String id) {
		
		float prix = 0;
		Seance s = this.findById(id);
		List<Assister> clients = s.getClients();
		for (Assister c: clients)
			prix += c.getPrix();
		return prix;
	}

	// Récupère le nombre de places restantes pour une séance
	@Override
	public int getPlaces(String id) {
		Optional<Seance> optS = this.seanceRepository.findById(id);
		if (optS.get().getSalle() == null)
			throw new NullValueException(HttpStatus.NO_CONTENT, "Aucune salle n'a été trouvée dans la séance " + id);
		int totalPlaces = optS.get().getSalle().getPlace();
		int placesTaken = optS.get().getClients().size();
		return totalPlaces - placesTaken;
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
	 * @return
	 */
	public float calculerPrix(String sId, String aId) {
		float prix = 10; // prix de base: 10 euros
		
		Seance s = this.findById(sId);
		Assister a = this.assisterService.findById(aId);
		s.getClients();
		
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
			
		default:
			System.out.println("Erreur de type");
			break;
		}
		
		// TODO calculer l'âge proprement
		int age = LocalDate.now().getYear() - a.getClient().getNaissance().getYear();
		
		if (age < 10) // remise enfant (-10 ans): -4 euros
			prix -= 4;
			
		
		if (a.getClient().isEtudiant())	// remise etudiant : -2 euros
			prix -= 2;
		
		return prix;
	}

}
