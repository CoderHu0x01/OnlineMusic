package com.scme.onlinemusic.user.serviceimpl;

import java.sql.SQLException;

import com.scme.onlinemusic.user.daoimpl.UserDaoImpl;
import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.user.serviceimpl.exception.UserException;
import com.scme.onlinemusic.utils.CommonUtils;

/**
 * 用户业务逻辑层
 * 
 * @author HGD
 *
 */
public class UserServiceImpl {
	private UserDaoImpl userDao = new UserDaoImpl();

	/**
	 * 校验用户是否已被注册
	 * 
	 * @param username
	 * @return
	 */
	public boolean ajaxValidateUsername(String username) {
		try {
			return userDao.ajaxValidateUsername(username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 注册
	 * 
	 * @param user
	 */
	public void regist(User user) {
		try {
			/*
			 * 补全javabean中的信息
			 */
			// 补全uuid
			user.setUid(CommonUtils.uuid());
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 登录
	 * 
	 * @param formUser
	 * @throws UserException
	 */
	public User login(User formUser) throws UserException {
		try {
			User user = userDao.findByUsernameAndPass(formUser.getUsername(), formUser.getPassword());
			if (user == null) {
				throw new UserException("用户名或密码错误!");
			}
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
