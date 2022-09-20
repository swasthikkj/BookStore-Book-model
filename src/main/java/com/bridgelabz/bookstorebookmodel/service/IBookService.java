package com.bridgelabz.bookstorebookmodel.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.bookstorebookmodel.dto.BookDTO;
import com.bridgelabz.bookstorebookmodel.model.BookModel;
import com.bridgelabz.bookstorebookmodel.util.Response;

/**
 * Purpose:Interface for bookstore 
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

public interface IBookService {
	
	BookModel addBook(BookDTO bookDTO, String token);
	
	BookModel updateBook(BookDTO bookDTO, Long bookId, String token);

	List<BookModel> getAllBooks(String token);

	Optional<BookModel> getBookById(Long bookId, String token);

	Response deleteBook(Long bookId, String token);

	BookModel changeBookQuantity(Long bookId, Integer quantity, String token);
	
	BookModel changeBookPrice(Long bookId, Double price, String token);
		
}
