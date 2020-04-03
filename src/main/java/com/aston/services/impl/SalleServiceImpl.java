package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Salle;
import com.aston.repositories.SalleRepository;
import com.aston.services.SalleService;

@Service
public class SalleServiceImpl implements SalleService {

	@Autowired
	private SalleRepository salleRepository;

	@Override
	public Salle save(Salle s) {
		return this.salleRepository.save(s);
	}

	@Override
	public List<Salle> findAll() {
		return this.salleRepository.findAll();
	}

	@Override
	public Salle findById(String id) {
		Optional<Salle> s = this.salleRepository.findById(id);
		if (!s.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Salle.class.getName());
		
		return s.get();
	}

	@Override
	public Salle update(Salle s) {
		return this.salleRepository.save(s);
	}

	@Override
	public void deleteById(String id) {
		this.salleRepository.deleteById(id);
		
	}
	
	
}
