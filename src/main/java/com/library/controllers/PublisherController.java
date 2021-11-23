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
import com.library.services.PublisherService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

	@Autowired
	PublisherService publisherService;

	@GetMapping("")
	public String publisherList(Model model) {
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "/library/publisher/publishersList.html";
	}

	@GetMapping("/add")
	public String registerBook() {
		return "/library/publisher/registerPublisher.html";
	}

	@PostMapping("/save")
	public String registerPublisher(ModelMap modelMap, @RequestParam String name)  throws ErrorService {
		try {
			publisherService.createPublisher(name);
		} catch (ErrorService e) {
			modelMap.put("error", e.getMessage());
			return "/library/author/registerAuthor.html";
		}
		return "redirect:/publisher/";
	}
}
