package com.aston.services;

import java.time.LocalDateTime;
import java.util.List;

import com.aston.models.Film;
import com.aston.models.Seance;

public interface SeanceService {
	
	public Seance save(Seance s);
	public List<Seance> findAll();
	public Seance findById(String id);
	public Seance update(Seance s);
	public void deleteById(String id);
	
	public Seance addClient(String sId, String cId);
	public List<Seance> findAllByFilm(Film f);

	public float getRecette(String id);
	public int getPlacesRestantes(String id);
	public List<Seance> findSeanceByDateBetween(LocalDateTime min, LocalDateTime max);
	public List<Seance> findSeanceByFilmTitre(String titre);
	
	public List<Seance> findSeanceByFilmGenre(String genre);
	public List<Seance> findSeanceByType(String type);
}
