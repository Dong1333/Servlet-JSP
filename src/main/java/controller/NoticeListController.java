package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Notice> list = new ArrayList<> ();
		
		String sUrl = "jdbc:mysql://localhost:3306/firstDB";
		String sUser = "root";
		String sPwd = "12341234";
		
		String sSql = "SELECT * FROM NOTICE;"; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection MyConn = DriverManager.getConnection(sUrl, sUser, sPwd);
			
			Statement st = MyConn.createStatement();
			ResultSet rs = st.executeQuery(sSql);
			
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
		
		request.setAttribute("list", list);
		
		request
		
		.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
		.forward(request, response);

	}

}
