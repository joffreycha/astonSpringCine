package com.aston.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String id, String classe) {
		super(HttpStatus.NOT_FOUND, "L'id " + id + " n'a pas été trouvé dans la collection " + classe);
	}

}
