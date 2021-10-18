package com.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entities.Book;
import com.library.errors.ErrorService;
import com.library.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	AuthorService authorServ;
	@Autowired
	PublisherService publisherServ;

	public void createBook(Long isbn, String title, Integer year, Integer copies, String author, String publisher)
			throws ErrorService {
		if (isbn == null || isbn == 0 || title == null || title.isEmpty() || year == null || year == 0 || copies == null
				|| copies == 0 || author == null || author.isEmpty() || publisher == null || publisher.isEmpty()) {
			throw new ErrorService("Some fields are empty!");
		}
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setYear(year);
		book.setCopies(copies);
		book.setBorowedCopies(0);
		book.setRemainingCopies(copies);
		authorServ.createAuthor(author);
		publisherServ.createPublisher(publisher);
		book.setRegistered(true);

		bookRepository.save(book);
	}

	public Book queryBook(String id) throws ErrorService {
		if (id == null || id.isEmpty()) {
			throw new ErrorService("The id field is empty!");
		}
		return bookRepository.findBook(id);
	}

	public void modifyBook(String id, String title, Integer year, Integer copies, String author_id, String publisher_id)
			throws ErrorService {
		validateInformation(title, year, copies, author_id, publisher_id);

		Optional<Book> result = bookRepository.findById(id);
		if (result.isPresent()) {
			Book b = result.get();
			b.setTitle(title);
			b.setYear(year);
			b.setCopies(copies);
			b.setAuthor(authorServ.queryAuthor(author_id));
			b.setPublisher(publisherServ.queryPublisher(publisher_id));
			bookRepository.save(b);
		} else {
			throw new ErrorService("The book could not be found!");
		}
	}

	private void validateInformation(String title, Integer year, Integer copies, String author_id, String publisher_id)
			throws ErrorService {
		if (title == null || title.isEmpty()) {
			throw new ErrorService("The title field is empty!");
		}
		if (year == null || year == 0) {
			throw new ErrorService("The year field is empty!");
		}
		if (copies == null || copies == 0) {
			throw new ErrorService("The copies field is empty!");
		}
		if (author_id == null || author_id.isEmpty()) {
			throw new ErrorService("The copies field is empty!");
		}
		if (publisher_id == null || publisher_id.isEmpty()) {
			throw new ErrorService("The copies field is empty!");
		}
	}

	public void registerBook(String id) throws ErrorService {
		Optional<Book> result = bookRepository.findById(id);
		if (result.isPresent()) {
			Book b = result.get();
			b.setRegistered(!b.getRegistered());

			bookRepository.save(b);
		} else {
			throw new ErrorService("Book could not be found!");
		}
	}

	public void deleteBook(String id) throws ErrorService {
		Optional<Book> result = bookRepository.findById(id);
		if(result.isPresent()) {
			bookRepository.delete(result.get());
		} else {
			throw new ErrorService("Book not found!");
		}
	}
}
