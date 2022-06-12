package com.ssafy.happyhouse.model.service;

import java.util.List;

public interface RecommendService {
	
	List<String> getRecommendKeyword(String keyword) throws Exception;

}
