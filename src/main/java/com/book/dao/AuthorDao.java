package com.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.Author;

// Author Repository
public interface AuthorDao extends JpaRepository<Author, Integer>{

}
