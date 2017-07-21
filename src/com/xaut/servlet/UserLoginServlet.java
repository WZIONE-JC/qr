package com.xaut.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xaut.entity.Dictionary;
import com.xaut.service.AccountService;
import com.xaut.service.DictionaryService;
import com.xaut.service.TeacherService;

/**
 * �û���¼servlet
 * 
 * @author Administrator
 * 
 */
public class UserLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLoginServlet() {
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

		// Ҫ������ģ��������ñ����ʽ
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		System.out.println("����Ҫ��½��");
		String sname = request.getParameter("sname");
		String spass = request.getParameter("spass");
		String sf = request.getParameter("sf");

		// ��½���ж�
		if (sf.equals("gly")) {
			// ����Ա��Ҫ��������Աҵ���߼�����
			AccountService as = new AccountService();
			// �������ǲ��õ�ģʽ�� html--��ͨ�����ύ���� servlet -->servlet ��Ҫ���صĲ���һ���ַ���������һ��ҳ��
			try {
				as.login(sname, spass);
				//��Ҫ��ת��leader.jspҳ��֮ǰ�����Ȱ�leader.jspҳ������Ҫ��ѯ�����ݲ�ѯ����
				//���� �ֵ�ҵ���߼��� �в�ѯ�ķ���
				DictionaryService ds=new DictionaryService();
				java.util.List<Dictionary> list= ds.queryAll();
				//���ҽ���ѯ�Ľ�������ĳһ����Χ��
				request.setAttribute("data", list);
				//����ת�������ض���leader.jspҳ����
				//����ת���ſ��Ի�ȡrequest��Χ�е�ֵ
				request.getRequestDispatcher("/leader.jsp").forward(request, response);
				
				//response.sendRedirect("../leader.html");
			} catch (Exception e) {

				// ��Ҫ���������Ϣ������ �ͻ���
				// ���Ƚ���Ϣ������ĳһ����Χ�� request
				request.setAttribute("msg", e.getMessage());

				// response.sendRedirect("../index.html");
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			}

		} else {
			// ��ʦ��½��Ҫ������ʦҵ���߼�����
			// ������ʦ��¼���ж�
			TeacherService ts=new TeacherService();
			try {
				ts.teacherLogin(sname, spass);
				request.setAttribute("teacherId", ts.teacherLogin(sname, spass).getTid());
				//ת������ӭ��ʦ��¼�Ľ���
				request.getRequestDispatcher("/teacher.jsp").forward(request,
						response);
			} catch (Exception e) {
				// ��Ҫ���������Ϣ������ �ͻ���
				// ���Ƚ���Ϣ������ĳһ����Χ�� request
				request.setAttribute("msg", e.getMessage());

				// response.sendRedirect("../index.html");
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			}
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
