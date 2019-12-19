package com.book.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.Book;

// Book Repository
public interface BookDao extends JpaRepository<Book, Integer>{

	/**These are the customer made method used for performing different search operation on book database.*/
	
	public List<Book> findByAuthorAge(Integer age);

	public List<Book> findByAuthorAuname(String auname);

	public List<Book> findByAuthorAuid(Integer auid);

	public List<Book> findByAuthorAurating(Float rating);

	public List<Book> findByBkrating(Float bkrating);
	
	public List<Book> findByPriceLessThanAndAuthorAuratingGreaterThan(Integer price,Float rating);

}
