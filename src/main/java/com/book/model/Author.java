package com.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.ToString;


/**
 * @author Ashish.manjhi
 *
 */
@Entity
@Table(name="author", catalog = "book2")
@ToString
public class Author {

	/** members of author  model.*/

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "auid")
	private int auid;

	@NotNull
	@Column(name = "auname")
	private String auname;

	@NotNull
	@Column(name = "age")
	private int age;

	@NotNull
	@Column(name = "aurating")
	private float aurating;


	//Default Constructor
	public Author() {}


	//Parameterized Constructor
	/**
	 * @param auid
	 * @param auname
	 * @param age
	 * @param aurating
	 */
	public Author(int auid, @NotNull String auname, @NotNull int age, @NotNull float aurating) {
		super();
		this.auid = auid;
		this.auname = auname;
		this.age = age;
		this.aurating = aurating;
	}


	/** Setters and Getters  .*/

	/**
	 * @return age of author
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 * @return age of author
	 */
	public Author setAge(int age) {
		this.age = age;
		return this;
	}

	/**
	 * @return name of author
	 */
	public String getAuname() {
		return auname;
	}

	/**
	 * @param name	
	 * @return name of author
	 */
	public Author setAuname(String au_name) {
		this.auname = au_name;
		return this;
	}

	/** 
	 * @return rating of author
	 */
	public float getAurating() {
		return aurating;
	}

	/**
	 * @param author rating
	 * @return rating of author
	 */
	public Author setAurating(float au_rating) {
		this.aurating = au_rating;
		return this;
	}

	/**
	 * @return id of author
	 */
	public int getAuid() {
		return auid;
	}

	/**
	 * @param author id
	 * @return id of author
	 */
	public Author setAuid(int au_id) {
		this.auid = au_id;
		return this;
	}	

}
