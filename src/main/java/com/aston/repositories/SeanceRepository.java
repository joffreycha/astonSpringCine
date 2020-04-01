package com.aston.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Film;
import com.aston.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {

	public List<Seance> findAllByFilm(Film f);
}
