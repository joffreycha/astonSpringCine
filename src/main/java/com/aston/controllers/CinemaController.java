package com.aston.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aston.dto.CinemaDTO;
import com.aston.models.Cinema;
import com.aston.services.CinemaService;

@RestController
@CrossOrigin
@RequestMapping("cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@PostMapping("")
	public CinemaDTO save(@RequestBody CinemaDTO cDto) {
		return this.cinemaService.save(cDto);
	}
	
	@GetMapping("")
	public List<Cinema> findAll() {
		return this.cinemaService.findAll();
	}
	
	@GetMapping("{id}")
	public Cinema findById(String id) {
		return this.cinemaService.findById(id);
	}
	
	@PutMapping("")
	public CinemaDTO update(CinemaDTO cDto) {
		return this.cinemaService.update(cDto);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(String id) {
		this.cinemaService.deleteById(id);
	}
	
}
