package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.QueryResult;
import com.casit.bean.entity.PubContent;
import com.casit.service.PublishService;

/**
 * Servlet implementation class UserDownloadImageServlet
 */
@Component
public class UserDownloadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource   
	PublishService ps;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDownloadImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int index = Integer.parseInt(request.getParameter("index"));
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//		orderby.put("pubId", "desc");
		if(username!=null) {
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			System.out.println(username+"发布的内容"); 
			List<PubContent> pubContents = new ArrayList<PubContent>();
			pubContents = ps.find(username);
			Collections.reverse(pubContents);
			JsonFactory factory = new JsonFactory();
			PrintWriter out = response.getWriter();
			JsonGenerator generator = factory.createJsonGenerator(out);
			out.append("[");
			for(int i=0; i<pubContents.size(); i++) {
				new ObjectMapper().writeValue(generator, pubContents.get(i));
				out.append(",");
			}			
			out.append("]");
			out.flush(); 
			out.close();
		} else {
			QueryResult<PubContent> queryResult = ps.getScrollData(PubContent.class);
			int total = queryResult.getTotalNumber().intValue();	
			System.out.println(total);
			List<PubContent> list = queryResult.getResultList();
			Collections.reverse(list);
			JsonFactory factory = new JsonFactory();
			PrintWriter out = response.getWriter();
			JsonGenerator generator = factory.createJsonGenerator(out);
			out.append("[");
			for(int i=0; i<list.size(); i++) {
				new ObjectMapper().writeValue(generator, list.get(i));
				out.append(",");
			}			
			out.append("]");
			out.flush(); 
			out.close();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
