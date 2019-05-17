package com.scme.onlinemusic.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	// 连接池对象
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	// 事务专用连接线程
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		// 获取事务专用连接
		Connection conn = threadLocal.get();
		if (conn != null) {// 若事务专用连接不为空,则返回当前连接
			return conn;
		}
		return dataSource.getConnection();
	}

	/**
	 * 获取线程池对象
	 * 
	 * @return
	 */
	public static ComboPooledDataSource getDateSource() {
		return dataSource;
	}

	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		Connection conn = threadLocal.get();
		if (conn != null) {// 若事务专用连接不为空,则说明已经开启了事务
			throw new RuntimeException("您已经开启了事务,无需再次开启!");
		}
		// 否则为空，则为conn赋值
		conn = getConnection();
		// 关闭自动提交,开启事务
		conn.setAutoCommit(false);
		// 保存当前事务连接
		threadLocal.set(conn);
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException {
		Connection conn = threadLocal.get();
		if (conn == null) {// 若当前事务连接为null,则说明还没有连接
			throw new RuntimeException("您还没有开启事务无法提交！");
		}
		// 提交事务
		conn.commit();
		conn.close();
		// 将当前事务连接从线程中移除
		threadLocal.remove();
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection conn = threadLocal.get();
		if (conn == null) {// 若当前事务连接为null,则说明还没有提交事务
			throw new RuntimeException("事务还未提交无法回滚！");
		}
		// 回滚事务
		conn.rollback();
		conn.close();
		// 将当前事务连接从线程中移除
		threadLocal.remove();
	}

	/**
	 * 释放资源
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	public static void freeConnetion(Connection connection) throws SQLException {
		Connection conn = threadLocal.get();
		if (conn == null) {// 若事务专用连接已关闭,则释放当前连接
			connection.close();
		}
		if (conn != connection) {// 若事务专用连接与当前连接不相同则关闭当前连接
			connection.close();
		}
	}
}
