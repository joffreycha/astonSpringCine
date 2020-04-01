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

import com.aston.models.Client;
import com.aston.services.ClientService;

@RestController
@CrossOrigin
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("")
	public Client save(@RequestBody Client c) {
		return this.clientService.save(c);
	}
	
	@GetMapping("")
	public List<Client> findAll() {
		return this.clientService.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Client> findById(@PathVariable String id) {
		return this.clientService.findById(id);
	}
	
	@PutMapping("")
	public Client update(@RequestBody Client c) {
		return this.clientService.save(c);
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable String id) {
		this.clientService.deleteById(id);
	}
}
