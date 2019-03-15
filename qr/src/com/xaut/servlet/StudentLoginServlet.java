package com.xaut.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xaut.entity.Student;
import com.xaut.service.StudentService;

public class StudentLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentLoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String sphone = request.getParameter("sphone");
		String spass = request.getParameter("spass");
		// ��¼�Ĳ�����
		StudentService ss = new StudentService();
		Student dbstudent;
		try {
			dbstudent = ss.studentLogin(sphone, spass);
			// Ӧ�ý�ѧ������Ϣ������session���У��Ա��ں����ĻỰ����
			request.setAttribute("dbstudent", dbstudent);
			request.setAttribute("msg", "��¼�ɹ�");

			// ��ѧ����ȷ��¼��֮����Ҫ��ǰ���·�һ������
			// ���Ա�ʾѧ���Ѿ���¼���Ժ���Ҫ��¼
			// �Ự���� cookie
			Cookie c1 = new Cookie("islogin", "true");
			Cookie c2 = new Cookie("sphone", sphone);
			Cookie c3 = new Cookie("sno", dbstudent.getSno()+"");
			c1.setMaxAge(60 * 60 * 24 * 30 * 4);
			c1.setPath("/");

			c2.setMaxAge(60 * 60 * 24 * 30 * 4);    
			c2.setPath("/");
			
			c3.setMaxAge(60 * 60 * 24 * 30 * 4);
			c3.setPath("/");

			
			   
			
			// �����������ڴ��еĶ����·����ͻ�����
			response.addCookie(c1);
			response.addCookie(c2);
			response.addCookie(c3);

			/*request.getRequestDispatcher("/studentPhone.jsp?date="+new Date()).forward(request,
					response);*/
			//�˴�������ʹ������ת��
			//��Ϊcookie�ڴ˴��ɷ������·����ͻ���
			//��ʹ������ת����ʱ�򣬱�ʾ�ɷ����� ���ſͻ��˵������������������һ��ҳ�棬Ȼ��cookie��û�б��������·����ͻ���
			//�����޷���ȡ
			//���ض���һ����Ϊ�ض���ͻ��˻��������������
			response.sendRedirect("../studentPhone.jsp");  
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());

			request.getRequestDispatcher("/studentLogin.jsp?date="+new Date()).forward(request,
					response);

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
