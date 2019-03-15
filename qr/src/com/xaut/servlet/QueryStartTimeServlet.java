package com.xaut.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xaut.entity.Dictionary;
import com.xaut.entity.Starttime;
import com.xaut.util.DBManager;

/**
 * Servlet implementation class QueryStartTime
 */
@WebServlet("/QueryStartTime")
public class QueryStartTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db = new DBManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStartTimeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		
		String sql="select stime,iswork from starttime WHERE tid="+teacherid;
		// ArrayList��ʵ�������鶯̬���ݵ�һ��List�ӿڵ�ʵ����
		List<Starttime> list = new ArrayList<Starttime>();
		
		ResultSet rs = db.query(sql);
		try {
			while (rs.next()) {
				// һ����¼��Ӧһ��java�����
				Starttime s = new Starttime();
				s.setStime(rs.getString("stime"));
				s.setIswork(rs.getInt("iswork"));
				list.add(s);// �򼯺����������
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConn();
		
		System.out.println(JSON.toJSONString(list));
		response.getWriter().println(JSON.toJSONString(list));
		
	}

}
