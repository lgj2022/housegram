package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDTO;


@Mapper
public interface NoticeMapMapper {
	void create(NoticeDTO noticeDTO) throws SQLException;

	void update(NoticeDTO noticeDTO) throws SQLException;

	void delete(NoticeDTO noticeDTO) throws SQLException;

	NoticeDTO detail(String noticeNo) throws SQLException;
	
	List<NoticeDTO> listNotice(Map<String, Object> map) throws SQLException;
	
	List<NoticeDTO> searchNotice(Map<String, String> map) throws SQLException;

	int getTotalCount(Map<String, Object> map)  throws SQLException;
}
