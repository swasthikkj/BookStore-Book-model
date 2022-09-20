package com.bridgelabz.bookstorebookmodel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.bookstorebookmodel.dto.BookDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purpose:Model for bookstore 
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Entity
@Table(name = "BookModel")
@Data
@NoArgsConstructor
public class BookModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookId;
	private long userId;
	private String bookName;
	private String bookAuthor;
	private String bookDescription;
//	private MultipartFile bookLogo;
	private double bookPrice;
	private int bookQuantity;
	
	public BookModel(BookDTO bookDTO) {
		this.bookName = bookDTO.getBookName();
		this.bookAuthor = bookDTO.getBookAuthor();
		this.bookDescription = bookDTO.getBookDescription();
		this.bookPrice = bookDTO.getBookPrice();
		this.bookQuantity = bookDTO.getBookQuantity();
	}
}
