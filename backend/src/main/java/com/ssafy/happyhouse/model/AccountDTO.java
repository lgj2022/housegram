package com.ssafy.happyhouse.model;

public class AccountDTO {
	String id;
	String pw;
	String name;
	String phone;
	String salt;
	String email;
	boolean authority;
	String profile_img;
	String age;

	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean isAuthority() {
		return authority;
	}
	
	public void setAuthority(boolean authority) {
		this.authority = authority;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AccountDTO() {
		super();
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", salt=" + salt
				+ ", email=" + email + ", authority=" + authority + ", profile_img=" + profile_img + ", age=" + age
				+ "]";
	}



}
