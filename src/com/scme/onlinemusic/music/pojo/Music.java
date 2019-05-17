package com.scme.onlinemusic.music.pojo;

import java.util.Date;

import com.scme.onlinemusic.user.pojo.User;

public class Music {
	private String mid;// 音乐编号
	private String mname;// 音乐名称
	private String singer;// 歌手
	private String album;// 专辑
	private String summary;// 描述
	private String path;// 音乐路径
	private Date uploadTime;// 上传时间

	// 所属用户
	User owner;
	// 音乐类型
	private MusicType musicType;

	public Music() {
	}

	public Music(String mid, String mname, String singer, String album, String summary, String path, Date uploadTime) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.singer = singer;
		this.album = album;
		this.summary = summary;
		this.path = path;
		this.uploadTime = uploadTime;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public MusicType getMusicType() {
		return musicType;
	}

	public void setMusicType(MusicType musicType) {
		this.musicType = musicType;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
