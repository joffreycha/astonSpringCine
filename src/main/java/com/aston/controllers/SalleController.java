package com.aston.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aston.models.Salle;
import com.aston.services.SalleService;

@RestController
@CrossOrigin
@RequestMapping("salles")
public class SalleController {

	@Autowired private SalleService salleService;
	
	@GetMapping("")
	public List<Salle> findAll() {
		return this.salleService.findAll();
	}
}
