package com.aston.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NullValueException extends ResponseStatusException {
	
	private static final long serialVersionUID = 1L;
	
	public NullValueException(HttpStatus status, String reason) {
		super(status, reason);
		// TODO Auto-generated constructor stub
	}
	
}
