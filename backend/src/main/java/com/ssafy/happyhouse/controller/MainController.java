package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.happyhouse.model.service.NewsService;

@Controller
public class MainController {
	@Autowired
	NewsService newsService;

	@GetMapping("/")
	public String index() throws Exception {
		newsService.init();
		return "index";
	}
}
