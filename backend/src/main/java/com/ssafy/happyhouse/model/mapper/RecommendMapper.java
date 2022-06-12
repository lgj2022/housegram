package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendMapper {
	List<String> getRecommendKeyword(String keyword) throws SQLException;
}


