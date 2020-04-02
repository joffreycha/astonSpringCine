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

import com.aston.models.Seance;
import com.aston.services.SeanceService;

@RestController
@CrossOrigin
@RequestMapping("seances")
public class SeanceController {

	@Autowired
	private SeanceService seanceService;
	
	@PostMapping("")
	public Seance save(@RequestBody Seance s) {
		return this.seanceService.save(s);
	}
	
	@GetMapping("")
	public List<Seance> findAll() {
		return this.seanceService.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Seance> findById(@PathVariable String id) {
		return this.seanceService.findById(id);
	}
	
	@PutMapping("")
	public Seance update(@RequestBody Seance s) {
		return this.seanceService.update(s);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable String id) {
		this.seanceService.deleteById(id);
	}
	
	
	@PutMapping("{sId}/clients/{cId}")
	public Seance addClient(@PathVariable String sId, @PathVariable String cId) {
		return this.seanceService.addClient(sId, cId);
	}
	
	// Get the income of a seance
	@GetMapping("{id}/recette")
	public float getRecette(@PathVariable String id) {
		return this.seanceService.getRecette(id);
	}
	
	// Get the number of places remaining
	@GetMapping("{id}/places")
	public int getPlaces(@PathVariable String id) {
		return this.seanceService.getPlaces(id);
	}
	
	
}
