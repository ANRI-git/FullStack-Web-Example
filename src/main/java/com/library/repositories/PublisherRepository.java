package com.library.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entities.Book;
import com.library.entities.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {

	@Query("SELECT p FROM Publisher p WHERE p.name = :name")
	public Optional<Publisher> findPublisher(@Param("name") String name);

	@Query("SELECT b FROM Book b WHERE b.author = :name")
	public List<Book> findBooks(@Param("name") String name);
}
