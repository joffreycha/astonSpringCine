package com.aston.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.exceptions.NotFoundException;
import com.aston.models.Client;
import com.aston.repositories.ClientRepository;
import com.aston.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired	private ClientRepository clientRepository;
	
	@Override
	public Client save(Client c) {
		// Check si les champs requis sont null
		if (c.getNom() == null || c.getNom().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez indiquer un nom pour le client");
		if (c.getNaissance() == null)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez indiquer une date de naissance pour le client");
	
		// Check si la date de naissance est bien antérieure à la date du jour
		if (c.getNaissance().isAfter(LocalDate.now()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La date de naissance doit être antérieure à la date du jour");
		
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
			throw new NotFoundException(id, Client.class.getSimpleName());
		
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
	
	/**
	 * Calcule l'âge d'un client
	 * @param c le client dont on veut calculer l'âge
	 * @return l'âge du client
	 */
	@Override
	public int getAge(Client c) {
		int age = 0;
		Period period = Period.between(c.getNaissance(), LocalDate.now());
		age = period.getYears();

		return age;
	}

}
