package com.oracle.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * db���� database Ҳ�������ݿ���ʹ������ �������Ҫ���ں����ݿ���н�����CRUD������ɾ�Ĳ顿 ����������� ���� ���ݵĲ���������ɾ�Ĳ�
 * 
 * @author Administrator
 * 
 */
public class DBManagerTest {

	// ���ȴ��Ҫ��ȷ java �������ݿ�ļ������� JDBC
	// JDBC�������ݿ��������Ĳ���
	// a ��������
	// b ��������
	// c ����ִ�ж���
	// d ����CRUD�Ĳ���
	// e �ͷ���Դ �ر�����

	// main������java�������ڣ�java����Ӵ˴���ʼִ��
	public static void main(String[] args) {
		// System.out.println("hello world��");
		// JOptionPane.showMessageDialog(null, "��ã��й������磬���");
		// ���������ݿ��ʱ��sun��˾��һ��jdbc��api�����ǽӿ�
		// �ӿ�ֻ�ܹ�ͨ��ʵ����������
		// �������ݿ⳧��ʵ����sun��˾�����׽ӿ�
		// ���ʵ�ֽӿڵ��� ��֯����һ���ļ��ͽ���jar�ļ���
		// �������ǳ����ڷ������ݿ�֮ǰ��������Ҫ�������ʵ�ֵ�����jar�ļ�
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://47.90.16.158:3309/erweidb", "erweidb", "ligongda");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from account");
			while (rs.next()) {
				System.out.println(rs.getString("sphone"));
				System.out.println(rs.getString("spass"));
				System.out.println(rs.getString("slastlogintime"));
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
