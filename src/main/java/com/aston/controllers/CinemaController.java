package com.aston.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aston.dto.CinemaDTO;
import com.aston.services.CinemaService;

@RestController
@CrossOrigin
@RequestMapping("cinemas")
public class CinemaController {

	@Autowired private CinemaService cinemaService;
	
	@PostMapping("")
	public CinemaDTO save(@RequestBody CinemaDTO cDto) {
		return this.cinemaService.save(cDto);
	}
	
}
