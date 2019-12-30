package com.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.dao.BookDao;
import com.book.exception.BookIdAlreadyExistsException;
import com.book.exception.BookNotFoundException;
import com.book.model.Book;

/** This {@link BookController} class to get book details. */

@RequestMapping("/books")
@RestController
public class BookController {

	/*
	 * Book Repository
	 */
	@Autowired
	private BookDao dao;

	/** PostMapping creates a new book in the database. */
	/**
	 * @param books details 
	 * @return book status
	 * @throws BookIdAlreadyExistsException 
	 */
	@PostMapping()
	public ResponseEntity<Book> createBook(@RequestBody Book book) throws BookIdAlreadyExistsException {
		/*
		 * Condition if true i.e a book with same id is already present in the database then,
		 *  an exception will be thrown with response code and book id. 
		 *  Else the book will be created.
		 */
		if(dao.findById(book.getBkid()).isPresent()) {
			throw new BookIdAlreadyExistsException(book.getBkid(), new ResponseEntity<Book>(HttpStatus.NOT_ACCEPTABLE));
		}

		return new ResponseEntity<Book>(dao.save(book), HttpStatus.CREATED);
	}

	/**
	 * GetMapping  provides the list of all the books in the database.
	 */
	/**
	 * @return List of all books
	 */
	@GetMapping()
	public ResponseEntity<List<Book>> getAll() {
		return new ResponseEntity<List<Book>>(dao.findAll(), HttpStatus.OK);
	}

	/**
	 * GetMapping provides detail of an book with a particular id in the
	 * database.
	 */
	/**
	 * @param Book id
	 * @return Book with book id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer bkid) throws BookNotFoundException {

		Optional<Book> book = dao.findById(bkid);
		/*
		 *  If the book with inputed book id is not present in the database an exception will be thrown with a message and response code. 
		 *  If successful  book detail of the inputed book id will be shown in the screen.
		 */
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book Not Found",ResponseEntity.notFound().build());
		} else {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		}
	}

	/**
	 * GetMapping provides detail of an book with a particular book
	 * rating in the database.
	 */
	/**
	 * @param book rating
	 * @return book with book rating
	 */
	@GetMapping("/bkrating/{rating}")
	public ResponseEntity<List<Book>> getBookByRating(@PathVariable(value = "rating") Float bkrating) {
		List<Book> book=dao.findByBkrating((Float) bkrating);
		/*
		 * If condition true, then BookNotFoundException is thrown,
		 * Else the given book with the particular details is shown.
		 */
		if (book.isEmpty()) {
			throw new BookNotFoundException("Book Not Found",ResponseEntity.notFound().build());
		} else {
			return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
		}}

	/**
	 * DeleteMapping deletes a book with a particular id in the
	 * database.
	 */
	/**
	 * @param book id
	 * @return status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") Integer bkid) {

		Optional<Book> book = dao.findById(bkid);
		/*
		 * If condition true, then BookNotFoundException is thrown,
		 * Else the given book with the particular details is deleted from the database.
		 */
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book Not Found",ResponseEntity.notFound().build());
		}
		dao.deleteById(bkid);

		return ResponseEntity.ok().build();
	}

	/**
	 * PutMapping updates the details of an book with a particular id in
	 * the database.
	 */
	/**
	 * @param book id
	 * @return book with updated details.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Integer bkid, @RequestBody Book book) {
		Optional<Book> dbBookOptional = dao.findById(bkid);

		/*
		 * If condition is true then the detail of the book are updated,
		 * Else BookNotFoundException is thrown.
		 */
		if (dbBookOptional.isPresent()) {
			Book dbBook = dbBookOptional.get();
			dbBook.setBkname(book.getBkname());
			dbBook.setBkrating(book.getBkrating());
			dbBook.setPrice(book.getPrice());
			dbBook.setAuthor(book.getAuthor());
			book = dao.save(dbBook);
		} else {
			throw new BookNotFoundException("Book Not Found",ResponseEntity.notFound().build());

		}

		// Updated Book
		Book updateBook = dao.save(book);
		return ResponseEntity.ok().body(updateBook);
	}

	/**
	 * GetMapping  provides detail of an book with book price less than
	 * and author rating greater than in the database.
	 * 
	 * eg:- book price than 1800 and author rating greater than 3.
	 * 
	 */
	/**
	 * @param book price less than
	 * @param author rating greater than
	 * @return book with book price less than and author rating greater than
	 */
	@GetMapping("/price/{price}/authorRating/{aurating}")
	public ResponseEntity<List<Book>> getBookPriceLessThanAndAuthorRatingGreaterThan(
			@PathVariable("price") Integer price, @PathVariable("aurating") Float aurating) {

		List<Book> books = dao.findByPriceLessThanAndAuthorAuratingGreaterThan(price, aurating);
		/*
		 * If condition true, then BookNotFoundException is thrown,
		 * Else the given book with the particular details is shown.
		 */
		if(books.isEmpty()) {
			throw new BookNotFoundException("Book Not Found",ResponseEntity.notFound().build());
		}

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}


}
