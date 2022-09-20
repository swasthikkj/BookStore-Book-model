package com.bridgelabz.bookstorebookmodel.dto;

import lombok.Data;

/**
 * Purpose:DTO for bookstore 
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Data
public class BookDTO {
	private String bookName;
	private String bookAuthor;
	private String bookDescription;
	private double bookPrice;
	private int bookQuantity;
}
