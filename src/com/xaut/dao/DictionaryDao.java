package com.xaut.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xaut.entity.Dictionary;
import com.xaut.util.DBManager;

public class DictionaryDao {

	private DBManager db = new DBManager();

	/**
	 * ��ѯ���е��ֵ���Ϣ ���ݿ���һ���ֵ��¼ ��Ӧһ��java�� Dictionary �ܶ�����¼�� ��Ӧ����һ�� ���� List ����һ������
	 * 
	 * @return
	 */
	public List<Dictionary> queryAll() {
		// list��һ�����ϵĽӿ�
		// ArrayList��ʵ�������鶯̬���ݵ�һ��List�ӿڵ�ʵ����
		List<Dictionary> list = new ArrayList<Dictionary>();

		String sql = "select * from dictionary order by did desc ";
		ResultSet rs = db.query(sql);

		try {
			while (rs.next()) {
				// һ����¼��Ӧһ��java�����
				Dictionary d = new Dictionary();
				d.setDbjname(rs.getString("dbjname"));
				d.setDid(rs.getInt("did"));
				d.setDnjname(rs.getString("dnjname"));
				d.setDxiname(rs.getString("dxiname"));
				d.setDxyname(rs.getString("dxyname"));
				d.setDzyname(rs.getString("dzyname"));
				list.add(d);// �򼯺����������
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConn();

		return list;

	}

	/**
	 * ���ݴ��ݵ��ֵ����������ݿ��н��в�ѯ ����ҵ��ˣ��򷵻�true ���򷵻� false
	 * 
	 * @param d
	 * @return
	 */
	public boolean queryByParam(Dictionary d) {
		try {
			String sql = "select * from dictionary where dxyname='"
					+ d.getDxyname() + "' AND" + "  dxiname ='"
					+ d.getDxiname() + "'  " + "AND dzyname='" + d.getDzyname()
					+ "'  " + "AND dnjname='" + d.getDnjname() + "' "
					+ "and  dbjname='" + d.getDbjname() + "'";

			return db.query(sql).next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConn();

		return true;

	}

	/**
	 * ��������
	 * 
	 * @param d
	 */
	public void save(Dictionary d) {
		String sql = "insert into dictionary(dxyname,dxiname,dzyname,dnjname,dbjname)"
				+ " values('"
				+ d.getDxyname()
				+ "','"
				+ d.getDxiname()
				+ "','"
				+ d.getDzyname()
				+ "','"
				+ d.getDnjname()
				+ "','"
				+ d.getDbjname() + "')";
		db.update(sql);
	}

	
	
	/**
	 * �õ������ֵ��� ѧԺ���Ʋ��ظ�������
	 * 
	 * @return
	 */
	public List<String> getYuan() {
		List<String> list = new ArrayList<String>();

		String sql = "select DISTINCT dxyname from dictionary";
		ResultSet rs = db.query(sql);

		try {
			while (rs.next()) {

				list.add(rs.getString("dxyname"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.closeConn();

		return list;
	}

	/**
	 * �õ������ֵ��� ѧԺ���Ʋ��ظ�������
	 * 
	 * @return
	 */
	public List<String> getX() {
		List<String> list = new ArrayList<String>();

		String sql = "select DISTINCT dxiname from dictionary";
		ResultSet rs = db.query(sql);

		try {
			while (rs.next()) {

				list.add(rs.getString("dxiname"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.closeConn();

		return list;
	}

	public List<String> getZY() {
		List<String> list = new ArrayList<String>();

		String sql = "select DISTINCT dzyname from dictionary";
		ResultSet rs = db.query(sql);

		try {
			while (rs.next()) {

				list.add(rs.getString("dzyname"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.closeConn();

		return list;
	}

	public List<String> getNJ() {
		List<String> list = new ArrayList<String>();

		String sql = "select DISTINCT dnjname from dictionary";
		ResultSet rs = db.query(sql);

		try {
			while (rs.next()) {

				list.add(rs.getString("dnjname"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.closeConn();

		return list;
	}

	public List<String> getBJ() {
		List<String> list = new ArrayList<String>();

		String sql = "select DISTINCT  dbjname from dictionary";
		ResultSet rs = db.query(sql);

		try {
			while (rs.next()) {

				list.add(rs.getString("dbjname"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.closeConn();

		return list;
	}

	/**
	 * ��ѯȫ���İ༶
	 * 
	 * @return
	 */
	public Map<String, Dictionary> getBJAll() {
		// map�Ǽ�ֵ�Լ���
		Map<String, Dictionary> map = new HashMap<String, Dictionary>();
		String sql = "select * from dictionary";
		ResultSet rs = db.query(sql);

		try {
			while (rs.next()) {
				// һ����¼��Ӧһ��java�����
				Dictionary d = new Dictionary();
				d.setDbjname(rs.getString("dbjname"));
				d.setDnjname(rs.getString("dnjname"));
				d.setDxiname(rs.getString("dxiname"));
				d.setDxyname(rs.getString("dxyname"));
				d.setDzyname(rs.getString("dzyname"));
				map.put(rs.getInt("did") + "", d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.closeConn();

		return map;
	}

	public List<Object[]> getClassStuCount() {

		List<Object[]> list = new ArrayList<Object[]>();

		try {
			String sql = "SELECT	CONCAT(d.dnjname,'��',d.dbjname),	count(s.sno) FROM	student s,	dictionary d where 	s.classno=d.did group by 	d.dnjname,d.dbjname";
			ResultSet rs = db.query(sql);
			while (rs.next()) {

				Object obj[] = new Object[2];
				obj[0] = rs.getObject(1);
				obj[1] = rs.getObject(2);
				list.add(obj);

			}
		} catch (SQLException e) {
 			e.printStackTrace();
		}

		db.closeConn();

		return list;
	}

}
