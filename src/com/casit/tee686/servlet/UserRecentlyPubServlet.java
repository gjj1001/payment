package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.QueryResult;
import com.casit.bean.entity.PubContent;
import com.casit.bean.entity.UserInfo;
import com.casit.service.PublishService;

/**
 * Servlet implementation class UserRecentlyPubServlet
 */
@Component
public class UserRecentlyPubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource   
	PublishService ps;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRecentlyPubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//		orderby.put("pubId", "desc");
		String uname = new String(request.getParameter("uname").getBytes("iso-8859-1"),"utf-8");
		List<PubContent> pubContents = ps.find(uname);
		Collections.reverse(pubContents);
		PrintWriter out = response.getWriter();
		new ObjectMapper().writeValue(out, pubContents.get(0));		
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
