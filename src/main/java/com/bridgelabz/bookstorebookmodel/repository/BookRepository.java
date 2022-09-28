package com.bridgelabz.bookstorebookmodel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstorebookmodel.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

	Optional<BookModel> findByBookId(Long bookId);

	List<BookModel> findByUserId(Long userId);
	
	List<BookModel> findByAuthor(String bookAuthor);
	
	List<BookModel> findByBookName(String bookName);

}
