package com.xaut.service;

import com.xaut.dao.QDRecordDao;
import com.xaut.dao.StudentDao;
import com.xaut.entity.Student;

public class StudentService {
	private StudentDao sdao = new StudentDao();
	private QDRecordDao qddao=new QDRecordDao();

	public void save(Student s) {
		// ����Ҫ�������ݵ��ж�
		// ����ѧ����Ϣ�Ƿ����

		if (sdao.queryBySphone(s.getSphone()) == true) {
			throw new RuntimeException("ѧ���ֻ����Ѿ���ռ��");
		}

		sdao.saveStudent(s);
	}

	public Student studentLogin(String sphone, String spass) {

		Student s = sdao.queryBySphone2(sphone);
		if (s == null) {
			throw new RuntimeException("���޴���");
		}

		if (!s.getSpassword().equals(spass)) {
			throw new RuntimeException("�û������������");
		}

		return s;
	}

	/**
	 * �õ��༶�����������Ѿ�ǩ����ѧ������
	 * 
	 * @return
	 */
	public int[] getStuCountAndQDCount(String classno,String day,String time) {
		//ĳ�����������
		int totleCount = sdao.queryStudentCountByClassNo(classno);
		//ĳ���� ĳһ�� ĳ���Ͽ�ʱ��� ���е��õ�����
		int qdcount=qddao.queryQDCount(Integer.parseInt(classno), day, time);
		
		int[] results={totleCount,qdcount};
		
		return results;
		
	}

}
