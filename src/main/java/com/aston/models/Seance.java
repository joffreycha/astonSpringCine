package com.aston.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seance {

	@Id
	private String id;
	
	private Film film;
	private LocalDateTime date;
	
	@DBRef
	private List<Client> clients = new ArrayList<Client>(); 
	
	@DBRef
	private Salle salle;
	
	private String type;
	
}
