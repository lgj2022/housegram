package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NoticeDTO;


public interface NoticeService {
	@Transactional
	void create(NoticeDTO noticeDTO) throws Exception;

	@Transactional
	void update(NoticeDTO noticeDTO)throws Exception;
	@Transactional
	void delete(NoticeDTO noticeDTO) throws Exception;

	NoticeDTO detail(String noticeNo) throws Exception;
	
	List<NoticeDTO> listNotice(Map<String, Object> map) throws Exception;

	List<NoticeDTO> searchNotice(Map<String, String> map) throws SQLException;
	
	int getTotalCount(Map<String, Object> map) throws Exception;
}
