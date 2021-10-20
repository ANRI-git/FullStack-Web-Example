package com.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

	@GetMapping("/listed-books")
	public String booksList() {
		return "/library/book/booksList.html";
	}

	@GetMapping("/book-registration")
	public String registerBook() {
		return "/library/book/registerBook.html";
	}
	
	@PostMapping("/addingBook")
	public String registerBook(String title, Integer sbn, Integer year, Integer copies, String author, String publisher) {
		return "/library/book/registerBook.html";
	}
}
