package com.scme.onlinemusic.music.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.scme.onlinemusic.music.daoimpl.MusicDaoImpl;
import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.music.pojo.MusicType;

public class MusicServiceImpl {
	private MusicDaoImpl musicDao = new MusicDaoImpl();

	/**
	 * 加载音乐分享信息
	 * 
	 * @return
	 */
	public List<Music> loadMusicShare() {
		try {
			return musicDao.load();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按类型编号查找音乐类型
	 * 
	 * @param tid
	 * @return
	 */
	public MusicType findByTid(String tid) {
		try {
			return musicDao.findByTid(tid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载音乐类型
	 * 
	 * @return
	 */
	public List<MusicType> loadMusicType() {
		try {
			return musicDao.loadMusicType();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加音乐信息
	 * 
	 * @param music
	 */
	public void add(Music music) {
		try {
			musicDao.add(music);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
