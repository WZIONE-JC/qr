package com.xaut.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * db���� database Ҳ�������ݿ���ʹ������ �������Ҫ���ں����ݿ���н�����CRUD������ɾ�Ĳ顿 ����������� ���� ���ݵĲ���������ɾ�Ĳ�
 * 
 * @author Administrator
 * 
 */
public class DBManager {

	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
	//?useUnicode=true&characterEncoding=UTF-8
	//private static final String URL = "jdbc:mysql://118.89.150.221:3306/QRcode?useUnicode=true&characterEncoding=UTF-8";
	private static final String URL = "jdbc:mysql://127.0.01:3306/erwei?useUnicode=true&characterEncoding=UTF-8";
	private static final String NAME = "root";
	private static final String PASS = "root";
	
	//private static final String URL = "jdbc:mysql://47.90.16.158:3309/erweidb2";
	//private static final String NAME = "erweidb2";
	//private static final String PASS = "ligongda";
	
	//private static final String NAME = "myuser";
	//private static final String PASS = "mypassword";
	private Connection conn;

	static {
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, NAME, PASS);
	}

	/**
	 * ������ݵĸ��²���
	 */
	public void update(String sql) {

		try {
			conn = getConnection();
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConn();
		}

	}
	/**
	 * ������ݵĲ�ѯ����
	 */
	public ResultSet query(String sql) {
		try {
			conn = getConnection();
			return conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �˴�connection�����Թرգ���Ϊresultset�Ľ���Ҫ��connection��������״̬
		return null;

	}

	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
