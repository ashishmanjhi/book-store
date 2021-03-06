package com.book.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * @author Ashish.manjhi
 * 
 *This {@link BookNotFoundException} class is invoked when user try to find a book with id which is not present.   
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND,reason = "Book Not Found in the database with the given parameters")
public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	// String message
	private String msg;
	//Response code
	private ResponseEntity<Object> build;
	

	/**
	 * Constructors
	 * 
	 * @param msg
	 * @param build
	 */
	public BookNotFoundException(String msg, ResponseEntity<Object> build) {
		super();
		this.setMsg(msg);
		this.setBuild(build);
	}
	
	/*
	 * Methods
	 */

	public ResponseEntity<Object> getBuild() {
		return build;
	}

	public void setBuild(ResponseEntity<Object> build) {
		this.build = build;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

}
