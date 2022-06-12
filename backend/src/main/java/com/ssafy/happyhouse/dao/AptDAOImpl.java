//package happyhouse.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import happyhouse.dto.AccountDTO;
//import happyhouse.dto.AptDTO;
//import happyhouse.util.DBUtil;
//
//public class AptDAOImpl implements AptDAO {
//	private static AptDAO aptDAO = new AptDAOImpl();
//
//	private AptDAOImpl() {
//	}
//
//	public static AptDAO getAptDAO() {
//		return aptDAO;
//	}
//
//	@Override
//	public ArrayList<AptDTO> search(String keyword, String searchTarget) throws SQLException {
//		ArrayList<AptDTO> aptList = new ArrayList<>();
//		Connection conn = DBUtil.getConnection();
//		StringBuilder searchApt = new StringBuilder();
//		searchApt.append(
//				"select hi.aptName, hi.dongName, hi.dongCode, hd.dealAmount,  hd.dealYear, hd.dealMonth, hd.dealDay, hi.lat, hi.lng \n");
//		searchApt.append("from housedeal hd join houseinfo hi \n");
//		searchApt.append("on hd.aptCode = hi.aptCode \n");
//		if (searchTarget.equals("dongSearch")) {
//			System.out.println("동검색");
//			searchApt.append("where hi.dongCode = (select dongCode from dongcode where dongName = ? ) \n");
//		} else {
//			searchApt.append("where hi.aptName like concat('%', ?, '%') \n");
//		}
//		searchApt.append("order by  hd.dealYear asc, hd.dealMonth desc , hd.dealDay desc \n");
//		PreparedStatement pstmt = conn.prepareStatement(searchApt.toString());
//		pstmt.setString(1, keyword);
//		System.out.println(searchApt.toString());
//		ResultSet rs = pstmt.executeQuery();
//		while (rs.next()) {
//			AptDTO aptDTO = new AptDTO(rs.getString("hi.aptName"), rs.getString("hi.dongName"),
//					rs.getString("hi.dongCode"), rs.getString("hd.dealAmount").trim(), rs.getString("hd.dealYear"),
//					rs.getString("hd.dealMonth"), rs.getString("hd.dealDay"), rs.getString("hi.lat"),
//					rs.getString("hi.lng"));
//			aptList.add(aptDTO);
//		}
//		System.out.println(aptList.size());
//		DBUtil.close(rs, pstmt, conn);
//		return aptList;
//	}
//
//}
//
////select hi.aptName, hi.dongName, hd.dealAmount, hi.dongCode, hd.dealYear, hd.dealMonth, hd.dealDay, hi.lat, hi.lng
////from housedeal hd join houseinfo hi
////on hd.aptCode = hi.aptCode
////where hi.dongCode = (
////					select dongCode
////                    from dongcode
////                    where dongName = "내수동"
////                    );
////
////select *
////from housedeal hd join houseinfo hi
////on hd.aptCode = hi.aptCode
////where hi.aptName like "%광화문%";