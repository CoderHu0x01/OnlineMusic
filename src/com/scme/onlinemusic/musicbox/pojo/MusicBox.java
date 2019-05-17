package com.scme.onlinemusic.musicbox.pojo;

import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.user.pojo.User;

public class MusicBox {
	private String bid;// 编号
	private Music music;// 音乐盒中的音乐
	private User owner;// 所属用户

	public MusicBox() {
	}

	public MusicBox(String bid, Music music, User owner) {
		super();
		this.bid = bid;
		this.music = music;
		this.owner = owner;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
