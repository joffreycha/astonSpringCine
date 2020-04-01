package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Film;
import com.aston.repositories.FilmRepository;
import com.aston.services.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
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
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, "Le film avec l'id " + id + " n'a pas été trouvé");
		return f;
	}

	@Override
	public Film update(Film f) {
		return this.filmRepository.save(f);
	}

	@Override
	public void deleteById(String id) {
		// TODO not found
		if (!this.filmRepository.findById(id).isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, "Le film avec l'id " + id + " n'a pas été trouvé");
		this.filmRepository.deleteById(id);
	}

}
