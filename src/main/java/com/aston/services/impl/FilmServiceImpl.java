package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Assister;
import com.aston.models.Client;
import com.aston.models.Film;
import com.aston.models.Seance;
import com.aston.repositories.FilmRepository;
import com.aston.services.FilmService;
import com.aston.services.SeanceService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private SeanceService seanceService;
	
	@Override
	public Film save(Film f) {
		return this.filmRepository.save(f);
	}

	@Override
	public List<Film> findAll() {
		return this.filmRepository.findAll();
	}

	@Override
	public Optional<Film> findById(String id) {
		Optional<Film> f  = this.filmRepository.findById(id);
		if (!f.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Film.class.getName());
		return f;
	}

	@Override
	public Film update(Film f) {
		return this.filmRepository.save(f);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id);  // check if the id exists
		this.filmRepository.deleteById(id);
	}

	@Override
	public float getRecette(String id) {
		// combien a rapporté un film
		float prix = 0;
		Optional<Film> optF = this.findById(id);
		if (!optF.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Film.class.getName());
		else {
			List<Seance> seances = this.seanceService.findAllByFilm(optF.get());
			for (Seance s: seances) {
				List<Assister> clients = s.getClients();
				for (Assister c: clients) {
					prix += c.getPrix();
				}
			}
		}
		return prix;
	}
}
