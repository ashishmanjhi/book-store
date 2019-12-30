package com.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason = "Author Not Found in the database with the given parameters")
public class AuthorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6628392998402192795L;
	
	
	// String message
	private String msg;
	//Response code
	private ResponseEntity<Object> build;

	
	/**
	 * Constructors for AuthorNotFoundException
	 * 
	 * @param msg
	 * @param build
	 */
	public AuthorNotFoundException(String msg, ResponseEntity<Object> build) {
		super();
		this.setMsg(msg);
		this.setBuild(build);
	}

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
