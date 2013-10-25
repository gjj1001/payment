package com.casit.tee686.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.product.UserInfo;
import com.casit.service.product.UserService;

/**
 * Servlet implementation class UserInfoServlet
 */
@Component
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Resource
    UserService us;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		String uname = new String(request.getParameter("uname").getBytes("iso-8859-1"),"utf-8");
		System.out.println(uname);
		UserInfo userInfo = us.find(uname);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		new ObjectMapper().writeValue(out, userInfo); 
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
