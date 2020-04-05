package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.exceptions.NotFoundException;
import com.aston.models.Salle;
import com.aston.repositories.SalleRepository;
import com.aston.services.SalleService;

@Service
public class SalleServiceImpl implements SalleService {

	@Autowired	private SalleRepository salleRepository;

	@Override
	public Salle save(Salle s) {
		// Check si les champs requis sont null
		if (s.getNom() == null || s.getNom().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez indiquer un nom pour la salle");
		if (s.getPlace() <= 0)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La salle doit comporter au moins 1 place");

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
			throw new NotFoundException(id, Salle.class.getSimpleName());
		
		return s.get();
	}

	@Override
	public Salle update(Salle s) {
		return this.salleRepository.save(s);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check si l'id existe
		this.salleRepository.deleteById(id);
	}
	
}
