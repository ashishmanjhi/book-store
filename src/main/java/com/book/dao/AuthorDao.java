package com.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.Author;

/**
 * 
 * @author Ashish.manjhi
 *
 *This {@link AuthorDao} interface is the Repository of {@link AuthorController} class.
 */
public interface AuthorDao extends JpaRepository<Author, Integer>{

}
