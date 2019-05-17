package com.scme.onlinemusic.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 增强QueryRunner处理mysql连接与释放
 * 
 * @author HGD
 *
 */
public class TxQueryRunner extends QueryRunner {

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		int[] result = super.batch(connection, sql, params);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public int execute(String sql, Object... params) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		int result = super.execute(connection, sql, params);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> List<T> execute(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		List<T> result = super.execute(connection, sql, rsh, params);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		T result = super.insert(connection, sql, rsh, params);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		T result = super.insert(connection, sql, rsh);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		T result = super.insertBatch(connection, sql, rsh, params);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		T result = super.query(connection, sql, param, rsh);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		T result = super.query(connection, sql, params, rsh);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		T result = super.query(connection, sql, rsh, params);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		T result = super.query(connection, sql, rsh);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		int result = super.update(connection, sql, params);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		int result = super.update(connection, sql, param);
		JdbcUtils.freeConnetion(connection);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		int result = super.update(connection, sql);
		JdbcUtils.freeConnetion(connection);
		return result;
	}
}
