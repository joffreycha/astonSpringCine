package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.exceptions.NotFoundException;
import com.aston.models.Assister;
import com.aston.models.Film;
import com.aston.models.Seance;
import com.aston.repositories.FilmRepository;
import com.aston.services.FilmService;
import com.aston.services.SeanceService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired	private FilmRepository filmRepository;
	@Autowired	private SeanceService seanceService;
	
	@Override
	public Film save(Film f) {
		// Check si les champs requis ne sont pas null
		if (f.getTitre() == null || f.getTitre().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez indiquer un titre pour le film");
		if (f.getGenre() == null || f.getGenre().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez sélectionner un genre pour le film");
		if (f.getVisa() == null || f.getVisa().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez indiquer un visa pour le film");
			
		return this.filmRepository.save(f);
	}

	@Override
	public List<Film> findAll() {
		return this.filmRepository.findAll();
	}

	@Override
	public Film findById(String id) {
		Optional<Film> f  = this.filmRepository.findById(id);
		if (!f.isPresent())
			throw new NotFoundException(id, Film.class.getSimpleName());
		
		return f.get();
	}

	@Override
	public Film update(Film f) {
		return this.filmRepository.save(f);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check si l'id existe
		this.filmRepository.deleteById(id);
	}
	
	/**
	 * Calcule la recette d'un film toutes séances confondues
	 * @param id id du film
	 * @return le montant de la recette
	 */
	@Override
	public float getRecette(String id) {
		float prix = 0;
		Film f = this.findById(id);
		
		List<Seance> seances = this.seanceService.findAllByFilm(f);
		for (Seance s: seances) {
			List<Assister> clients = s.getClients();
			for (Assister c: clients) {
				prix += c.getPrix();
			}
		}
		
		return prix;
	}

}
