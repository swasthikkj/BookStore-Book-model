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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstorebookmodel.dto.BookDTO;
import com.bridgelabz.bookstorebookmodel.model.BookModel;
import com.bridgelabz.bookstorebookmodel.service.IBookService;
import com.bridgelabz.bookstorebookmodel.util.BookResponse;

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
	public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookDTO bookDTO, @RequestHeader String token) {
		BookModel bookModel = bookService.addBook(bookDTO, token);
		BookResponse response = new BookResponse(200, "Book added successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	/**
	 * Purpose:update book
	 */

	@PutMapping("/updateBook/{bookId}")
	public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long bookId, @RequestHeader String token) {
		BookModel bookModel = bookService.updateBook(bookDTO, bookId, token);
		BookResponse response = new BookResponse(200, "Book updated successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:get all books
	 */

	@GetMapping("/getAllBooks")
	public ResponseEntity<BookResponse> getAllBooks(@RequestHeader String token) {
		List<BookModel> bookModel = bookService.getAllBooks(token);
		BookResponse response = new BookResponse(200, "Fetched all books", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:get book by id
	 */

	@GetMapping("/getBookById/{bookId}")
	public ResponseEntity<BookResponse> getBookById(@PathVariable Long bookId, @RequestHeader String token) {
		Optional<BookModel> bookModel = bookService.getBookById(bookId, token);
		BookResponse response = new BookResponse(200, "Fetched book by id", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:delete book by id
	 */

	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable Long bookId, @RequestHeader String token) {
		BookResponse bookModel = bookService.deleteBook(bookId, token);
		BookResponse response = new BookResponse(200, "Book deleted successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:change book quantity
	 */

	@PutMapping("/changeBookQuantity/{bookId}/{quantity}")
	public ResponseEntity<BookResponse> changeBookQuantity(@PathVariable Long bookId, @PathVariable Integer quantity, @RequestHeader String token) {
		BookModel bookModel = bookService.changeBookQuantity(bookId, quantity, token);
		BookResponse response = new BookResponse(200, "Book quantity changed successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose:change book price
	 */

	@PutMapping("/changeBookPrice/{bookId}")
	public ResponseEntity<BookResponse> changeBookPrice(@PathVariable Long bookId, @RequestParam Long price, @RequestHeader String token) {
		BookModel bookModel = bookService.changeBookPrice(bookId, price, token);
		BookResponse response = new BookResponse(200, "Book price changed successfully", bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Purpose:validate book
	 */

	@GetMapping("/validateBook/{token}")
	public Boolean validateBook(@PathVariable String token) {
		return bookService.validateBook(token);
	}
	
	/**
	 * Purpose:update book quantity
	 */

	@PutMapping("/updateBookQuantity/{bookId}/{quantity}")
	public BookResponse updateBookQuantity(@PathVariable Long bookId, @PathVariable Integer quantity) {
		return bookService.updateBookQuantity(bookId, quantity);
	}
	
	@PutMapping("/updateBooksQuantity/{bookId}/{quantity}")
	public BookResponse updateBooksQuantity(@PathVariable Long bookId, @PathVariable Integer quantity) {
		return bookService.updateBookQuantity(bookId, quantity);
	}
}
