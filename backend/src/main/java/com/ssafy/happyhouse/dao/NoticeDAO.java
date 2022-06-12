package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.ListParameterDto;
import com.ssafy.happyhouse.model.NoticeDTO;



public interface NoticeDAO {
	void create(NoticeDTO noticeDTO) throws SQLException;

	ArrayList<NoticeDTO> list() throws SQLException;

	void update(NoticeDTO noticeDTO) throws SQLException;

	void delete(NoticeDTO noticeDTO) throws SQLException;

	NoticeDTO detail(String noticeNo) throws SQLException;
	
	int getTotalCount(ListParameterDto listParameterDto) throws SQLException;
	
	List<NoticeDTO> listNotice(ListParameterDto listParameterDto) throws SQLException;
}
