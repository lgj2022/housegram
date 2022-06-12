package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.mapper.RecommendMapper;

@Service
public class RecommendServiceImpl implements RecommendService{

	@Autowired
	private RecommendMapper recommendMapper;

	@Override
	public List<String> getRecommendKeyword(String keyword) throws Exception {
		return recommendMapper.getRecommendKeyword(keyword);
	}
}
