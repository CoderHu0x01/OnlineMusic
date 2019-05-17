package com.scme.onlinemusic.user.pojo;

public class User {
	private String uid;// 用户id
	private String username;// 用户名
	private String password;// 密码

	private String repass;// 确认密码

	public User() {
	}

	public User(String uid, String username, String password) {
		this.uid = uid;
		this.username = username;
		this.password = password;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}
}
