package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aston.models.Seance;
import com.aston.repositories.SeanceRepository;
import com.aston.services.SeanceService;

public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository seanceRepository;
	
	@Override
	public Seance save(Seance s) {
		// TODO seance doesn't exist
		return this.seanceRepository.save(s);
	}

	@Override
	public List<Seance> findAll() {
		return this.seanceRepository.findAll();
	}

	@Override
	public Optional<Seance> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seance update(Seance s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

}
