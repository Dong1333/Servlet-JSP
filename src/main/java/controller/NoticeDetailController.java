 package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Notice;
import service.NoticeService;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 사용자가 요청할때 전달 했던 ID값
			int id = Integer.parseInt(request.getParameter("id"));
			
			NoticeService service = new NoticeService();
			Notice notice = service.getNotice(id); // 서비스 Notice 하나를 얻는 메소드
			
			request.setAttribute("n", notice); // 받은 notice를 "n"이라는 키워드로 전달
			
			
			// forward
			// 사용자에게 돌려주는 출력 로직
			request
			.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp")
			.forward(request, response);
	}
}
