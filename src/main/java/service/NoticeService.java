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
		
		return null;
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "1");
	}
	
	public int getNoticeCount(String filed, String query) {
		
		return 0;
	}
	
	public Notice getNotice(int id) {
		
		return null;
	}

	public Notice getNextNotice(int id) {
			
			return null;
		}
	
	public Notice getPrevNotice(int id) {
		
		return null;
	}

}
