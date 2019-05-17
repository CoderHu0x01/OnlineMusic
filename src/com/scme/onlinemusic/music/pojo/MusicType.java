package com.scme.onlinemusic.music.pojo;

public class MusicType {
	private String tid;// 类型编号
	private String tname;// 音乐类型

	public MusicType() {
	}

	public MusicType(String tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
}
