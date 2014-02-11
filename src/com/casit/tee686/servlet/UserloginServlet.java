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
 * 用户登录
 */
@Component
public class UserloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Resource
	UserService us;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserloginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = new String(request.getParameter("uname").getBytes("iso-8859-1"),"utf-8");
		String pwd = new String(request.getParameter("pwd").getBytes("iso-8859-1"),"utf-8");
		System.out.println("username="+name+" password="+pwd);		
		if(us.isUserExist(name, pwd)) {
			StringBuilder builder = new StringBuilder();
			builder.append("{\"isErr\":\"no\",\"key\":\"success\"}");
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(builder.toString());
			out.flush();
			out.close();
		} else {
			StringBuilder builder = new StringBuilder();
			builder.append("{\"isErr\":\"yes\"}");
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(builder.toString());
			out.flush();
			out.close();
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
