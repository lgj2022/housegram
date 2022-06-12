package com.ssafy.happyhouse.model;

public class NewsDTO {
	String newsNo;
	String publisher;
	String title;
	String link;
	String img;
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getNewsNo() {
		return newsNo;
	}
	public void setNewsNo(String newsNo) {
		this.newsNo = newsNo;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public NewsDTO() {
		super();
	}
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "NewsDTO [newsNo=" + newsNo + ", publisher=" + publisher + ", title=" + title + ", link=" + link
				+ ", img=" + img + "]";
	}
	
	
}
