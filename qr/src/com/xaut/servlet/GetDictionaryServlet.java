package com.xaut.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xaut.entity.Dictionary;
import com.xaut.service.DictionaryService;

public class GetDictionaryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetDictionaryServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		DictionaryService ds = new DictionaryService();
		PrintWriter pw = response.getWriter();
		// �ڴ˴�Ҫ���� ���ֲ�ͬ������
		// flag�ǿͻ��˴��ݵ�һ��������ʾҪ��ȡ�����ݵ�����
		String flag = request.getParameter("flag");
		if (flag.equals("1")) {// ��ѯѧԺ
			List<String> yuanlist = ds.getYuan();
			// �����ϱ�Ϊ�ַ�������json��ʽ�������ݵĴ���
			StringBuffer str = new StringBuffer();
			for (String string : yuanlist) {
				str.append(string + ",");
			}
			System.out.println(str);
			pw.print(str);
		} else if (flag.equals("2")) {
			List<String> yuanlist = ds.getX();
			// �����ϱ�Ϊ�ַ�������json��ʽ�������ݵĴ���
			StringBuffer str = new StringBuffer();
			for (String string : yuanlist) {
				str.append(string + ",");
			}
			pw.print(str);
		} else if (flag.equals("3")) {
			List<String> yuanlist = ds.getZY();
			// �����ϱ�Ϊ�ַ�������json��ʽ�������ݵĴ���
			StringBuffer str = new StringBuffer();
			for (String string : yuanlist) {
				str.append(string + ",");
			}
			pw.print(str);
		} else if (flag.equals("4")) {
			List<String> yuanlist = ds.getNJ();
			// �����ϱ�Ϊ�ַ�������json��ʽ�������ݵĴ���
			StringBuffer str = new StringBuffer();
			for (String string : yuanlist) {
				str.append(string + ",");
			}
			pw.print(str);
		} else if (flag.equals("5")) {
			List<String> yuanlist = ds.getBJ();  
			// �����ϱ�Ϊ�ַ�������json��ʽ�������ݵĴ���
			StringBuffer str = new StringBuffer();
			for (String string : yuanlist) {
				str.append(string + ",");
			}
			pw.print(str);
		} else {

			// if (flag.equals("4")) {
			// xxxѧԺ-xxϵ-xxרҵ-xx��-xx��
			Map<String, Dictionary> map = ds.getBJAll();
			String json = JSON.toJSONString(map);
			System.out.println(json);
			pw.print(json);//���ظ��ͻ��������
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
