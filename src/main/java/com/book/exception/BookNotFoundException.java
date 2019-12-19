package com.book.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="This Book is not found in the system")
public class BookNotFoundException extends Exception {

	private String msg;
	
	public BookNotFoundException(String msg) {
		super();
		this.setMsg(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private static final long serialVersionUID = 1L;

}
