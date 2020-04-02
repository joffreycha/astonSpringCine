package com.aston.services;

import java.util.List;
import java.util.Optional;

import com.aston.models.Film;

public interface FilmService {

	public Film save(Film f);
	public List<Film> findAll();
	public Optional<Film> findById(String id);
	public Film update(Film f);
	public void deleteById(String id);
	
	public float getRecette(String id);
}
