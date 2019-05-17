package com.scme.onlinemusic.music.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.music.pojo.MusicType;
import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.utils.CommonUtils;
import com.scme.onlinemusic.utils.TxQueryRunner;

public class MusicDaoImpl {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 加载所有音乐分享信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Music> load() throws SQLException {
		String sql = "select * from tb_music as m,tb_user as u where m.uid=u.uid";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		/*
		 * 将多个Map转换成多个Music
		 */
		List<Music> listMusic = toListMusic(mapList);
		return listMusic;
	}

	/**
	 * 将多个Map转换成多个Music
	 * 
	 * @param mapList
	 * @return
	 */
	private List<Music> toListMusic(List<Map<String, Object>> mapList) {
		List<Music> listMusic = new ArrayList<Music>();
		for (Map<String, Object> map : mapList) {
			/*
			 * 将多个map转换成一个Music
			 */
			Music music = toMusic(map);
			listMusic.add(music);
		}
		return listMusic;
	}

	/**
	 * 将多个map转换成一个Music
	 * 
	 * @param map
	 * @return
	 */
	private Music toMusic(Map<String, Object> map) {
		Music music = CommonUtils.toBean(map, Music.class);
		User owner = CommonUtils.toBean(map, User.class);
		music.setOwner(owner);
		return music;
	}

	/**
	 * 加载指定用户的音乐信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Music> findMusicByUid(String uid) throws SQLException {
		String sql = "select * from tb_music where uid=?";
		return qr.query(sql, new BeanListHandler<Music>(Music.class), uid);
	}

	/**
	 * 按编号查找音乐类型
	 * 
	 * @param tid
	 * @return
	 * @throws SQLException
	 */
	public MusicType findByTid(String tid) throws SQLException {
		String sql = "select * from tb_musicType where tid=?";
		return qr.query(sql, new BeanHandler<MusicType>(MusicType.class), tid);
	}

	/**
	 * 加载音乐类型
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<MusicType> loadMusicType() throws SQLException {
		String sql = "select * from tb_musictype";
		List<MusicType> list = qr.query(sql, new BeanListHandler<MusicType>(MusicType.class));
		return list;
	}

	/**
	 * 添加音乐信息
	 * 
	 * @param music
	 * @throws SQLException
	 */
	public void add(Music music) throws SQLException {
		String sql = "insert into tb_music values(?,?,?,?,?,?,?,?,?)";
		Object[] params = { music.getMid(), music.getMname(), music.getSinger(), music.getAlbum(), music.getSummary(),
				music.getPath(), music.getUploadTime(), music.getMusicType().getTid(), music.getOwner().getUid() };
		qr.update(sql, params);
	}
}
