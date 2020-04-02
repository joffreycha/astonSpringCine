package com.aston.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Salle;

public interface SalleRepository extends MongoRepository<Salle, String> {

}
