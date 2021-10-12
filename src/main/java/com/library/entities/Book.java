package com.library.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	private String id;
	private Long isbn;
	private String titulo;
	private Integer year;
	private Integer copies;
	private Integer borowedCopies;
	private Integer remainingCopies;
	private Boolean registered;
	@ManyToOne
	private Author author;
	@ManyToOne
	private Publisher publisher;
	
	public Book() {
		super();
	}
	
	public Book(String id, Long isbn, String titulo, Integer year, Integer copies, Integer borowedCopies,
			Integer remainingCopies, Boolean registered, Author author, Publisher publisher) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
		this.year = year;
		this.copies = copies;
		this.borowedCopies = borowedCopies;
		this.remainingCopies = remainingCopies;
		this.registered = registered;
		this.author = author;
		this.publisher = publisher;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public Integer getBorowedCopies() {
		return borowedCopies;
	}

	public void setBorowedCopies(Integer borowedCopies) {
		this.borowedCopies = borowedCopies;
	}

	public Integer getRemainingCopies() {
		return remainingCopies;
	}

	public void setRemainingCopies(Integer remainingCopies) {
		this.remainingCopies = remainingCopies;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public int hashCode() {
		return Objects.hash(titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", year=" + year + ", copies=" + copies
				+ ", borowedCopies=" + borowedCopies + ", remainingCopies=" + remainingCopies + ", registered="
				+ registered + ", author=" + author + ", publisher=" + publisher + "]";
	}

}
