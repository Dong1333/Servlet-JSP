 package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			int id = Integer.parseInt(request.getParameter("id"));
			String sUrl = "jdbc:mysql://localhost:3306/firstDB";
			String sUser = "root";
			String sPwd = "12341234";
			
			String sSql = "SELECT * FROM NOTICE WHERE ID=?;"; 
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection MyConn = DriverManager.getConnection(sUrl, sUser, sPwd);
				
				PreparedStatement st = MyConn.prepareStatement(sSql);
				st.setInt(1, id);
				ResultSet rs = st.executeQuery();
				

				// model 변수 생성
				rs.next();
				String title = rs.getString("TITLE"); 
				String writeid =rs.getString("WRITER_ID"); 
				Date regdate =rs.getDate("REGDATE");
				String hit =rs.getString("HIT"); 
				String files =rs.getString("FILES");	
				String content =rs.getString("CONTENT"); 
				
				request.setAttribute("title", title);
				request.setAttribute("writeid", writeid);
				request.setAttribute("regdate", regdate);
				request.setAttribute("hit", hit);
				request.setAttribute("files", files);
				request.setAttribute("content", content);
				
			    
			    
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
			
			
			request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
	}
}
