package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entities.Book;
import com.library.enums.Genres;
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

	@Transactional
	public void createBook(String title, Long isbn, Integer year, String genre , Integer copies, String author_id, String publisher_id)
			throws ErrorService {
		validateInformation(title, isbn, year, genre, copies, author_id, publisher_id);

		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setYear(year);
		book.setGenre(Genres.valueOf(genre));
		book.setCopies(copies);
		book.setBorowedCopies(0);
		book.setRemainingCopies(copies);
		book.setAuthor(authorServ.queryAuthor(author_id));
		book.setPublisher(publisherServ.queryPublisher(publisher_id));
		book.setRegistered(true);

		bookRepository.save(book);
	}

	public Book queryBook(String id) throws ErrorService {
		return bookRepository.findBook(id);
	}

	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public void modifyBook(String id, Long isbn, String title, Integer year, String genre, Integer copies, String author_id,
			String publisher_id) throws ErrorService {
		validateInformation(title, isbn, year, genre, copies, author_id, publisher_id);

		Optional<Book> result = bookRepository.findById(id);
		if (result.isPresent()) {
			Book b = result.get();
			b.setTitle(title);
			b.setYear(year);
			b.setGenre(Genres.valueOf(genre));
			b.setCopies(copies);
			b.setAuthor(authorServ.queryAuthor(author_id));
			b.setPublisher(publisherServ.queryPublisher(publisher_id));
			bookRepository.save(b);
		} else {
			throw new ErrorService("The book could not be found!");
		}
	}

	private void validateInformation(String title, Long isbn, Integer year, String genre, Integer copies, String author_id,
			String publisher_id) throws ErrorService {
		if (title == null || title.isEmpty()) {
			throw new ErrorService("The TITLE field is empty!");
		}
		if (isbn == null || isbn == 0) {
			throw new ErrorService("The ISBN field is empty!");
		}
		if (year == null || year == 0) {
			throw new ErrorService("The YEAR field is empty!");
		}
		if (genre == null || genre.isEmpty()) {
			throw new ErrorService("The GENRE field is empty!");
		}
		if (copies == null || copies == 0) {
			throw new ErrorService("The COPIES field is empty!");
		}
		if (author_id == null || author_id.isEmpty()) {
			throw new ErrorService("The AUTHOR field is empty!");
		}
		if (publisher_id == null || publisher_id.isEmpty()) {
			throw new ErrorService("The PUBLISHER field is empty!");
		}
	}

	public void registerBook(String id) {
		Book b = bookRepository.getById(id);
		b.setRegistered(!b.getRegistered());
		bookRepository.save(b);
	}

	public void deleteBook(String id) {
		bookRepository.delete(bookRepository.getById(id));
	}
}
