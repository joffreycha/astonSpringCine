package com.aston.dto;

import java.util.ArrayList;
import java.util.List;

import com.aston.models.Cinema;
import com.aston.models.Salle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class CinemaDTO {

	private Cinema cinema;
	private List<Salle> salles = new ArrayList<Salle>();	
}
