package com.xaut.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xaut.entity.QDRecord;
import com.xaut.util.DBManager;

public class QDRecordDao {

	private DBManager db = new DBManager();


	public void saveRecord(QDRecord q) {
		String sql = " insert into qdrecord(studentno,qdate,qtime,qstarttime) "
				+ "values( " + q.getStudentno() + ", '" + q.getQdate1()
				+ "' , '" + q.getQtime1() + "' , '" + q.getQstarttime1()
				+ "' ) ";

		db.update(sql);

	}

	/**
	 * ��ѯĳ���༶��ĳһ�죬ĳһ�ÿε�ǩ��ѧ������
	 * 
	 * @param classno
	 * @param day
	 *            xxxx-xx-xx
	 * @param time
	 *            xx:xx:xx
	 * @return
	 */
	public int queryQDCount(int classno, String day, String time) {
		DBManager db = new DBManager();
		int count = 0;
		try {
			String sql = "select count(studentno) countsno from "
					+ " student s,qdrecord qd where s.sno=qd.studentno and "
					+ " s.classno=" + classno + " and qd.qdate= '" + day + "' "
					+ "  and qd.qstarttime='" + time + "' ";

			ResultSet rs = db.query(sql);
			while (rs.next()) {
				count = rs.getInt("countsno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}

		return count;
	}

	/**
	 * ��ѯĳһ��ѧ����� ��ĳһ���ĳһ�ÿ����Ƿ�ǩ����
	 * 
	 * @param sno
	 * @param day
	 * @param startTime
	 * @return true�����Ѿ�ǩ����
	 */
	public boolean getQDData(int sno, String day, String startTime) {

		try {
			String sql = "select * from qdrecord where studentno=" + sno
					+ " and qdate='" + day + "' and qstarttime='" + startTime
					+ "' ";
			ResultSet rs = db.query(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * ��ѯĳһ��ѧ���ĳ��ڼ�¼
	 * 
	 * @param stuno
	 * @return
	 */
	public List<QDRecord> queryStudentQDRecord(int stuno) {
		List<QDRecord> list = new ArrayList<QDRecord>();
		try {
			String sql = "select  qdate, qstarttime,qtime ,(qtime-qstarttime) cqs    from qdrecord where studentno=  "
					+ stuno;
			ResultSet rs = db.query(sql);  
			while (rs.next()) {
				QDRecord q = new QDRecord();
				q.setQdate(rs.getDate("qdate"));
				Time t1 = rs.getTime("qstarttime");
				q.setQstarttime1(t1.toString()); //���ǽ����ݲ�ѯ��TIme ת��Ϊ�� String �̶���װ���� Qstarttime1
				Time t2 = rs.getTime("qtime");
				q.setQtime1(t2.toString()); 
				q.setCqstate(rs.getInt("cqs")); 
				list.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	
}
