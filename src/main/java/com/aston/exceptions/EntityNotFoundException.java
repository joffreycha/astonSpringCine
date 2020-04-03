package com.aston.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(HttpStatus statusCode, String id, String classe) {
		super(statusCode, "L'id " + id + " n'a pas été trouvé dans la collection " + classe);
	}

}
