package com.aston.services;

import java.util.List;

import com.aston.models.Client;

public interface ClientService {

	public Client save(Client c);
	public List<Client> findAll();
	public Client findById(String id);
	public Client update(Client c);
	public void deleteById(String id);
	
	public int getAge(Client c);
}
