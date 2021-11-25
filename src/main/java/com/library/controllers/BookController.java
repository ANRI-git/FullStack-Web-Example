package com.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.entities.Book;
import com.library.errors.ErrorService;
import com.library.services.AuthorService;
import com.library.services.BookService;
import com.library.services.PublisherService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private PublisherService publisherService;

	@GetMapping("")
	public String booksList(Model model) {
		model.addAttribute("books", bookService.findAllBooks());
		return "/library/book/booksList";
	}

	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "/library/book/registerBook";
	}

	@PostMapping("/save")
	public String saveBook(Model model, ModelMap modelMap,@RequestParam String title, @RequestParam(defaultValue="0") Long isbn,
			@RequestParam(defaultValue="0") Integer year,@RequestParam String genre,@RequestParam(defaultValue="0") Integer copies,@RequestParam String author,@RequestParam String publisher)
			throws ErrorService {
		try {
			bookService.createBook(title, isbn, year, genre, copies, author, publisher);
		} catch (ErrorService e) {
			modelMap.put("title", title);
			modelMap.put("isbn", isbn);
			modelMap.put("year", year);
			modelMap.put("copies", copies);
			modelMap.put("author", author);
			modelMap.put("publisher", publisher);
			modelMap.put("error", e.getMessage());
			model.addAttribute("authors", authorService.findAllAuthors());
			model.addAttribute("publishers", publisherService.findAllPublishers());
			return "/library/book/registerBook";
		}
		model.addAttribute("books", bookService.findAllBooks());
		modelMap.put("success", "true");
		modelMap.put("message", "Book added successfully!");
		return "/library/book/booksList";
	}
	
	@GetMapping("/modify")
	public String modifyBook(ModelMap modelMap, @RequestParam String id) throws ErrorService {
		Book b = bookService.queryBook(id);
		modelMap.put("title", b.getTitle());
		modelMap.put("isbn", b.getIsbn());
		modelMap.put("year", b.getYear());
		modelMap.put("genre", b.getGenre());
		modelMap.put("copies", b.getCopies());
		modelMap.put("author", b.getAuthor().getId());
		modelMap.put("publisher", b.getPublisher().getId());
		return "/library/book/registerBook";
	}
	
	@PostMapping("/delete")
	public String deleteBook(@RequestParam String id) {
		bookService.deleteBook(id);
		return "redirect:/book";
	}
	
	@PostMapping("/register")
	public String registerBook(@RequestParam String id) {
		bookService.registerBook(id);
		return "redirect:/book";
	}
}
