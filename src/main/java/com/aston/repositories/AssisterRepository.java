package com.aston.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Assister;

public interface AssisterRepository extends MongoRepository<Assister, String> {

}
