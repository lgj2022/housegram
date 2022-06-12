package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NewsDTO;
import com.ssafy.happyhouse.model.service.NewsService;


@RestController
@RequestMapping("/happyhouse/api/news")
@CrossOrigin("*")
public class NewsController {
	@Autowired
	NewsService newsService;

	
	@GetMapping(value = "")
	public ResponseEntity<Map<String, Object>> noticelist() throws Exception {
		newsService.init();
		List<NewsDTO> list = newsService.listNews();
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println(list.toString());
		map.put("res", "뉴스 리스트 조회 성공");
		map.put("news", list);
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}

}
