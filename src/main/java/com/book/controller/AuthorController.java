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
import com.book.model.Author;

/** The AuthorController class provides methods to get author details.*/
/**
 * @author Ashish.manjhi
 *
 */
@RequestMapping("/authors")
@RestController
public class AuthorController {

	@Autowired
	private AuthorDao authordao;

	/**
	 * This GetMapping method provides the list of all the authors in the database.
	 */
	/**
	 * @return author list
	 */
	@GetMapping
	public ResponseEntity<List<Author>> getAll() {
		return new ResponseEntity<List<Author>>(authordao.findAll(), HttpStatus.OK);
	}

	/** This PostMapping method creates a new author in the database. */
	/**
	 * @param author
	 * @return author size
	 */
	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		return new ResponseEntity<Author>(authordao.save(author), HttpStatus.CREATED);

	}

	/**
	 * This GetMapping method provides detail of an author with a particular id in
	 * the database.
	 */
	/**
	 * @param auid
	 * @return author
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Integer auid) {

		Optional<Author> au = authordao.findById(auid);

		if (!au.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			return new ResponseEntity<Author>(au.get(), HttpStatus.OK);
		}
	}

	/**
	 * This DeleteMapping method delete an authors with a particular id in the
	 * database.
	 */
	/**
	 * @param author id
	 * @return remain author
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable(value = "id") Integer id) {

		Optional<Author> auth = authordao.findById(id);
		if (auth == null) {
			return ResponseEntity.notFound().build();
		}
		authordao.deleteById(id);

		return ResponseEntity.ok().build();
	}

	/**
	 * This PutMapping method update the details of an authors with a particular id
	 * in the database.
	 */
	/**
	 * @param author
	 * @param author id
	 * @return update author details
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Author> createOrUpdateAuthor(@RequestBody Author author, @PathVariable Integer id) {
		Optional<Author> authorOptional = authordao.findById(id);
		if (authorOptional.isPresent()) {
			Author dbAuthor = authorOptional.get();
			dbAuthor.setAge(author.getAge());
			dbAuthor.setAuname(author.getAuname());
			dbAuthor.setAurating(author.getAurating());
			author = authordao.save(dbAuthor);

		} else {
			author = authordao.save(author);
		}
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}
}
