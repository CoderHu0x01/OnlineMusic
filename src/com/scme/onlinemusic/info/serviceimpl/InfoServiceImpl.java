package com.scme.onlinemusic.info.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.scme.onlinemusic.info.daoimpl.InfoDaoImpl;
import com.scme.onlinemusic.info.pojo.Info;

public class InfoServiceImpl {
	private InfoDaoImpl infoDao = new InfoDaoImpl();

	/**
	 * 异步统计消息数量
	 * 
	 * @param uid
	 * @return
	 */
	public int ajaxCountInfoNum(String uid) {
		try {
			return infoDao.ajaxCountInfoNum(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加短消息
	 * 
	 * @param info
	 */
	public void addInfo(Info info) {
		try {
			infoDao.add(info);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载当前用户的所有短消息
	 * 
	 * @param uid
	 */
	public List<Info> loadInfo(String sender) {
		try {
			return infoDao.loadInfo(sender);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按照编号删除对应的短消息
	 * 
	 * @param iid
	 */
	public void deleteByIids(String iids) {
		try {
			infoDao.deleteByIids(iids);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
