package com.aston.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Client;
import com.aston.repositories.ClientRepository;
import com.aston.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired	private ClientRepository clientRepository;
	
	@Override
	public Client save(Client c) {
		return this.clientRepository.save(c);
	}

	@Override
	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}

	@Override
	public Client findById(String id) {
		Optional<Client> c = this.clientRepository.findById(id);
		if (!c.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Client.class.getName());
		return c.get();
	}

	@Override
	public Client update(Client c) {
		return this.save(c);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check si l'id existe
		this.clientRepository.deleteById(id);
	}
	
	// Calcule et retourne l'age d'un client
	@Override
	public int getAge(Client c) {
		int age = 0;
		Period period = Period.between(LocalDate.now(), c.getNaissance());
		age = period.getYears();

		return age;
	}

}
