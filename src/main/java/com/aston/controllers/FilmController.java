package com.aston.controllers;

import java.util.List;

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

	@Autowired private FilmService filmService;

	@GetMapping("")
	public List<Film> findAll() {
		return this.filmService.findAll();
	}
	
	@PostMapping("")
	public Film save(@RequestBody Film f) {
		return this.filmService.save(f);
	}

	@PutMapping("")
	public Film update(@RequestBody Film f) {
		return this.filmService.update(f);
	}
	
	@DeleteMapping("")
	public void deleteById(@RequestBody Film f) {
		this.deleteById(f.getId());
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable String id) {
		this.filmService.deleteById(id);
	}
	
	@GetMapping("{id}")
	public Film findById(@PathVariable String id) {
		return this.filmService.findById(id);
	}
	
	/**
	 * Récupère la recette d'un film par son id
	 * @param id id du film
	 * @return le montant de la recette en float
	 */
	@GetMapping("{id}/recette")
	public float getRecette(@PathVariable String id) {
		return this.filmService.getRecette(id);
	}
}
