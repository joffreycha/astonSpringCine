package com.aston.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aston.models.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

}
