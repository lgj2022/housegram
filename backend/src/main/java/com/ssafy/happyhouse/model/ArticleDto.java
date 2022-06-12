package com.ssafy.happyhouse.model;

import java.util.List;

public class ArticleDto {
	int articleNo;
	String userid;
	String username;
	String title;
	String content;
	String like_count;
	String createdAt;
	String user_profile_img;
	int hashtagno;
	private List<FileInfoDto> fileInfos;
	private List<TagDto> tagInfos;
	
	public String getUser_profile_img() {
		return user_profile_img;
	}
	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getHashtagno() {
		return hashtagno;
	}
	public void setHashtagno(int hashtagno) {
		this.hashtagno = hashtagno;
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
	public String getLike_count() {
		return like_count;
	}
	public void setLike_count(String like_count) {
		this.like_count = like_count;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<TagDto> getTagInfos() {
		return tagInfos;
	}
	public void setTagInfos(List<TagDto> tagInfos) {
		this.tagInfos = tagInfos;
	}
	public ArticleDto(int articleNo, String userid, String subject, String content, String like_count, String createdAt,
			List<FileInfoDto> fileInfos) {
		super();
		this.articleNo = articleNo;
		this.userid = userid;
		this.title = subject;
		this.content = content;
		this.like_count = like_count;
		this.createdAt = createdAt;
		this.fileInfos = fileInfos;
	}
	public ArticleDto() {
		super();
	}
	@Override
	public String toString() {
		return "ArticleDto [articleNo=" + articleNo + ", userid=" + userid + ", username=" + username + ", title="
				+ title + ", content=" + content + ", like_count=" + like_count + ", createdAt=" + createdAt
				+ ", user_profile_img=" + user_profile_img + ", hashtagno=" + hashtagno + ", fileInfos=" + fileInfos
				+ ", tagInfos=" + tagInfos + "]";
	}
	
	

}
