package com.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LibraryController {
	
	@GetMapping("")
	public String index() {
		return "index.html";
	}

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@GetMapping("/register")
	public String register() {
		return "register.html";
	}

	@GetMapping("/logout")
	public String logout() {
		return "logout.html";
	}
}
