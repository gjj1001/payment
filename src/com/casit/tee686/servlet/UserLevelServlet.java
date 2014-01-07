package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.casit.service.UserService;

/**
 * Servlet implementation class UserLevelServlet
 */
@Component
public class UserLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource
	UserService us;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLevelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = new String(request.getParameter("uname").getBytes("iso-8859-1"),"utf-8");
		String num = new String(request.getParameter("num").getBytes("iso-8859-1"),"utf-8");
		System.out.println("为"+uname+"更新"+num+"v币");
		us.updateTm(Integer.valueOf(num), uname);
		response.setContentType("text/plain");   
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write("增加"+num+"v币");	
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
