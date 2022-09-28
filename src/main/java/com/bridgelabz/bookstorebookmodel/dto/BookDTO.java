package com.bridgelabz.bookstorebookmodel.dto;

import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * Purpose:DTO for bookstore 
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Data
public class BookDTO {
//	@Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "Book name is Invalid")
	private String bookName;
//	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Author name is Invalid")
	private String bookAuthor;
//	@Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "Description is Invalid")
	private String bookDescription;
//	@Pattern(regexp = "^[50-5000]{1,4}$", message = "Book price is Invalid")
	private long bookPrice;
//	@Pattern(regexp = "^[1-1000]$", message = "Book quantity is Invalid")
	private int bookQuantity;
}
