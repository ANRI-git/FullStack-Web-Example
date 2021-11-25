package com.library.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.library.enums.Genres;

@Entity
public class Book {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	private Long isbn;
	private String title;
	private Integer year;
	private Genres genre;
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
	
	public Book(String id, Long isbn, String titulo, Integer year, Genres genre, Integer copies, Integer borowedCopies,
			Integer remainingCopies, Boolean registered, Author author, Publisher publisher) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = titulo;
		this.year = year;
		this.genre = genre;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Genres getGenre() {
		return genre;
	}
	
	public void setGenre(Genres genre) {
		this.genre = genre;
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
		return Objects.hash(title);
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
		return Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", year=" + year + ",genre=" + genre + ", copies=" + copies
				+ ", borowedCopies=" + borowedCopies + ", remainingCopies=" + remainingCopies + ", registered="
				+ registered + ", author=" + author + ", publisher=" + publisher + "]";
	}

}
