package com.scme.onlinemusic.info.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.scme.onlinemusic.info.pojo.Info;
import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.utils.CommonUtils;
import com.scme.onlinemusic.utils.TxQueryRunner;

public class InfoDaoImpl {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 异步统计消息数量
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int ajaxCountInfoNum(String uid) throws SQLException {
		String sql = "select count(*) from tb_info where sender=?";
		Number num = (Number) qr.query(sql, new ScalarHandler(), uid);
		return num.intValue();
	}

	/**
	 * 按照消息id删除对应的短消息
	 * 
	 * @param iid
	 * @throws SQLException
	 */
	public void deleteByIids(String iids) throws SQLException {
		StringBuilder sb = new StringBuilder("delete from tb_info where iid=?");
		/*
		 * 按照逗号切割编号字符串获取编号数组
		 */
		Object[] arrIids = iids.split(",");
		if (arrIids.length > 1) {// 当消息编号大于一个时
			for (int i = 0; i < arrIids.length; i++) {
				sb.append(" or iid=? ");
			}
		}
		qr.update(sb.toString(), arrIids);
	}

	/**
	 * 添加短消息
	 * 
	 * @param info
	 * @throws SQLException
	 */
	public void add(Info info) throws SQLException {
		String sql = "insert into tb_info values(?,?,?,?,?,?,?)";
		Object[] params = { info.getIid(), info.getSender(), info.getSendTime(), info.getTitle(), info.getContent(),
				info.getUser().getUid(), info.getMusic().getMid() };
		qr.update(sql, params);
	}

	/**
	 * 加载所有短消息
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public List<Info> loadInfo(String sender) throws SQLException {
		String sql = "select * from tb_info as i,tb_user as u,tb_music as m where i.uid=u.uid and i.mid=m.mid and sender=?";
		List<Map<String, Object>> mapListInfo = qr.query(sql, new MapListHandler(), sender);
		/*
		 * 将多个Map转换成多个Info
		 */
		List<Info> listInfo = toListInfo(mapListInfo);
		return listInfo;
	}

	/**
	 * 将多个Map转换成多个Info
	 * 
	 * @param mapListInfo
	 * @return
	 */
	private List<Info> toListInfo(List<Map<String, Object>> mapListInfo) {
		List<Info> listInfo = new ArrayList<Info>();
		for (Map<String, Object> map : mapListInfo) {
			/*
			 * 将一个map转换成一个info
			 */
			Info info = toInfo(map);
			listInfo.add(info);
		}
		return listInfo;
	}

	/**
	 * 将多个Map转换成一个info
	 * 
	 * @param map
	 * @return
	 */
	private Info toInfo(Map<String, Object> map) {
		Info info = CommonUtils.toBean(map, Info.class);
		User user = CommonUtils.toBean(map, User.class);
		Music music = CommonUtils.toBean(map, Music.class);
		info.setUser(user);
		info.setMusic(music);
		return info;
	}
}
