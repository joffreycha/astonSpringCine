package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.aston.exceptions.EntityNotFoundException;
import com.aston.models.Assister;
import com.aston.repositories.AssisterRepository;
import com.aston.services.AssisterService;

public class AssisterServiceImpl implements AssisterService {

	@Autowired
	private AssisterRepository assisterRepository;
	
	@Override
	public Assister save(Assister a) {
		return this.assisterRepository.save(a);
	}

	@Override
	public List<Assister> findAll() {
		return this.assisterRepository.findAll();
	}

	@Override
	public Assister findById(String id) {
		Optional<Assister> a = this.assisterRepository.findById(id);
		if (!a.isPresent())
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND, id, Assister.class.getName());
		return a.get();
	}

	@Override
	public Assister update(Assister a) {
		return this.save(a);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id);
		this.assisterRepository.deleteById(id);

	}

}
