package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Client;
import com.aston.models.Seance;
import com.aston.repositories.SeanceRepository;
import com.aston.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository seanceRepository;
	
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
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, "La séance avec l'id " + id + " n'a pas été trouvée");
		return s;
	}

	@Override
	public Seance update(Seance s) {
		return this.seanceRepository.save(s);
	}

	@Override
	public void deleteById(String id) {
		if (!this.seanceRepository.findById(id).isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, "La séance avec l'id " + id + " n'a pas été trouvée");
		this.seanceRepository.deleteById(id);
	}

	@Override
	public Client addClient(String sId, String cId) {
		// TODO Auto-generated method stub
		return null;
	}

}
