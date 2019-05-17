package com.scme.onlinemusic.musicbox.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.musicbox.pojo.MusicBox;
import com.scme.onlinemusic.utils.CommonUtils;
import com.scme.onlinemusic.utils.TxQueryRunner;

public class MusicBoxDaoImpl {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 按多个音乐编号查询音乐信息
	 * 
	 * @param mids
	 * @return
	 * @throws SQLException
	 */
	public List<Music> findMusicByMids(String mids) throws SQLException {
		StringBuilder sb = new StringBuilder("select * from tb_music where mid=?");
		// 将字符串按逗号切割成数组
		Object[] arrMid = mids.split(",");
		if (arrMid.length > 1) {// mid长度大于1时
			for (int i = 0; i < arrMid.length; i++) {// 处理sql语句
				sb.append(" or mid=?");
			}
		}
		return qr.query(sb.toString(), new BeanListHandler<Music>(Music.class), arrMid);
	}

	/**
	 * 向音乐盒中添加音乐
	 * 
	 * @param musicBox
	 * @throws SQLException
	 */
	public void addMusic(MusicBox musicBox) throws SQLException {
		String sql = "insert into tb_musicbox values(?,?,?)";
		Object[] params = { musicBox.getBid(), musicBox.getMusic().getMid(), musicBox.getOwner().getUid() };
		qr.update(sql, params);
	}

	/**
	 * 加载音乐盒信息
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public List<MusicBox> load(String uid) throws SQLException {
		String sql = "select * from tb_musicbox as b,tb_music as m where b.mid=m.mid and b.uid=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), uid);
		/*
		 * 将List中的Map映射成多个MusicBox
		 */
		List<MusicBox> listMusicBox = toListMusicBox(mapList);
		return listMusicBox;
	}

	/**
	 * 将List中的Map映射成多个MusicBox
	 * 
	 * @param mapList
	 */
	private List<MusicBox> toListMusicBox(List<Map<String, Object>> mapList) {
		List<MusicBox> listMusicBox = new ArrayList<MusicBox>();
		/*
		 * 将多个map映射成一个MusicBox
		 */
		for (Map<String, Object> map : mapList) {
			MusicBox musicBox = toMusicBox(map);
			listMusicBox.add(musicBox);
		}
		return listMusicBox;
	}

	/**
	 * 将多个map映射成一个MusicBox
	 * 
	 * @param map
	 */
	private MusicBox toMusicBox(Map<String, Object> map) {
		Music music = CommonUtils.toBean(map, Music.class);
		MusicBox musicBox = CommonUtils.toBean(map, MusicBox.class);
		musicBox.setMusic(music);
		return musicBox;
	}

	/**
	 * 按音乐编号删除音乐盒中的音乐
	 * 
	 * @param mids
	 * @throws SQLException
	 */
	public void deletes(String mids) throws SQLException {
		StringBuilder sb = new StringBuilder("delete from tb_musicbox where mid=?");
		// 将字符串按逗号切割成数组
		Object[] arrMid = mids.split(",");
		if (arrMid.length > 1) {// mid长度大于1时
			for (int i = 0; i < arrMid.length; i++) {// 处理sql语句
				sb.append(" or mid=?");
			}
		}
		qr.update(sb.toString(), arrMid);
	}
}
