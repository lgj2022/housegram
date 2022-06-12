package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NewsDTO;


@Mapper
public interface NewsMapMapper {
	NewsDTO checkDate() throws SQLException;
	void delete() throws Exception;
	void create(NewsDTO newsDTO) throws SQLException;
	List<NewsDTO> listNews() throws SQLException;
}
