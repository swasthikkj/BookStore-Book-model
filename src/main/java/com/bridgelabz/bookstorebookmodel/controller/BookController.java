package com.bridgelabz.bookstorebookmodel.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstorebookmodel.dto.BookDTO;
import com.bridgelabz.bookstorebookmodel.model.BookModel;
import com.bridgelabz.bookstorebookmodel.service.IBookService;
import com.bridgelabz.bookstorebookmodel.util.Response;

/**
 * Purpose:Controller for bookstore 
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/bookService")
public class BookController {
	@Autowired
	IBookService bookService;

	/**
	 * Purpose:add book
	 */

	@PostMapping("/addBook")
	public ResponseEntity<Response> addBook(@Valid @RequestBody BookDTO bookDTO, @RequestHeader String token) {
		BookModel bookModel = bookService.addBook(bookDTO, token);
		Response response = new Response(200, "Book added successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	/**
	 * Purpose:update book
	 */

	@PutMapping("/updateBook/{bookId}")
	public ResponseEntity<Response> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long bookId, @RequestHeader String token) {
		BookModel bookModel = bookService.updateBook(bookDTO, bookId, token);
		Response response = new Response(200, "Book updated successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:get all books
	 */

	@GetMapping("/getAllBooks")
	public ResponseEntity<Response> getAllBooks(@RequestHeader String token) {
		List<BookModel> bookModel = bookService.getAllBooks(token);
		Response response = new Response(200, "Fetched all books", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:get book by id
	 */

	@GetMapping("/getBookById/{bookId}")
	public ResponseEntity<Response> getBookById(@PathVariable Long bookId, @RequestHeader String token) {
		Optional<BookModel> bookModel = bookService.getBookById(bookId, token);
		Response response = new Response(200, "Fetched book by id", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:delete book by id
	 */

	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<Response> deleteBook(@PathVariable Long bookId, @RequestHeader String token) {
		Response bookModel = bookService.deleteBook(bookId, token);
		Response response = new Response(200, "Book deleted successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:change book quantity
	 */

	@PutMapping("/changeBookQuantity/{bookId}")
	public ResponseEntity<Response> changeBookQuantity(@PathVariable Long bookId, @PathVariable Integer quantity, @RequestHeader String token) {
		BookModel bookModel = bookService.changeBookQuantity(bookId, quantity, token);
		Response response = new Response(200, "Book quantity changed successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:change book price
	 */

	@PutMapping("/changeBookPrice/{bookId}")
	public ResponseEntity<Response> changeBookPrice(@PathVariable Long bookId, @PathVariable Double price, @RequestHeader String token) {
		BookModel bookModel = bookService.changeBookPrice(bookId, price, token);
		Response response = new Response(200, "Book price changed successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
