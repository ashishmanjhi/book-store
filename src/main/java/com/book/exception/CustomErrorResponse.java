package com.book.exception;

import java.util.Optional;

import com.book.model.Book;

public class CustomErrorResponse {
	 public String responseMsg;
	    public Optional<Book> bookList;

	    public CustomErrorResponse(String responseMsg, Optional<Book> bookList) {
	        this.responseMsg = responseMsg;
	        this.bookList = bookList;
	    }
	}
