package com.xaut.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xaut.util.DBManager;

public class AccountDao {
	private DBManager db = new DBManager();

	/**
	 * ����ֻ������Ƿ����
	 * 
	 * @param phoneNo
	 * @return
	 */
	public boolean existPhone(String phoneNo) {

		try {
			// String * from account where sphone='1111111'

			String sql = "select * from account where sphone='" + phoneNo
					+ "' ";

			ResultSet rs = db.query(sql);

			return rs.next();// next�ĺ����� �Ƿ�������ݣ���������ڷ���true�����򷵻�false
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * ͨ���ֻ������ȡ����
	 * 
	 * @param phoneNo
	 * @return
	 */
	public String getPassByPhoneNo(String phoneNo) {
		String pass = null;
		try {
			// String * from account where sphone='1111111'

			String sql = "select * from account where sphone='" + phoneNo
					+ "' ";

			ResultSet rs = db.query(sql);
			if (rs.next()) {
				pass = rs.getString("spass");
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass;

	}

	/**
	 * �޸�����
	 * 
	 * @param phoneNo
	 * @param pass
	 */

	public void repass(String phoneNo, String pass) {
		String sql = "update account set spass ='" + pass + "' where sphone='"
				+ phoneNo + "'";

		db.update(sql);
	}

	 
}
