package com.aston.services;

import java.util.List;
import java.util.Optional;

import com.aston.models.Cinema;

public interface CinemaService {

	public Cinema save(Cinema c);
	public List<Cinema> findAll();
	public Optional<Cinema> findById(String id);
	public Cinema update(Cinema c);
	public void deleteById(String id);
}
