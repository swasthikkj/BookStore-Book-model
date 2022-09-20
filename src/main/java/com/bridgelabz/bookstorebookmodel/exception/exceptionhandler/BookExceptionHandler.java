package com.bridgelabz.bookstorebookmodel.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstorebookmodel.exception.BookNotFoundException;
import com.bridgelabz.bookstorebookmodel.util.Response;
@ControllerAdvice
public class BookExceptionHandler {
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Response> handleId(BookNotFoundException ab) {
		Response response = new Response();
		response.setErrorcode(400);
		response.setMessage(ab.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
