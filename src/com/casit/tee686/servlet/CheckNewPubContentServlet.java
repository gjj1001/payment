package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.casit.bean.QueryResult;
import com.casit.bean.entity.PubContent;
import com.casit.service.PublishService;

/**
 * Servlet implementation class CheckNewPubContentServlet
 */
@Component
public class CheckNewPubContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource
	PublishService ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckNewPubContentServlet() { 
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QueryResult<PubContent> queryResult = ps.getScrollData(PubContent.class);
		long total = queryResult.getTotalNumber();
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		writer.write(String.valueOf(total));
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
