package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.library.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	@Query("SELECT b FROM Book b WHERE b.name = :name")
	public Book findBook(@Param("name") String name);
	
}
