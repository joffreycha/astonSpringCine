package com.aston.services;

import java.util.List;
import java.util.Optional;

import com.aston.models.Salle;

public interface SalleService {

	public Salle save(Salle s);
	public List<Salle> findAll();
	public Optional<Salle> findById(String id);
	public Salle update(Salle s);
	public void deleteById(String id);
}
