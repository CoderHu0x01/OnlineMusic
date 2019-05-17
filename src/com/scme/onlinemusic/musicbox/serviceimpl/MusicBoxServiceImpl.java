package com.scme.onlinemusic.musicbox.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.musicbox.daoimpl.MusicBoxDaoImpl;
import com.scme.onlinemusic.musicbox.pojo.MusicBox;

public class MusicBoxServiceImpl {
	private MusicBoxDaoImpl musicBoxDao = new MusicBoxDaoImpl();

	/**
	 * 按多个音乐编号查询音乐信息
	 * 
	 * @param mids
	 * @return
	 */
	public List<Music> findMusicByMids(String mids) {
		try {
			return musicBoxDao.findMusicByMids(mids);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 向音乐盒中添加音乐
	 * 
	 * @param musicBox
	 */
	public void addMusic(MusicBox musicBox) {
		try {
			musicBoxDao.addMusic(musicBox);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载音乐盒信息
	 * 
	 * @param uid
	 * @return
	 */
	public List<MusicBox> loadMusicBox(String uid) {
		try {
			return musicBoxDao.load(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除音乐盒中的音乐
	 * 
	 * @param mids
	 */
	public void deletes(String mids) {
		try {
			musicBoxDao.deletes(mids);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
