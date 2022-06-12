//package com.ssafy.happyhouse.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ssafy.happyhouse.model.NoticeDTO;
//
//
//public class NoticeDAOImpl implements NoticeDAO {
//	private static NoticeDAO noticeDAO = new NoticeDAOImpl();
//
//	private NoticeDAOImpl() {
//	}
//
//	public static NoticeDAO getNoticeDAO() {
//		return noticeDAO;
//	}
//
//	@Override
//	public void create(NoticeDTO noticeDTO) throws SQLException {
//		Connection conn = DBUtil.getConnection();
//		StringBuilder createNotice = new StringBuilder();
//		createNotice.append("insert into notice (userId, author, title, content, createdAt)\n");
//		createNotice.append("values (?, ?, ?, ?,now())");
//		PreparedStatement pstmt = conn.prepareStatement(createNotice.toString());
//		pstmt.setString(1, noticeDTO.getUserId());
//		pstmt.setString(2, noticeDTO.getAuthor());
//		pstmt.setString(3, noticeDTO.getTitle());
//		pstmt.setString(4, noticeDTO.getContent());
//		pstmt.executeUpdate();
//		DBUtil.close(pstmt, conn);
//
//	}
//
//	@Override
//	public ArrayList<NoticeDTO> list() throws SQLException {
//		ArrayList<NoticeDTO> noticeList = new ArrayList<>();
//		Connection conn = DBUtil.getConnection();
//		StringBuilder listNotice = new StringBuilder();
//		listNotice.append("select noticeNo, userId, author, title, content, createdAt from notice \n");
//		PreparedStatement pstmt = conn.prepareStatement(listNotice.toString());
//		ResultSet rs = pstmt.executeQuery();
//		while (rs.next()) {
//			NoticeDTO noticeDTO = new NoticeDTO();
//			noticeDTO.setNoticeNo(rs.getInt("noticeNo"));
//			noticeDTO.setUserId(rs.getString("userId"));
//			noticeDTO.setAuthor(rs.getString("author"));
//			noticeDTO.setTitle(rs.getString("title"));
//			noticeDTO.setContent(rs.getString("content"));
//			noticeDTO.setCreatedAt(rs.getString("createdAt"));
//			noticeList.add(noticeDTO);
//		}
//		DBUtil.close(rs, pstmt, conn);
//		return noticeList;
//	}
//
//	@Override
//	public void update(NoticeDTO noticeDTO) throws SQLException {
//		Connection conn = DBUtil.getConnection();
//		StringBuilder updateNotice = new StringBuilder();
//		updateNotice.append("update notice set title=?, content=? \n");
//		updateNotice.append("where noticeNo=? \n");
//		PreparedStatement pstmt = conn.prepareStatement(updateNotice.toString());
//		pstmt.setString(1, noticeDTO.getTitle());
//		pstmt.setString(2, noticeDTO.getContent());
//		pstmt.setInt(3, noticeDTO.getNoticeNo());
//		pstmt.executeUpdate();
//		DBUtil.close(pstmt, conn);
//
//	}
//
//	@Override
//	public void delete(NoticeDTO noticeDTO) throws SQLException {
//		Connection conn = DBUtil.getConnection();
//		StringBuilder deleteNotice = new StringBuilder();
//		deleteNotice.append("delete from notice \n");
//		deleteNotice.append("where noticeNo=?");
//		PreparedStatement pstmt = conn.prepareStatement(deleteNotice.toString());
//		pstmt.setInt(1, noticeDTO.getNoticeNo());
//		pstmt.executeUpdate();
//		DBUtil.close(pstmt, conn);
//
//	}
//
//	@Override
//	public NoticeDTO detail(String noticeNo) throws SQLException {
//		NoticeDTO noticeDTO = null;
//		Connection conn = DBUtil.getConnection();
//		StringBuilder detailNotice = new StringBuilder();
//		detailNotice.append("select noticeNo, userId, author, title, content, createdAt from notice \n");
//		detailNotice.append("where noticeNo = ?");
//		PreparedStatement pstmt = conn.prepareStatement(detailNotice.toString());
//		pstmt.setString(1, noticeNo);
//		ResultSet rs = pstmt.executeQuery();
//		System.out.println(rs);
//		if (rs.next()) {
//			noticeDTO = new NoticeDTO();
//			noticeDTO.setNoticeNo(rs.getInt("noticeNo"));
//			noticeDTO.setUserId(rs.getString("userId"));
//			noticeDTO.setAuthor(rs.getString("author"));
//			noticeDTO.setTitle(rs.getString("title"));
//			noticeDTO.setContent(rs.getString("content"));
//			noticeDTO.setCreatedAt(rs.getString("createdAt"));
//		}
//		System.out.println(noticeDTO.toString());
//		DBUtil.close(rs, pstmt, conn);
//		return noticeDTO;
//	}
//	@Override
//	public List<NoticeDTO> listNotice(ListParameterDto listParameterDto) throws SQLException {
//		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBUtil.getConnection();
//			
//			StringBuilder listNotice = new StringBuilder();
//			listNotice.append("select n.noticeNo, n.userId, n.title, n.content, n.createdAt, u.name,  \n");
//			listNotice.append(" 		case when date_format(n.createdAt, '%y%m%d') = date_format(now(), '%y%m%d') \n");
//			listNotice.append("			then date_format(n.createdAt, '%H:%i:%d') \n");
//			listNotice.append("			else date_format(n.createdAt, '%y.%m.%d') \n");
//			listNotice.append("		end createdAt \n");
//			listNotice.append("from notice n, user u \n");
//			listNotice.append("where n.userId = u.id  \n");
//			String key = listParameterDto.getKey();
//			String word = listParameterDto.getWord();
//			if(!word.isEmpty()) {
//				if(key.equals("title")) {
//					listNotice.append("and n.title like concat('%', ?, '%') \n");
//				} else {
//					listNotice.append("and n." + key + " = ? \n");
//				}
//			}
//			listNotice.append("order by n.noticeNo desc limit ?, ? \n");
//			pstmt = conn.prepareStatement(listNotice.toString());
//			int idx = 0;
//			if(!word.isEmpty()) {
//				pstmt.setString(++idx, word);
//			}
//
//			pstmt.setInt(++idx, listParameterDto.getStart());
//			pstmt.setInt(++idx, listParameterDto.getCurrentPerPage());
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				NoticeDTO noticeDTO = new NoticeDTO();
//				noticeDTO.setNoticeNo(rs.getInt("noticeNo"));
//				noticeDTO.setUserId(rs.getString("userId"));
//				noticeDTO.setAuthor(rs.getString("name"));
//				noticeDTO.setTitle(rs.getString("title").replace("<", "&lt;"));
//				noticeDTO.setContent(rs.getString("content"));
//				noticeDTO.setCreatedAt(rs.getString("createdAt"));
//				
//				list.add(noticeDTO);
//			}
//		} finally {
//			DBUtil.close(rs, pstmt, conn);
//		}
//		return list;
//	}
//	
//	@Override
//	public int getTotalCount(ListParameterDto listParameterDto) throws SQLException {
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBUtil.getConnection();
//			StringBuilder listNotice = new StringBuilder();
//			listNotice.append("select count(noticeNo) \n");
//			listNotice.append("from notice \n");
//			String key = listParameterDto.getKey();
//			String word = listParameterDto.getWord();
//			if(!word.isEmpty()) {
//				if(key.equals("title")) {
//					listNotice.append("where title like concat('%', ?, '%') \n");
//				} else {
//					listNotice.append("where " + key + " = ? \n");
//				}
//			}
//
//			pstmt = conn.prepareStatement(listNotice.toString());
//			if(!word.isEmpty())
//				pstmt.setString(1, word);
//			rs = pstmt.executeQuery();
//			rs.next();
//			cnt = rs.getInt(1);
//		} finally {
//			DBUtil.close(rs, pstmt, conn);
//		}
//		return cnt;
//	}
//}
