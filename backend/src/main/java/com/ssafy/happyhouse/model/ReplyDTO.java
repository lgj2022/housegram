package com.ssafy.happyhouse.model;

public class ReplyDTO {
	String rno;
	String rcontent;
	String rid;
	String rcreatedAt;
	String articleno;
	
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRcreatedAt() {
		return rcreatedAt;
	}
	public void setRcreatedAt(String rcreatedAt) {
		this.rcreatedAt = rcreatedAt;
	}
	public ReplyDTO() {
		super();
	}
	public String getArticleno() {
		return articleno;
	}
	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	@Override
	public String toString() {
		return "ReplyDTO [rno=" + rno + ", rcontent=" + rcontent + ", rid=" + rid + ", rcreatedAt=" + rcreatedAt
				+ ", articleno=" + articleno + "]";
	}
	
	
}
