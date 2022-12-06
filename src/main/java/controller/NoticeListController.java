package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Notice;
import service.NoticeService;


// 컨트롤러 영역 * 사용자의 요청을 받는 곳, 사용자와 상호작용 하는 공간
@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 기능에 필요한 데이터.  전달 예시 > list?f=title&q=a
		
		// 우선 검색어 전달이 안 됐을때 대비하여 임시 변수선언 하기 (값이 들어오면 지금 선언한 변수값이 할당)
		String field_ = request.getParameter("f"); // 필드 기준으로으어떤 기준으로 전달할 지
		String query_ = request.getParameter("q"); // 사용자가 입력한 검색어(검색 값)

		// 실제로 전달이 안됐을 때 (null 이면) "title" 기본 값 지정
		// 값이 전달 돼으면 field_으로 전달 받은 값 field에 넣어주기
		String field = "title";
		if(field_ != null)
			field = field_;
		
		String query = "";
		if(query_ != null)
			query = query_;
		
		NoticeService service = new NoticeService();
		List<Notice> list = service.getNoticeList(field, query, 1);
		
		request.setAttribute("list", list);
		
		request
		.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
		.forward(request, response);

	}
}
