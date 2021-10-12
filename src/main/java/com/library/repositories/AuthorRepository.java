package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

	@Query("SELECT a FROM Author a WHERE a.name = :name")
	public Author findAuthor(@Param("name") String name);
}
