package com.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	@Query("SELECT b FROM Book b WHERE b.id = :id")
	public Book findBook(@Param("id") String id);

	@Query("SELECT b FROM Book b WHERE b.title = :title")
	public List<Book> findAllBooks(@Param("title") String title);
}
