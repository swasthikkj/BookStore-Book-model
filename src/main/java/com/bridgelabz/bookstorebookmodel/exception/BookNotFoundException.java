package com.bridgelabz.bookstorebookmodel.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BookNotFoundException extends RuntimeException {
	private int statuscode;
	private String message;

	public BookNotFoundException(int statuscode, String message) {
		super(message);
		this.statuscode = statuscode;
		this.message = message;
	}
}
