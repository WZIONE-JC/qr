package com.xaut.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.xaut.entity.QDRecord;
import com.xaut.service.QDRecordService;

public class QDServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public QDServlet() {
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
		String sno = request.getParameter("sno");
		 
		String stime=request.getSession().getAttribute("startTime").toString();
		String uuid = request.getParameter("uuid");// ��ȡ���û������uuidֵ

		QDRecordService qs = new com.xaut.service.QDRecordService();
		QDRecord q = new QDRecord();
		q.setQstarttime1(stime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		q.setQdate1(sdf.format(new Date()));
		
		// ��session�л�ȡ��ά���ж�Ӧ�ĵ�ǰ���ڰ༶���
		int classNo = Integer.parseInt(request.getSession().getAttribute(
						"sessionClassID").toString());

		// ���û����ݵ��������application��Χ�е�ֵ���бȽ�
		Map<String, String> map = (Map<String, String>) getServletContext()
				.getAttribute("random");
		String applicationRandom = map.get(classNo + "");// ͨ���༶�ı�ţ���ȡ����༶��Ӧ��
															// �����
		System.out.println("ѧ����ʼǩ����:" + uuid + "\t\t" + applicationRandom);
		
		if (!uuid.equals(applicationRandom)) {
			System.out.println("���Ϸ���ǩ��--->>>>>>>> :" + uuid + "\t\t"
					+ applicationRandom + "\t\t" + sno);

			response.getWriter().println("��ά����ʧЧ���߶�ά��Ƿ�");
			return;
		}
		try {
			q.setStudentno(Integer.parseInt(sno));

			qs.saveRecord(q, classNo);
			response.getWriter().println("����ǩ���ɹ�");// ����ǳɹ�ǩ��������������
			// ����ǩ���ɹ���ֻҪ���������Ϣ�����ʾ����
			System.out.println(sno + " ǩ���ɹ�");
		} 
		catch (Exception e){

			System.err.println("ǩ�������з����˴���:" + e.getMessage());

			// ע��Ŀǰ�����ֿ��ܷ����쳣 1 ѧ����Ų����ڣ�2 ѧ�������ڵ�ǰ���ڵİ༶
			if (e.getMessage().equals("ѧ����Ϣ������")) {

				// ���ͻ��˵�cookie���
				Cookie cks[] = request.getCookies();
				for (Cookie cookie : cks) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				response.getWriter().println(e.getMessage());
			}

			else if (e.getMessage().startsWith("For input string")) {

				// ���ͻ��˵�cookie���
				Cookie cks[] = request.getCookies();
				for (Cookie cookie : cks) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				response.getWriter().println("ǩ�������з����˴���,�����µ�½");
			} else {
				response.getWriter().println(e.getMessage());

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
