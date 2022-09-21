package com.bridgelabz.bookstorebookmodel.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstorebookmodel.exception.BookNotFoundException;
import com.bridgelabz.bookstorebookmodel.util.BookResponse;
@ControllerAdvice
public class BookExceptionHandler {
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<BookResponse> handleId(BookNotFoundException ab) {
		BookResponse response = new BookResponse();
		response.setErrorcode(400);
		response.setMessage(ab.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
