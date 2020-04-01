package com.aston.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aston.models.Seance;

@Service
public interface SeanceService {
	
	public Seance save(Seance s);
	public List<Seance> findAll();
	public Optional<Seance> findById(String id);
	public Seance update(Seance s);
	public void deleteById(String id);

}