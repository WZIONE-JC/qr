package com.xaut.service;

import java.util.ArrayList;
import java.util.List;

import com.xaut.dao.TeacherClassDao;
import com.xaut.dao.TeacherDao;
import com.xaut.entity.Teacher;
import com.xaut.entity.Teacherclass;

/**
 * ��ʦҵ���߼���
 * 
 * @author Administrator
 * 
 */
public class TeacherService {

	private TeacherDao tdao = new TeacherDao();
	private TeacherClassDao tcdao = new TeacherClassDao();

	/**
	 * ��ʦ��¼���߼�
	 * 
	 * @param tname
	 * @param tpass
	 * @return
	 */
	public Teacher teacherLogin(String tname, String tpass) {

		Teacher t = tdao.queryByTeacherPhone(tname);

		if (t == null) {// �����ʦû���ҵ�
			throw new RuntimeException("���޴���");// javaǿ����쳣�������
		}

		// ��ʾ���ݿ���������������벻һ��
		if (!t.getTpassword().equals(tpass)) {
			throw new RuntimeException("�û��������������");// javaǿ����쳣�������
		}

		return t;

	}

	/**
	 * ��ѯ��ʦ���е�����
	 * 
	 * @return
	 */
	public List<Teacher> queryAll() {
		String sql = "select * from teacher";
		return tdao.queryAll();

	}

	/**
	 * ������ʦ��Ϣ
	 * 
	 * @param t
	 * @param classid
	 *            һ����ʦ��Ӧ����༶
	 */
	public void saveTeacher(Teacher t, String classid[]) {
		// ����Ҫ����teacher
		int tid = tdao.saveTeacher(t);
		// ������ʦ��Ӧ�İ༶
		for (String cid : classid) {
			Teacherclass tc = new Teacherclass();
			tc.setDictionaryid(Integer.parseInt(cid));
			tc.setTeacherid(tid);
			tcdao.save(tc);
		}

	}
	
	
	public List<Teacher> queryClassTeacher(String sno) {
		return tdao.queryClassTeacher(sno);
	}
	//�޸���ʦ����
	public int modifyPasswd(int teacherid,String ModifyPasswd){
		return tdao.modifyPasswd(teacherid,ModifyPasswd);
	}

}
