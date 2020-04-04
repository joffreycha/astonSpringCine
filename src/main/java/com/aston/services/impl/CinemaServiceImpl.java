package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aston.dto.CinemaDTO;
import com.aston.exceptions.NotFoundException;
import com.aston.models.Cinema;
import com.aston.models.Salle;
import com.aston.repositories.CinemaRepository;
import com.aston.services.CinemaService;
import com.aston.services.SalleService;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired	private CinemaRepository cinemaRepository;
	@Autowired	private SalleService salleService;
	
	@Override
	public CinemaDTO save(CinemaDTO cDto) {
		Cinema c = cDto.getCinema();
		if (c == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le cinema ne peut pas être null");
		}
		
		// Check si le nom du cinéma n'est pas pas null
		if (c.getNom() == null || c.getNom().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le cinéma doit avoir un nom");

		this.cinemaRepository.save(c);
		
		
		// Check s'il y a au moins une salle enregistrée
		if (cDto.getSalles().size() == 0)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le cinéma doit comporter au moins une salle");
			
		for (Salle s : cDto.getSalles()) {
			s.setCinema(c);
			s.setId(this.salleService.save(s).getId());
		}
		return cDto;
	}

	@Override
	public List<Cinema> findAll() {
		return this.cinemaRepository.findAll();
	}

	@Override
	public Cinema findById(String id) {
		Optional<Cinema> c = this.cinemaRepository.findById(id);
		if (!c.isPresent())
			throw new NotFoundException(id, Cinema.class.getSimpleName());
		
		return c.get();
	}

	@Override
	public CinemaDTO update(CinemaDTO cDto) {
		return this.save(cDto);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check si l'id existe
		this.cinemaRepository.deleteById(id);
	}

}
