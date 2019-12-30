package com.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.ToString;


/**
 * @author Ashish.manjhi
 *
 *This {@link Book} class represents detail of the Book.
 *
 */
@Entity
@Table(name="book", catalog = "book2")
@ToString
public class Book {
	
	//Book Id
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "bkid")
	private int bkid;
	
	//Book Name
	@NotNull
	@Column(name = "bkname")
	private String bkname;
	
	//Book Rating
	@NotNull
	@Column(name = "bkrating")
	private float bkrating;
	
	//Book price
	@NotNull
	@Column(name = "price")
	private int price;
	
	//Author Id
	@ManyToOne
	private Author author;
	
	
	// ----------------
    // - CONSTRUCTORS -
    // ----------------

	/**
	 * Default Constructor for Book
	 */
	public Book() {}
	
	
/**
 * Parameterized Constructor for Book
 * 
 * @param book id
 * @param book name
 * @param book rating
 * @param book price
 * @param author
 */
public Book(int bkid, @NotNull String bkname, @NotNull float bkrating, @NotNull int price, Author author) {
		super();
		this.bkid = bkid;
		this.bkname = bkname;
		this.bkrating = bkrating;
		this.price = price;
		this.author = author;
	}


// -----------
// - METHODS -
// -----------

	/**
	 * @return Book Id
	 */
	public int getBkid() {
		return bkid;
	}
	
	/**
	 * @param Book Id
	 * @return Book Id
	 */
	public Book setBkid(int bk_id) {
		this.bkid = bk_id;
		return this;
	}
	
	/**
	 * @return Book Price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * @param Book Price
	 * @return Book Price
	 */
	public Book setPrice(int price) {
		this.price = price;
		return this;
	}
	
	/**
	 * @return Book Name
	 */
	public String getBkname() {
		return bkname;
	}
	
	/**
	 * @param Book Name
	 * @return Book Name
	 */
	public Book setBkname(String bk_name) {
		this.bkname = bk_name;
		return this;
	}
	
	/**
	 * @return Book Rating
	 */
	public float getBkrating() {
		return bkrating;
	}
	
	/**
	 * @param  Book Rating
	 * @return Book Rating
	 */
	public Book setBkrating(float bk_rating) {
		this.bkrating = bk_rating;
		return this;
	}
	
	/**
	 * @return author
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param author
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	 // -------------
    // - TO STRING -
    // -------------
	
	/**
	 *
	 */
	@Override
	public String toString() {
		return "Book [bk_id=" + bkid + ", bk_name=" + bkname + ", bk_rating=" + bkrating + ", price=" + price
				+ "]";
	}
}
