package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aston.dto.CinemaDTO;
import com.aston.exceptions.NotFoundException;
import com.aston.exceptions.NullValueException;
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
		// TODO Exception
		Cinema c = cDto.getCinema();
		if (c == null) {
			throw new NullValueException(HttpStatus.BAD_REQUEST, "Le cinema est null");
		}
		
		for (Salle s : cDto.getSalles()) {
			s.setCinema(c);
			this.salleService.save(s);
		}
		this.cinemaRepository.save(c);
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
