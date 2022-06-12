package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeDTO;
import com.ssafy.happyhouse.model.mapper.NoticeMapMapper;


@Service
public class NoticeServiceImpl implements NoticeService {
/*
	private NoticeDAO noticeDAO = NoticeDAOImpl.getNoticeDAO();

	private static NoticeService noticeService = new NoticeServiceImpl();

	private NoticeServiceImpl() {
	}
*/
	@Autowired
	private NoticeMapMapper noticeDAO;
	/*
	public static NoticeService getNoticeService() {
		return noticeService;
	}*/

	@Override
	public void create(NoticeDTO noticeDTO) throws Exception {
		noticeDAO.create(noticeDTO);

	}


	@Override
	public void update(NoticeDTO noticeDTO) throws Exception {
		noticeDAO.update(noticeDTO);

	}

	@Override
	public void delete(NoticeDTO noticeDTO) throws Exception {
		noticeDAO.delete(noticeDTO);

	}

	@Override
	public NoticeDTO detail(String noticeNo) throws Exception {
		return noticeDAO.detail(noticeNo);
	}

	@Override
	public List<NoticeDTO> listNotice(Map<String, Object> map) throws Exception {
		System.out.println("service notice list 여기까지 들어감");
		System.out.println("map start:" +map.get("start"));
		System.out.println("map spp"+map.get("spp"));
		
		List<NoticeDTO> ch = noticeDAO.listNotice(map);
		System.out.println(ch.toString());
		return noticeDAO.listNotice(map);
	}
	/*
	@Override
	public PageNavigation makePageNavigation(String pg, String key, String word) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int currentPage = pg != null ? Integer.parseInt(pg) : 1;
		int naviSize = 5;
		int countPerPage = 10;
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setCountPerPage(countPerPage);
		pageNavigation.setNaviSize(naviSize);

		ListParameterDto listParameterDto = new ListParameterDto();
		listParameterDto.setKey(key == null ? "" : key.trim());
		listParameterDto.setWord(word == null ? "" : word.trim());

		int totalCount = noticeDAO.getTotalCount(listParameterDto);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / countPerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		pageNavigation.setStartRange(currentPage <= naviSize);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}*/


	@Override
	public List<NoticeDTO> searchNotice(Map<String, String> map) throws SQLException {
		return noticeDAO.searchNotice(map);
	}


	@Override
	public int getTotalCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getTotalCount(map);
	}
	

}
