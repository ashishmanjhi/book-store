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
import com.book.model.Book;

/** The BookController class provides methods to get book details. */
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookDao dao;

	/** This PostMapping method creates a new book in the database. */
	/**
	 * @param books
	 * @return book size
	 */
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(dao.save(book), HttpStatus.CREATED);
	}

	/**
	 * This GetMapping method provides the list of all the books in the database.
	 */
	/**
	 * @return Book list
	 */
	@GetMapping
	public ResponseEntity<List<Book>> getAll() {
		return new ResponseEntity<List<Book>>(dao.findAll(), HttpStatus.OK);
	}

	/**
	 * This GetMapping method provides detail of an book with a particular id in the
	 * database.
	 */
	/**
	 * @param book id
	 * @return book
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer bkid) {

		Optional<Book> bk = dao.findById(bkid);

		if (!bk.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			return new ResponseEntity<Book>(bk.get(), HttpStatus.OK);
		}
	}

	/**
	 * This GetMapping method provides detail of an book with a particular book
	 * rating in the database.
	 */
	/**
	 * @param book rating
	 * @return book
	 */
	@GetMapping("/bkrating/{rating}")
	public ResponseEntity<List<Book>> getBookByRating(@PathVariable(value = "rating") Float bkrating) {
		return new ResponseEntity<List<Book>>(dao.findByBkrating((Float) bkrating), HttpStatus.OK);
	}

	/**
	 * This DeleteMapping method delete an book with a particular id in the
	 * database.
	 */
	/**
	 * @param book id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") Integer bkid) {

		Optional<Book> book = dao.findById(bkid);
		if (!book.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(bkid);

		return ResponseEntity.ok().build();
	}

	/**
	 * This PutMapping method update the details of an book with a particular id in
	 * the database.
	 */
	/**
	 * @param book id
	 * @param book
	 * @return updated book
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Integer bkid, @RequestBody Book book) {
		Optional<Book> dbBookOptional = dao.findById(bkid);

		if (dbBookOptional.isPresent()) {
			Book dbBook = dbBookOptional.get();
			dbBook.setBkname(book.getBkname());
			dbBook.setBkrating(book.getBkrating());
			dbBook.setPrice(book.getPrice());
			dbBook.setAuthor(book.getAuthor());
			book = dao.save(dbBook);
		} else {
			book = dao.save(book);
		}
		Book updateBook = dao.save(book);
		return ResponseEntity.ok().body(updateBook);
	}

	/**
	 * This GetMapping method provides detail of an book with book price less than
	 * and author rating greater than in the database.
	 */
	/**
	 * @param book   price less than
	 * @param author rating greater than
	 * @return book
	 */
	@GetMapping("/price/{price}/authorRating/{aurating}")
	public ResponseEntity<List<Book>> getBookPriceLessThanAndAuthorRatingGreaterThan(
			@PathVariable("price") Integer price, @PathVariable("aurating") Float aurating) {
		List<Book> books = dao.findByPriceLessThanAndAuthorAuratingGreaterThan(price, aurating);
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
}
