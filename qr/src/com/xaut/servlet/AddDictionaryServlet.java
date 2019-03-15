package com.xaut.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xaut.entity.Dictionary;
import com.xaut.service.DictionaryService;

public class AddDictionaryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddDictionaryServlet() {
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

		// �����������Ӧ�ı����ʽ
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// ��ȡ��Ҫ����˭
		String select_xy = request.getParameter("select_xy");
		String select_x = request.getParameter("select_x");
		String select_zy = request.getParameter("select_zy");
		String select_nj = request.getParameter("select_nj");
		String select_bj = request.getParameter("select_bj");
		// ��Ҫ����servlet����ҵ���߼��Ĵ���

		Dictionary d = new Dictionary();
		d.setDbjname(select_bj);
		d.setDnjname(select_nj);
		d.setDxiname(select_x);
		d.setDxyname(select_xy);
		d.setDzyname(select_zy);
     
		DictionaryService ds = new DictionaryService();
		try {
			ds.saveDictionary(d);
			request.setAttribute("msg", "������ݳɹ�");

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());

		}
		
		//���� �ֵ�ҵ���߼��� �в�ѯ�ķ���
 		java.util.List<Dictionary> list= ds.queryAll();
		//���ҽ���ѯ�Ľ�������ĳһ����Χ��
		request.setAttribute("data", list);
		request.setAttribute("flag", 3);  
		
		//���ؽ��
		// �˴���/�ͱ�ʾӦ�ó���ĸ�Ŀ¼ http://localhost:8080/e/
		request.getRequestDispatcher("/leader.jsp").forward(request, response);

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
