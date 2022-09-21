package com.bridgelabz.bookstorebookmodel.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponse {
	private int errorcode;
	private String message;
	private Object token;
	
	public BookResponse() {
		
	}
}
