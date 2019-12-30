package com.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.book.model.Book;


/**
 * @author Ashish.manjhi
 *This {@link BookIdAlreadyExistsException} class is invoked when user try to create a book with id which is already present.
 *
 */
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Book with the inputed book-id is already registered in the system")
public class BookIdAlreadyExistsException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	// Book ID
	public int bookId;
	// Response Code
	public ResponseEntity<Book> status;
	
	/**
	 * Constructor for BookIdAlreadyExistsException class
	 * @param responseEntity 
	 * 
	 * @param book id
	 */
	public BookIdAlreadyExistsException(int bkid, ResponseEntity<Book> responseEntity) {
		super();
	this.setBookId(bkid);
	this.setStatus(responseEntity);
	}

	//Methods
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public ResponseEntity<Book> getStatus() {
		return status;
	}

	public void setStatus(ResponseEntity<Book> status) {
		this.status = status;
	}
	
}
