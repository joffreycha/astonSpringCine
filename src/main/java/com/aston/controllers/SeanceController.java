package com.aston.controllers;

import java.time.LocalDateTime;
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

import com.aston.models.Seance;
import com.aston.services.SeanceService;

@RestController
@CrossOrigin
@RequestMapping("seances")
public class SeanceController {

	@Autowired
	private SeanceService seanceService;

	@GetMapping("")
	public List<Seance> findAll() {
		return this.seanceService.findAll();
	}
	
	@PostMapping("")
	public Seance save(@RequestBody Seance s) {
		return this.seanceService.save(s);
	}

	@PutMapping("")
	public Seance update(@RequestBody Seance s) {
		return this.seanceService.update(s);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Seance s) {
		this.deleteById(s.getId());
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable String id) {
		this.seanceService.deleteById(id);
	}
	
	@GetMapping("{id}")
	public Seance findById(@PathVariable String id) {
		return this.seanceService.findById(id);
	}
	
	// Récupère la recette d'une seance par son id
	@GetMapping("{id}/recette")
	public float getRecette(@PathVariable String id) {
		return this.seanceService.getRecette(id);
	}

	// Récupère le nombre de places restantes pour une séance
	@GetMapping("{id}/places")
	public int getPlaces(@PathVariable String id) {
		return this.seanceService.getPlacesRestantes(id);
	}
	
	@GetMapping("{id}/horaire/{min}/{max}")
	public List<Seance> findSeanceByDateBetween(@PathVariable LocalDateTime min, @PathVariable LocalDateTime max) {
		return this.seanceService.findSeanceByDateBetween(min, max);
	}
	
	@GetMapping("{id}/film/{nom}")
	public List<Seance> findSeanceByFilmTitre(@PathVariable String titre) {
		return this.seanceService.findSeanceByFilmTitre(titre);
	}
	
	/**
	 * Ajoute un client (Assister) à une séance
	 * @param sId id de la séance
	 * @param cId id du client à ajouter
	 */
	@PostMapping("{sId}/assister/{cId}")
	public void addClient(@PathVariable String sId, @PathVariable String cId) {
		this.seanceService.addClient(sId, cId);
	}
	

	@GetMapping("film/{genre}")
	public List<Seance> findSeanceByFilmGenre(@PathVariable String genre) {
		return this.seanceService.findSeanceByFilmGenre(genre);
	}
	
	@GetMapping("{type}")
	public List<Seance> findSeanceByType(@PathVariable String type) {
		return this.seanceService.findSeanceByType(type);
	}
	
}
