package com.oracle.service;

import java.util.List;
import java.util.Map;
import com.oracle.dao.DictionaryDao;
import com.oracle.entity.Dictionary;

public class DictionaryService {

	private DictionaryDao ddao = new DictionaryDao();

	public List<Dictionary> queryAll() {
		return ddao.queryAll();
	}

	/**
	 * �����ֵ�����
	 * 
	 * @param d
	 */
	public void saveDictionary(Dictionary d) {
		// ���Ƚ���һЩ�ж�
		// ����Ѿ�����ͬ�������ˣ����ܹ�����
		boolean result = ddao.queryByParam(d);
		if (result == true) {
			throw new RuntimeException("�Ѿ��ظ������������ظ����");
		}

		ddao.save(d);

	}
	
	
	public List<String> getYuan() {
		return ddao.getYuan();
	}

	public List<String> getX() {
		return ddao.getX();
	}

	public List<String> getZY() {
		// TODO Auto-generated method stub
		return ddao.getZY();
	}

	public List<String> getNJ() {
		// TODO Auto-generated method stub
		return ddao.getNJ();
	}

	public List<String> getBJ() {
		// TODO Auto-generated method stub
		return ddao.getBJ();
	}

	public  Map<String, Dictionary>  getBJAll() {
		return ddao.getBJAll();
	}
}
