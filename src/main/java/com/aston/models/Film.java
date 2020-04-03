package com.aston.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

	@Id	private String id;
	private String titre;
	private int duree; // en minutes
	private int ageLimite;
	private String genre;
	private String visa;
}
