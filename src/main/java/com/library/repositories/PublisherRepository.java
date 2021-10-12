package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entities.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {

	@Query("SELECT p FROM Publisher p WHERE p.name = :name")
	public Publisher findPublisher(@Param("name") String name);
}
