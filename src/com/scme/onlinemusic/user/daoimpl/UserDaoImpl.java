package com.scme.onlinemusic.user.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.utils.TxQueryRunner;

/**
 * 用户持久层
 * 
 * @author HGD
 *
 */
public class UserDaoImpl {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 异步校验,按用户名查找用户,判断该用户是否已被注册
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean ajaxValidateUsername(String username) throws SQLException {
		/*
		 * 查询当前用户名是否存在一行数据
		 */
		String sql = "select count(1) from tb_user where username=?";
		Number num = (Number) qr.query(sql, new ScalarHandler(), username);
		return num.intValue() == 0;
	}

	/**
	 * 添加
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void add(User user) throws SQLException {
		String sql = "insert into tb_user values(?,?,?)";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword());
	}

	/**
	 * 按用户名和密码查找用户
	 * 
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public User findByUsernameAndPass(String username, String password) throws SQLException {
		String sql = "select * from tb_user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), username, password);
	}
}
