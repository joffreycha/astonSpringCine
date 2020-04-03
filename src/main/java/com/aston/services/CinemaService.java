package com.aston.services;

import java.util.List;

import com.aston.dto.CinemaDTO;
import com.aston.models.Cinema;

public interface CinemaService {

	public CinemaDTO save(CinemaDTO cDto);
	public List<Cinema> findAll();
	public Cinema findById(String id);
	public CinemaDTO update(CinemaDTO cDto);
	public void deleteById(String id);
}
