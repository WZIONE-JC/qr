package com.xaut.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xaut.dao.QDRecordDao;

import com.xaut.dao.StudentDao;
import com.xaut.entity.QDRecord;

import com.xaut.entity.Student;

public class QDRecordService {
	private QDRecordDao qddao = new QDRecordDao();
	private StudentDao sdao = new StudentDao();
	
	public void saveRecord(QDRecord q, int classNo) {

		// ����һϵ�е�У���߼�

		// ���ѧ���Ƿ����
		int sno = q.getStudentno();

		Student dbs = sdao.queryBySNO(sno);
		if (dbs == null) {// ����ѧ���ı���Ҳ���ѧ��
			throw new RuntimeException("ѧ����Ϣ������");
		}

		// ѧ���Ƿ����ڵ�ǰ���ڵİ༶
		// ѧ���İ༶���� == ��ǰ���ڵİ༶�ı��
		int dbclassno = dbs.getClassno();// ͨ��ѧ����Ż�ȡ����ѧ�������ݿ��е� �����༶���
		if (dbclassno != classNo) {
			throw new RuntimeException("��ѧ�������ڵ�ǰǩ���༶");
		}

		// ��
		// ֻ����ǩ��һ�Ρ���ǰǩ��ʱ�� > �������һ��ǩ��ʱ�� ����Сʱ��
		// ��

		boolean result = qddao.getQDData(q.getStudentno(), q.getQdate1(), q
				.getQstarttime1());
		if (result == true) {
			throw new RuntimeException("���ÿ��ѳɹ�ǩ��,�����ظ�ǩ��");  
		}

		SimpleDateFormat f1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");

		q.setQdate1(f1.format(new Date()));
		q.setQtime1(f2.format(new Date()));
		q.setQstarttime1(q.getQstarttime1());

		qddao.saveRecord(q);

	}
	
	public List<QDRecord> queryStudentQDRecord(int stuno) {
		return qddao.queryStudentQDRecord(stuno);
	}

}
