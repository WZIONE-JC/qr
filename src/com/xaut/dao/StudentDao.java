package com.xaut.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xaut.entity.Student;
import com.xaut.entity.Teacher;
import com.xaut.util.DBManager;

public class StudentDao {

	private DBManager db = new DBManager();

	public void saveStudent(Student stu) {

		String sql = "insert into student(sname,ssex,sphone,spassword,classno) "
				+ " values( '"
				+ stu.getSname()
				+ "',"
				+ stu.getSsex()
				+ ",'"
				+ stu.getSphone()
				+ "','"
				+ stu.getSpassword()
				+ "',"
				+ stu.getClassno() + " )";

		db.update(sql);

	}

	/**
	 * ͨ���ֻ��Ų����Ƿ����ѧ��
	 * 
	 * @param sphone
	 * @return
	 */
	public boolean queryBySphone(String sphone) {
		try {
			String sql = "select * from student where sphone=" + sphone;
			ResultSet rs = db.query(sql);

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return true;
	}

	/**
	 * ͨ���ֻ��������ѧ����Ϣ
	 * 
	 * @param sphone
	 * @return
	 */
	public Student queryBySphone2(String sphone) {
		Student s = null;
		try {
			String sql = "select * from student where sphone=" + sphone;
			ResultSet rs = db.query(sql);

			while (rs.next()) {
				s = new Student();
				s.setClassno(rs.getInt("classno"));
				s.setSname(rs.getString("sname"));
				s.setSno(rs.getInt("sno"));
				s.setSpassword(rs.getString("spassword"));
				s.setSphone(sphone);
				s.setSsex(rs.getInt("ssex"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return s;
	}
	
	
	
	
	/**
	 * ͨ��ѧ���ı�Ų���ѧ������Ϣ
	 * 
	 * @param sphone
	 * @return
	 */
	public Student queryBySNO(int sno) {
		Student s = null;
		try {
			String sql = "select * from student where sno=" + sno;
			ResultSet rs = db.query(sql);

			while (rs.next()) {
				s = new Student();
				s.setClassno(rs.getInt("classno"));
				s.setSname(rs.getString("sname"));
				s.setSno(rs.getInt("sno"));
				s.setSpassword(rs.getString("spassword"));
 				s.setSsex(rs.getInt("ssex"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
		return s;
	}
	
	

	/**
	 * ͨ���༶��Ų��� �༶��Ӧ��ѧ������
	 * 
	 * @return
	 */
	public int queryStudentCountByClassNo(String classno) {
		DBManager db = new DBManager();
		int count = 0;
		try {
			String sql = "select count(sno) countno from student where classno="
					+ classno;
			ResultSet rs = db.query(sql);
			while (rs.next()) {
				count = rs.getInt("countno");
			}
		} catch (SQLException e) {
 			e.printStackTrace();
		} finally {
			db.closeConn();
		}

		return count;
	}

}
