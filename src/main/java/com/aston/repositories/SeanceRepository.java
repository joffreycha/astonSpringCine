package com.aston.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {

	
}
