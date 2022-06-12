package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.ReplyDTO;
import com.ssafy.happyhouse.model.mapper.ReplyMapMapper;


@Service
public class ReplyServiceImpl implements ReplyService {
/*
	private NoticeDAO noticeDAO = NoticeDAOImpl.getNoticeDAO();

	private static NoticeService noticeService = new NoticeServiceImpl();

	private NoticeServiceImpl() {
	}
*/
	@Autowired
	private ReplyMapMapper replyDAO;
	/*
	public static NoticeService getNoticeService() {
		return noticeService;
	}*/

	@Override
	public void create(ReplyDTO replyDTO) throws Exception {
		replyDAO.create(replyDTO);
	}


	@Override
	public void update(ReplyDTO replyDTO) throws Exception {
		replyDAO.update(replyDTO);

	}

	@Override
	public void delete(ReplyDTO replyDTO) throws Exception {
		replyDAO.delete(replyDTO);
	}


	@Override
	public List<ReplyDTO> listReply(int articleno) throws Exception {
		return replyDAO.listReply(articleno);
	}



}
