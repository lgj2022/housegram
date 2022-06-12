package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NewsDTO;


public interface NewsService {
	
	void init() throws Exception;
	NewsDTO checkDate() throws Exception;
	void delete() throws Exception;
	void create(NewsDTO newsDTO) throws Exception;
	List<NewsDTO> listNews() throws Exception;
}
