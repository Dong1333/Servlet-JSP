package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Notice;

public class NoticeService {
	
	public List<Notice> getNoticeList(){
		
		return getNoticeList("TITLE", "", 1);
	}
	
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList("TITLE", "", page);
	}
	
	public List<Notice> getNoticeList(String field, String query, int page){
		// 매개변수 filed로 받을 값 TITLE, WRITER_ID 중 1
		// 매개변수 query로 받을 값 검색어 값 ex) a, "2"
		List<Notice> list = new ArrayList<>() ;
		
		String sql = "SELECT * FROM("
				+ "	SELECT	ROW_NUMBER() OVER(ORDER BY ID DESC) AS ROWNUM, "
				+ "    NOTICE.* FROM NOTICE WHERE "+field+" LIKE ? ) TMP "
				+ " WHERE ROWNUM BETWEEN ? AND ?";

		// 등차수열 1, 11, 21, 31 -> an = 1+(page-1)*10
		
		// 10, 20, 30, 40, -> page * 10

		
		String sUrl = "jdbc:mysql://localhost:3306/firstDB";
		String sUser = "root";
		String sPwd = "12341234";
		 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection MyConn = DriverManager.getConnection(sUrl, sUser, sPwd);
			
			PreparedStatement st = MyConn.prepareStatement(sql);
			
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page * 10);
			
			ResultSet rs = st.executeQuery();
			
			// model 변수 생성
			while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE"); 
			String writeid =rs.getString("WRITER_ID"); 
			Date regdate =rs.getDate("REGDATE");
			String hit =rs.getString("HIT"); 
			String files =rs.getString("FILES");	
			String content =rs.getString("CONTENT"); 
			
			Notice notice = new Notice(
					id, 
					title,
					writeid,
					regdate,
					hit,
					files,
					content
				);
			list.add(notice);
		}
		    rs.close();
		    st.close();
		    MyConn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String filed, String query) {
		String sql = "SELECT * FROM("
				+ "	SELECT ROW_NUMBER() OVER(ORDER BY ID DESC) AS ROWNUM,"
				+ "	NOTICE.* FROM NOTICE) TMP"
				+ "WHERE ROWNUM BETWEEN 1 AND 5";
		return 0;
	}
	
	public Notice getNotice(int id) {
		String sql = "SELECT * FROM NOTICE WHRER ID=?";
		return null;
	}

	public Notice getNextNotice(int id) {
		String sql = "select * from"
				+ "	(select ROW_NUMBER() over(order by n.regdate) rownum, n.*"
				+ "    from ( select *  from notice where regdate >"
				+ "		(select regdate from notice where id = 3)) n) n2"
				+ "where rownum = 1";
			return null;
		}
	
	public Notice getPrevNotice(int id) {
		String sql = "select * from "
				+ "	(select ROW_NUMBER() over(order by n.regdate desc) rownum, n.*"
				+ "    from ( select *  from notice where regdate >"
				+ "		(select regdate from notice where id = 3)) n) n2"
				+ " where rownum = 1"; 
		return null;
	}

}
