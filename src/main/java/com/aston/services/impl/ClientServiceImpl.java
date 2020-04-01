package com.aston.services.impl;

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

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client save(Client c) {
		return this.clientRepository.save(c);
	}

	@Override
	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}

	@Override
	public Optional<Client> findById(String id) {
		Optional<Client> c = this.clientRepository.findById(id);
		if (!c.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Client.class.getName());
		return c;
	}

	@Override
	public Client update(Client c) {
		return this.clientRepository.save(c);
	}

	@Override
	public void deleteById(String id) {
		if (!this.clientRepository.findById(id).isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Client.class.getName());
		this.clientRepository.deleteById(id);
	}

}
