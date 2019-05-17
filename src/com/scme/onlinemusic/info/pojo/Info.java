package com.scme.onlinemusic.info.pojo;

import java.util.Date;

import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.user.pojo.User;

public class Info {
	private String iid;// 消息编号
	private String sender;// 收件人
	private Date sendTime;// 发送时间
	private String title;// 标题
	private String content;// 内容
	private User user;// 发件人

	private Music music;// 发送短消息的音乐

	public Info() {
	}

	public Info(String iid, String sender, Date sendTime, String title, String content, User user, Music music) {
		super();
		this.iid = iid;
		this.sender = sender;
		this.sendTime = sendTime;
		this.title = title;
		this.content = content;
		this.user = user;
		this.music = music;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
}
