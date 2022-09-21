package com.bridgelabz.bookstorebookmodel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstorebookmodel.dto.BookDTO;
import com.bridgelabz.bookstorebookmodel.exception.BookNotFoundException;
import com.bridgelabz.bookstorebookmodel.model.BookModel;
import com.bridgelabz.bookstorebookmodel.repository.BookRepository;
import com.bridgelabz.bookstorebookmodel.util.BookResponse;
import com.bridgelabz.bookstorebookmodel.util.TokenUtil;


/**
 * Purpose:Service for bookstore(business logic)
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Service
public class BookService implements IBookService {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Purpose:To add books
	 */

	@Override
	public BookModel addBook(BookDTO bookDTO, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + token, Boolean.class);
		if (isUserPresent) {
			BookModel model = new BookModel(bookDTO);
			bookRepository.save(model);		
			return model;
		}
		throw new BookNotFoundException(400, "Book not added");
	}

	/**
	 * Purpose:To update book
	 */

	@Override
	public BookModel updateBook(BookDTO bookDTO, Long bookId, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookIdPresent = bookRepository.findByBookId(bookId);
			if(isBookIdPresent.isPresent()) {
				isBookIdPresent.get().setBookName(bookDTO.getBookName());
				isBookIdPresent.get().setBookDescription(bookDTO.getBookDescription());
				isBookIdPresent.get().setBookAuthor(bookDTO.getBookAuthor());
				isBookIdPresent.get().setBookPrice(bookDTO.getBookPrice());
				isBookIdPresent.get().setBookQuantity(bookDTO.getBookQuantity());
				bookRepository.save(isBookIdPresent.get());
				return isBookIdPresent.get();
			}
			throw new BookNotFoundException(400, "Book Not Found");
		}
		throw new BookNotFoundException(400, "Token Not Found");
	}

	/**
	 * Purpose:fetch all books
	 */

	@Override
	public List<BookModel> getAllBooks(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + token, Boolean.class);
		if (isUserPresent) {
			Long userId = tokenUtil.decodeToken(token);		
			List<BookModel> getAllBooks = bookRepository.findByUserId(userId);
			if(getAllBooks.size() > 0) {
				return getAllBooks;
			}			
		} 
		throw new BookNotFoundException(400, "Books not present");
	}

	/**
	 * Purpose:fetch notes by id
	 */

	@Override
	public Optional<BookModel> getBookById(Long bookId, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookIdPresent = bookRepository.findByBookId(bookId);
			if(isBookIdPresent.isPresent()) {
				return isBookIdPresent;
			} 
			throw new BookNotFoundException(400, "Book not present");
		}
		throw new BookNotFoundException(400, "Token not found");
	}

	/**
	 * Purpose:delete book
	 */

	@Override
	public BookResponse deleteBook(Long bookId, String token) {
		Optional<BookModel> isBookIdPresent = bookRepository.findByBookId(bookId);
		if (isBookIdPresent.isPresent()) {
			bookRepository.delete(isBookIdPresent.get());
			return new BookResponse(200, "Success", isBookIdPresent.get());
		} 
		throw new BookNotFoundException(400, "Book not found");
	}

	/**
	 * Purpose:change book quantity
	 */

	@Override
	public BookModel changeBookQuantity(Long bookId, Integer quantity, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookIdPresent = bookRepository.findByBookId(bookId);
			if (isBookIdPresent.isPresent()) {
				isBookIdPresent.get().setBookQuantity(quantity);
				bookRepository.save(isBookIdPresent.get());
				return isBookIdPresent.get();
			} 
		}
		throw new BookNotFoundException(400, "Token not found");
	}

	/**
	 * Purpose:change book price
	 */

	@Override
	public BookModel changeBookPrice(Long bookId, Long price, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookIdPresent = bookRepository.findByBookId(bookId);
			if (isBookIdPresent.isPresent()) {
				isBookIdPresent.get().setBookPrice(price);
				bookRepository.save(isBookIdPresent.get());
				return isBookIdPresent.get();
			}
		}
		throw new BookNotFoundException(400, "Token not found");
	}
	
	/**
	 * Purpose:validate book
	 */

	@Override
	public Boolean validateBook(String token) {
		Long decode = tokenUtil.decodeToken(token);
		Optional<BookModel> isTokenPresent = bookRepository.findById(decode);
		if (isTokenPresent.isPresent())
			return true;
		throw new BookNotFoundException(400, "Token not found");
	}
}
