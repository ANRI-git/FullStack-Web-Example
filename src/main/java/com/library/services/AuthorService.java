package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entities.Author;
import com.library.entities.Book;
import com.library.errors.ErrorService;
import com.library.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Transactional
	public void createAuthor(String name) throws ErrorService {
		validateInformation(name);

		Author author = new Author();
		author.setName(name);
		author.setRegistered(true);

		authorRepository.save(author);
	}

	public Author queryAuthor(String id) throws ErrorService {
		if (id == null || id.isEmpty()) {
			throw new ErrorService("The id field is empty!");
		}
		return authorRepository.getById(id);
	}
	
	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public List<Book> queryByBooks(String name) {
		return authorRepository.findByBooks(name);
	}

	public void modifyAuthor(String id, String name) throws ErrorService {
		validateInformation(name);

		Optional<Author> result = authorRepository.findById(id);
		if (result.isPresent()) {
			Author a = result.get();
			a.setName(name);

			authorRepository.save(a);
		} else {
			throw new ErrorService("The author could not be found!");
		}
	}

	private void validateInformation(String name) throws ErrorService {
		if (name == null || name.isEmpty()) {
			throw new ErrorService("The name field is empty!");
		}
	}

	public void registerAuthor(String id) throws ErrorService {
		Optional<Author> result = authorRepository.findById(id);
		if (result.isPresent()) {
			Author a = result.get();
			a.setRegistered(!a.getRegistered());

			authorRepository.save(a);
		} else {
			throw new ErrorService("Author could not be found!");
		}
	}
	
	public void deleteAuthor(String id) throws ErrorService {
		Optional<Author> result = authorRepository.findById(id);
		if(result.isPresent()) {
			authorRepository.delete(result.get());
		} else {
			throw new ErrorService("Author not found!");
		}
	}
}
