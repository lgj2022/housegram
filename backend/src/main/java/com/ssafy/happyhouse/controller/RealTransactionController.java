package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/real")
/* @CrossOrigin("*") */
public class RealTransactionController {

	@GetMapping("info")
	public String info() {
		return "real/real";
	}
	@PostMapping("info")
	public String info(String keyword, Model model) {
		/*
		 * ModelAndView mav = new ModelAndView(); mav.addObject("keyword", keyword);
		 * mav.setViewName("real/real"); return mav;
		 * 
		 */

		model.addAttribute("keyword", keyword);
		return "real/real";
	}
}
