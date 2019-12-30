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

import com.book.dao.AuthorDao;
import com.book.exception.AuthorNotFoundException;
import com.book.model.Author;

/** This {@link AuthorController} class provides methods to get author details.*/
/**
 * @author Ashish.manjhi
 *
 */
@RequestMapping("/authors")
@RestController
public class AuthorController {

	/*
	 * Author Repository 
	 */
	@Autowired
	private AuthorDao authordao;

	/**
	 * GetMapping provides the list of all the authors in the database.
	 * 
	 * @return List of all Authors
	 */
	@GetMapping
	public ResponseEntity<List<Author>> getAll() {
		return new ResponseEntity<List<Author>>(authordao.findAll(), HttpStatus.OK);
	}

	/** PostMapping  creates a new author in the database. 
	 * 
	 * @param Author details
	 * @return Author Status
	 */
	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		return new ResponseEntity<Author>(authordao.save(author), HttpStatus.CREATED);

	}

	/**
	 * GetMapping provides detail of an author with a particular id in
	 * the database.
	 */
	/**
	 * @param Author ID
	 * @return Author with the inputed Author ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Integer id) {

		Optional<Author> author = authordao.findById(id);

		/*
		 * If condition true, then AuthorNotFoundException is thrown,
		 * Else the given author with the particular details is shown.
		 */
		if (!author.isPresent()) {
			throw new AuthorNotFoundException("Author Not Found",ResponseEntity.notFound().build());
		} else {
			return new ResponseEntity<Author>(author.get(), HttpStatus.OK);
		}
	}

	/**
	 * DeleteMapping deletes an author with a particular id in the
	 * database.
	 *
	 * @param author id
	 * @return status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable(value = "id") Integer id) {

		Optional<Author> auth = authordao.findById(id);
		/*
		 * If condition true, then AuthorNotFoundException is thrown,
		 * Else the given author with the particular details is Deleted from the database.
		 */
		if (!auth.isPresent()) {
			throw new AuthorNotFoundException("Author Not Found",ResponseEntity.notFound().build());		
		}
		authordao.deleteById(id);

		return ResponseEntity.ok().build();
	}

	/**
	 * PutMapping updates the details of an author with a particular id
	 * in the database.
	 * 
	 * 
	 * @param author id
	 * @return author with updated details
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Author> createOrUpdateAuthor(@RequestBody Author author, @PathVariable Integer id) {
		Optional<Author> authorOptional = authordao.findById(id);
		/*
		 * If condition is true then the detail of the author are updated,
		 * Else AuthorNotFoundException is thrown.
		 */
		if (authorOptional.isPresent()) {
			Author dbAuthor = authorOptional.get();
			dbAuthor.setAge(author.getAge());
			dbAuthor.setAuname(author.getAuname());
			dbAuthor.setAurating(author.getAurating());
			author = authordao.save(dbAuthor);

		} else {
			throw new AuthorNotFoundException("Author Not Found",ResponseEntity.notFound().build());		
		}
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}
}
