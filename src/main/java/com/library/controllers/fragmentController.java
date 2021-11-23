package com.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class fragmentController {
	
	@GetMapping("/fragments")
	public String getHome() {
		return "/fragments/fragments.html";
	}
}
