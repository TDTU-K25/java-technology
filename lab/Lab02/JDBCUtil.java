package org.example;

import java.sql.*;

public class JDBCUtil {

	static void createDB(Connection conn, String databaseName) {
		String sql = "create database if not exists " + databaseName;
		try {
			Statement stm = conn.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	static boolean isTableExists(String tableName, String url) {
		Connection conn = getConnection(url);
		String sql = "select count(*) from information_schema.tables where table_name = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tableName);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getInt(1) != 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
	}

	static void deleteTable(String tableName, String url) {
		Connection conn = getConnection(url);
		String sql = "drop table " + tableName;
		try {
			Statement ps = conn.createStatement();
			ps.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
	}

	static void createTableProduct(String url) {
		Connection conn = getConnection(url);
		String sql = "create table product " +
			"(id int auto_increment, " +
			" name varchar(255), " +
			" price int, " +
			" color varchar(255), " +
			" primary key (id))";
		try {
			Statement stm = conn.createStatement();
			int rows = stm.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
	}

	public static Connection getConnection(String url) {
		try {
			Connection conn = DriverManager.getConnection(url);
			String databaseName = "ProductManagement";
			createDB(conn, databaseName);
			conn.setCatalog(databaseName);
			return conn;
		} catch (SQLException e) {
			System.out.println("Connect failed" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
