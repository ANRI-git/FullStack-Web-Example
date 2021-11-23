package com.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.errors.ErrorService;
import com.library.services.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@GetMapping("")
	public String authorList(Model model) {
		model.addAttribute("authors", authorService.findAllAuthors());
		return "/library/author/authorsList.html";
	}
	
	@GetMapping("/add")
	public String addAuthor() {
		return "/library/author/registerAuthor.html";
	}
	
	@PostMapping("/save")
	public String registerAuthor(ModelMap modelMap, @RequestParam String name) throws ErrorService {
		try {
			authorService.createAuthor(name);
		} catch (ErrorService e) {
			modelMap.put("error", e.getMessage());
			return "/library/author/registerAuthor.html";
		}
		return "redirect:/author/";
	}
}
