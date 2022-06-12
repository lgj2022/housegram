package com.ssafy.happyhouse.model;

public class TagDto {
	String tagno;
	String tagname;
	public String getTagno() {
		return tagno;
	}
	public void setTagno(String tagno) {
		this.tagno = tagno;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public TagDto(String tagno, String tagname) {
		super();
		this.tagno = tagno;
		this.tagname = tagname;
	}
	public TagDto() {
		super();
	}
	
	
	
}
