package com.aston.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aston.models.Client;
import com.aston.models.Film;
import com.aston.models.Seance;

public interface SeanceService {
	
	public Seance save(Seance s);
	public List<Seance> findAll();
	public Optional<Seance> findById(String id);
	public Seance update(Seance s);
	public void deleteById(String id);
	
	public Seance addClient(String sId, String cId);
	public List<Seance> findAllByFilm(Film f);

	public float getRecette(String id);
	public int getPlaces(String id);
}
