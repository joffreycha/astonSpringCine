package com.aston.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aston.exceptions.NotFoundException;
import com.aston.models.Assister;
import com.aston.repositories.AssisterRepository;
import com.aston.services.AssisterService;

@Service
public class AssisterServiceImpl implements AssisterService {

	@Autowired	private AssisterRepository assisterRepository;
	
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
			throw new NotFoundException(id, Assister.class.getSimpleName());
		return a.get();
	}

	@Override
	public Assister update(Assister a) {
		return this.save(a);
	}

	@Override
	public void deleteById(String id) {
		this.findById(id); // check si l'id existe
		this.assisterRepository.deleteById(id);

	}

}
