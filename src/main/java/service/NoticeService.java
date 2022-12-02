package service;

import java.util.List;

import entity.Notice;

public class NoticeService {
	
	public List<Notice> getNoticeList(){
		
		return getNoticeList("title", "", 1);
	}
	
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList("title", "", page);
	}
	
	public List<Notice> getNoticeList(String filed, String query, int page){
		String sql = "SELECT * FROM("
				+ "	SELECT ROW_NUMBER() OVER(ORDER BY ID DESC) AS ROWNUM,"
				+ "	NOTICE.* FROM NOTICE) TMP"
				+ "WHERE ROWNUM BETWEEN 1 AND 5";
		return null;
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
		String sql = "select * from"
				+ "	(select ROW_NUMBER() over(order by n.regdate) rownum, n.*"
				+ "   from ( select *  from notice where regdate <"
				+ "	(select regdate from notice where id = 3)) n) n2"
				+ "where rownum = 1";
		return null;
	}

}
