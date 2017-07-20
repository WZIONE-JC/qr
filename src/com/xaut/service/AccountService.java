package com.xaut.service;

import com.xaut.dao.AccountDao;

/**
 * ���˺���ص�ҵ���߼������
 * 
 * @author Administrator
 * 
 */
public class AccountService {

	AccountDao adao = new AccountDao();

	/**
	 * �޸��û�����ҵ���߼�����
	 * 
	 * @param phoneNo
	 * @param pass1
	 * @param pass2
	 */
	public void repass(String phoneNo, String pass1, String pass2) {

		// �ж�
		if (!pass1.equals(pass2)) {
			throw new RuntimeException("�������벻һ��");
		}

		String dbpass = adao.getPassByPhoneNo(phoneNo);

		if (dbpass.equals(pass1)) {
			throw new RuntimeException("�����벻�ܹ���������һ��");
		}

		// �޸�����
		adao.repass(phoneNo, pass1);

	}

	public void login(String name, String pass) {
		String dbpass = adao.getPassByPhoneNo(name);
		if (dbpass == null) {// ����û���������
			throw new RuntimeException("�û���������");
		}

		if (!dbpass.equals(pass)) {
			throw new RuntimeException("�û������������");
		}

	}

}
