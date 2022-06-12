package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NoticeDTO;
import com.ssafy.happyhouse.model.ReplyDTO;


public interface ReplyService {
	@Transactional
	void create(ReplyDTO replyDTO) throws Exception;
	@Transactional
	void update(ReplyDTO replyDTO) throws Exception;
	@Transactional
	void delete(ReplyDTO replyDTO) throws Exception;

	List<ReplyDTO> listReply(int articleno) throws Exception;

}
