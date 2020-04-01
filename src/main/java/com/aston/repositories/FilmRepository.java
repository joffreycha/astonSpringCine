package com.aston.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Film;

public interface FilmRepository extends MongoRepository<Film, String> {

}
