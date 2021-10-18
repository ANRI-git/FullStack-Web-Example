package com.library.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entities.Author;
import com.library.entities.Book;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

	@Query("SELECT a FROM Author a WHERE a.name = :name")
	public Optional<Author> findAuthor(@Param("name") String name);

	@Query("SELECT b FROM Book b WHERE b.author = :id")
	public List<Book> findByBooks(@Param("id") String id);
}
