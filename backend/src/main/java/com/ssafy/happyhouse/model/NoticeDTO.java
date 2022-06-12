package com.ssafy.happyhouse.model;

public class NoticeDTO {
	int noticeNo;
	String userId;
	String author;
	String title;
	String content;
	String createdAt;
	String hits;
	
	public NoticeDTO() {}
	
	
	public NoticeDTO(int noticeNo, String userId, String author, String title, String content, String createdAt,
			String hits) {
		super();
		this.noticeNo = noticeNo;
		this.userId = userId;
		this.author = author;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.hits = hits;
	}


	public String getHits() {
		return hits;
	}


	public void setHits(String hits) {
		this.hits = hits;
	}


	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "NoticeDTO [noticeNo=" + noticeNo + ", userId=" + userId + ", author=" + author + ", title=" + title
				+ ", content=" + content + ", createdAt=" + createdAt + ", hits=" + hits + "]";
	}


}
