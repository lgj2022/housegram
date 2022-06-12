package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.RecommendService;

@RestController()
@RequestMapping("/recommend")
@CrossOrigin("*")
public class RecommendController {
	private final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private RecommendService recommendService;
	
	
	@GetMapping("keyword/{keyword}")
	public ResponseEntity<List<String>> getRecommendKeyword(@PathVariable("keyword") String keyword) throws Exception{
		return new ResponseEntity<List<String>>(recommendService.getRecommendKeyword(keyword),HttpStatus.OK);
		
	}
	

}
