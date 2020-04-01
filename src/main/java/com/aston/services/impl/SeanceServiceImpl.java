package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Client;
import com.aston.models.Seance;
import com.aston.repositories.ClientRepository;
import com.aston.repositories.SeanceRepository;
import com.aston.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository seanceRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
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
		if (!this.seanceRepository.findById(id).isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Seance.class.getName());
		this.seanceRepository.deleteById(id);
	}

	@Override
	public Seance addClient(String sId, String cId) {
		// TODO Auto-generated method stub
		Optional<Seance> optS = this.seanceRepository.findById(sId);
		if (!optS.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, sId, Seance.class.getName());
		else {
			Optional<Client> c = this.clientRepository.findById(cId);
			if(!c.isPresent())
				throw new EntityNotFoundException(HttpStatus.NOT_FOUND, cId, Client.class.getName());
			else {
				Seance s = optS.get();
				s.getClients().add(c.get());
				return this.seanceRepository.save(s);
			}
		}
	}

}
