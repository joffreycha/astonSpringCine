package com.aston.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aston.models.Film;
import com.aston.services.FilmService;

@RestController
@CrossOrigin
@RequestMapping("films")
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@PostMapping("")
	public Film save(@RequestBody Film f) {
		return this.filmService.save(f);
	}
	
	@GetMapping("")
	public List<Film> findAll() {
		return this.filmService.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Film> findById(@PathVariable String id) {
		return this.filmService.findById(id);
	}
	
	@PutMapping("")
	public Film update(@RequestBody Film f) {
		return this.filmService.update(f);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable String id) {
		this.filmService.deleteById(id);
	}
}
