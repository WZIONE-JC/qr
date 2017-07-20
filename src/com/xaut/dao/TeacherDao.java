package com.xaut.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xaut.entity.Teacher;
import com.xaut.util.DBManager;

public class TeacherDao {

	private DBManager db = new DBManager();

	/**
	 * ������ʦ�ĵ绰���������ʦ���� Ϊʲô��¼�ķ���������Ҫ�������أ� �Ƚ�ֱ�۵��Ǵ�ҿ���ͨ���ֻ��ź�����ͬʱ����һ�����ݣ�
	 * DAO���Ǻ����ݿ�򽻵��� Ϊ����dao���еķ�����󻯵ĸ��ã���������Ҫ��dao�ķ�������Ϊԭ�ӷ�����
	 * queryByTeacherPhone����һ���ô��� �ȿ��Բ�����ʦ��Ϣί �����߼��ṩdao��֧�� Ҳ����Ϊ��¼�߼��ṩdao֧�� ����͸�����
	 * 
	 * 
	 * @param phoneNo
	 * @return
	 */
	public Teacher queryByTeacherPhone(String phoneNo) {
		Teacher t = null;
		String sql = " select * from teacher where tphone= '" + phoneNo + "' ";
		ResultSet rs = db.query(sql);
		try {
			while (rs.next()) {// ����鵽������
				t = new Teacher();// �����յ�teacher����
				t.setTid(rs.getInt("tid"));
				t.setTname(rs.getString("tname"));
				t.setTpassword(rs.getString("tpassword"));
				t.setTphone(rs.getString("tphone"));
				t.setTsex(rs.getInt("tsex"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;

	}

	/**
	 * ��ѯ�������е�����
	 * @return
	 */
	public List<Teacher> queryAll() {
		List<Teacher> list = new ArrayList<Teacher>();

		String sql = " select * from teacher  ";
		ResultSet rs = db.query(sql);
		try {
			while (rs.next()) {// ����鵽������
				Teacher t = new Teacher();// �����յ�teacher����
				t.setTid(rs.getInt("tid"));
				t.setTname(rs.getString("tname"));
				t.setTpassword(rs.getString("tpassword"));
				t.setTphone(rs.getString("tphone"));
				t.setTsex(rs.getInt("tsex"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	
	public int saveTeacher(Teacher t) {
 		String sql = "insert into teacher(tphone,tname,tsex,tpassword) values('"
				+ t.getTphone()
				+ "','"
				+ t.getTname()
				+ "',"
				+ t.getTsex()
				+ ",'" + t.getTpassword() + "')";
		String sqlid = "select max(tid) mid from teacher";

		db.update(sql);
		ResultSet rs = db.query(sqlid);
		try {
			rs.next();// ���Ƶ�������
			return rs.getInt("mid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConn();
		return -1;

	}

	
	
}
