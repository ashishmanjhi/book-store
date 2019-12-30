package com.book.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.Book;

/**
 * 
 * @author Ashish.manjhi
 *
 *This {@link BookDao} interface is the Repository of {@link BookController} class.
 */
public interface BookDao extends JpaRepository<Book, Integer>{

	/**These are some custom made RESTAPIs method used for performing 
	 * different search operation on book database.*/
	
	
	/*
	 * Find all the Books of a particular Author by age.
	 */
	public List<Book> findByAuthorAge(Integer age);

	/*
	 * Find all the Books of a particular Author by author name.
	 */
	public List<Book> findByAuthorAuname(String auname);

	/*
	 * Find all the Books of a particular Author by author id.
	 */
	public List<Book> findByAuthorAuid(Integer auid);

	/*
	 * Find all the Books of a particular Author by author rating.
	 */
	public List<Book> findByAuthorAurating(Float rating);

	/*
	 * Find all the Books with a particular book rating.
	 */
	public List<Book> findByBkrating(Float bkrating);
	
	/*
	 * Find all the Books of a particular Author by author id.
	 */
	public List<Book> findByPriceLessThanAndAuthorAuratingGreaterThan(Integer price,Float rating);

}
