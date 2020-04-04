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
	
	/**
	 * Récupère la recette d'une seance par son id
	 * @param id l'id de la séance
	 * @return la recette de la séance
	 */
	@GetMapping("{id}/recette")
	public float getRecette(@PathVariable String id) {
		return this.seanceService.getRecette(id);
	}

	/**
	 * Récupère le nombre de places restantes pour une séance
	 * @param id l'id de la séance
	 * @return le nombre de places restantes
	 */
	@GetMapping("{id}/places")
	public int getPlaces(@PathVariable String id) {
		return this.seanceService.getPlacesRestantes(id);
	}
	
	/**
	 * Cherche les séances dans une plage horaire
	 * @param min première date de la plage horaire
	 * @param max deuxième date (doit être postérieure à la première)
	 * @return les séances comprises dans la plage horaire sélectionnée
	 */
	@GetMapping("horaire/{min}/{max}")
	public List<Seance> findSeanceByDateBetween(@PathVariable String min, @PathVariable String max) {
		return this.seanceService.findSeanceByDateBetween(min, max);
	}
	
	/**
	 * Cherche les séances par titre de film
	 * @param titre le titre du film à rechercher
	 * @return les séances qui comportent ce film
	 */
	@GetMapping("film/{nom}")
	public List<Seance> findSeanceByFilmTitre(@PathVariable String titre) {
		return this.seanceService.findSeanceByFilmTitre(titre);
	}
	
	/**
	 * Ajoute un client à une séance
	 * @param sId l'id de la séance
	 * @param cId l'id du client à ajouter
	 * @return la séance mise à jour
	 */
	@PostMapping("{sId}/assister/{cId}")
	public void addClient(@PathVariable String sId, @PathVariable String cId) {
		this.seanceService.addClient(sId, cId);
	}
	
	/**
	 * Cherche les séances par type (2D, 3D, IMAX, 4DX) 
	 * @param type le type de séance à rechercher
	 * @return les séances qui correspondent au type sélectionné
	 */
	@GetMapping("type/{type}")
	public List<Seance> findSeanceByType(@PathVariable String type) {
		return this.seanceService.findSeanceByType(type);
	}
	
}
