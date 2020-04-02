package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Assister;
import com.aston.models.Client;
import com.aston.models.Film;
import com.aston.models.Seance;
import com.aston.repositories.SeanceRepository;
import com.aston.services.ClientService;
import com.aston.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository seanceRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public Seance save(Seance s) {
		return this.seanceRepository.save(s);
	}

	@Override
	public List<Seance> findAll() {
		return this.seanceRepository.findAll();
	}

	@Override
	public Optional<Seance> findById(String id) {
		Optional<Seance> s = this.seanceRepository.findById(id);
		if (!s.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Seance.class.getName());
		return s;
	}

	@Override
	public Seance update(Seance s) {
		return this.seanceRepository.save(s);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check if the id exists
		this.seanceRepository.deleteById(id);
	}

	@Override
	public Seance addClient(String sId, String cId) {
		Optional<Seance> optS = this.findById(sId);
		if (!optS.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, sId, Seance.class.getName());
		else {
			Optional<Client> c = this.clientService.findById(cId);
			if(!c.isPresent())
				throw new EntityNotFoundException(HttpStatus.NOT_FOUND, cId, Client.class.getName());
			else {
				Seance s = optS.get();
				// TODO mettre le bon prix en fonction du client
				s.getClients().add(new Assister(10, c.get()));
				return this.update(s);
			}
		}
	}

	@Override
	public List<Seance> findAllByFilm(Film f) {
		return this.seanceRepository.findAllByFilm(f);
	}

	@Override
	public float getRecette(String id) {
		// combien a rapporté une séance
		float prix = 0;
		Optional<Seance> optS = this.findById(id);
		if (!optS.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Seance.class.getName());
		else {			
			List<Assister> clients = optS.get().getClients();
			for (Assister c: clients) {
				prix += c.getPrix();
			}			
		}
		return prix;
	}

}
