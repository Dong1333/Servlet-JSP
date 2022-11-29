package entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String writeid;
	private Date regdate;
	private String hit;
	private String files;
	private String content;
	
	// 기본 성성자
	public Notice() {
	}
	
	// 생성자 
	public Notice(int id, String title, String writeid, Date regdate2, String hit, String files, String content) {
		this.id = id;
		this.title = title;
		this.writeid = writeid;
		this.regdate = regdate2;
		this.hit = hit;
		this.files = files;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriteid() {
		return writeid;
	}

	public void setWriteid(String writeid) {
		this.writeid = writeid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writeid=" + writeid + ", regdate=" + regdate + ", hit="
				+ hit + ", files=" + files + ", content=" + content + "]";
	}
}
