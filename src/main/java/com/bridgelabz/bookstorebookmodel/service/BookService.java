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
import com.bridgelabz.bookstorebookmodel.util.Response;
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
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/validateUser/" + token, Boolean.class);
		if (isUserPresent) {
			Long decode = tokenUtil.decodeToken(token);
			BookModel model = new BookModel(bookDTO);
			model.setUserId(decode);
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
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/validateUser/" + token, Boolean.class);
		if (isUserPresent) {
			Long userId = tokenUtil.decodeToken(token);
			Optional<BookModel> isUserAndBookIdPresent = bookRepository.findByUserIdAndBookId(userId, bookId);
			if(isUserAndBookIdPresent.isPresent()) {
				isUserAndBookIdPresent.get().setBookName(bookDTO.getBookName());
				isUserAndBookIdPresent.get().setBookDescription(bookDTO.getBookDescription());
				isUserAndBookIdPresent.get().setBookAuthor(bookDTO.getBookAuthor());
				isUserAndBookIdPresent.get().setBookPrice(bookDTO.getBookPrice());
				isUserAndBookIdPresent.get().setBookQuantity(bookDTO.getBookQuantity());
				bookRepository.save(isUserAndBookIdPresent.get());
				return isUserAndBookIdPresent.get();
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
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/validateUser/" + token, Boolean.class);
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
		boolean isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/validateUser/" + token, Boolean.class);
		if (isUserPresent) {
			Long userId = tokenUtil.decodeToken(token);
			Optional<BookModel> isUserAndBookIdPresent = bookRepository.findByUserIdAndBookId(userId, bookId);
			if(isUserAndBookIdPresent.isPresent()) {
				return isUserAndBookIdPresent;
			} 
			throw new BookNotFoundException(400, "Book not present");
		}
		throw new BookNotFoundException(400, "Token not found");
	}

	/**
	 * Purpose:delete book
	 */

	@Override
	public Response deleteBook(Long bookId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		Optional<BookModel> isUserAndBookIdPresent = bookRepository.findByUserIdAndBookId(userId, bookId);
		if (isUserAndBookIdPresent.isPresent()) {
			bookRepository.delete(isUserAndBookIdPresent.get());
			return new Response(200, "Success", isUserAndBookIdPresent.get());
		} 
		throw new BookNotFoundException(400, "Book not found");
	}

	/**
	 * Purpose:change book quantity
	 */

	@Override
	public BookModel changeBookQuantity(Long bookId, Integer quantity, String token) {
		Long userId = tokenUtil.decodeToken(token);
		Optional<BookModel> isUserAndBookIdPresent = bookRepository.findByUserIdAndBookId(userId, bookId);
		if (isUserAndBookIdPresent.isPresent()) {
			isUserAndBookIdPresent.get().setBookQuantity(quantity);
			bookRepository.save(isUserAndBookIdPresent.get());
			return isUserAndBookIdPresent.get();
		} 
		throw new BookNotFoundException(400, "Token not found");
	}

	/**
	 * Purpose:change book price
	 */

	@Override
	public BookModel changeBookPrice(Long bookId, Double price, String token) {
		Long userId = tokenUtil.decodeToken(token);
		Optional<BookModel> isUserAndBookIdPresent = bookRepository.findByUserIdAndBookId(userId, bookId);
		if (isUserAndBookIdPresent.isPresent()) {
			isUserAndBookIdPresent.get().setBookPrice(price);
			bookRepository.save(isUserAndBookIdPresent.get());
			return isUserAndBookIdPresent.get();
		} 
		throw new BookNotFoundException(400, "Token not found");
	}
}
