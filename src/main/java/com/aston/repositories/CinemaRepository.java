package com.aston.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {

}
