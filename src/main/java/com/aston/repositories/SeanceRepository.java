package com.aston.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Film;
import com.aston.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {

	public List<Seance> findAllByFilm(Film f);
	public List<Seance> findSeanceByDateBetween(LocalDateTime min, LocalDateTime max);
	public List<Seance> findSeanceByFilmTitre(String titre);
	
	public List<Seance> findSeanceByType(String type);
}
