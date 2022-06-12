package com.ssafy.happyhouse.model;

public class FileInfoDto {

	private String saveFolder;
	private String originFile;
	private String saveFile;
	private String imgpath;
	
	public String getSaveFolder() {
		return saveFolder;
	}

	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}

	public String getOriginFile() {
		return originFile;
	}

	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}

	public String getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Override
	public String toString() {
		return "FileInfoDto [saveFolder=" + saveFolder + ", originFile=" + originFile + ", saveFile=" + saveFile + "]";
	}

	

}
