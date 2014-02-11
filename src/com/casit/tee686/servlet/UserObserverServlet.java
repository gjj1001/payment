package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.entity.Observer;
import com.casit.service.ObserverService;

/**
 * Servlet implementation class UserRelationshipServlet
 */
@Component
public class UserObserverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Resource
    ObserverService os;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserObserverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String uname = request.getParameter("uname");//parent
		String username = request.getParameter("username");//child
		String headimage = request.getParameter("headimage");//child
		String headimg = request.getParameter("headimg");//parent
		String del = request.getParameter("del");
		String add = request.getParameter("add");
		String check = request.getParameter("check");
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//		orderby.put("pubId", "desc");
		if(del!=null) {
			uname = new String(uname.getBytes("iso-8859-1"), "utf-8");
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			os.delete(uname, username);
			PrintWriter out = response.getWriter();
			out.write("删除成功");	
			out.flush();
			out.close();
		} else if(add!=null) {
			uname = new String(uname.getBytes("iso-8859-1"), "utf-8");
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			System.out.println("uname:"+uname+" username:"+username+" headimage:"+headimage+
					" headimg:"+headimg);			
			os.add(uname, username, headimage, headimg);
			PrintWriter out = response.getWriter();
			out.write("关注成功");	
			out.flush();
			out.close();
		} else if(check!=null) {
			uname = new String(uname.getBytes("iso-8859-1"), "utf-8");
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			try {
				Observer observer = os.find(uname, username);			
			} catch(NoResultException e) {
				PrintWriter out = response.getWriter();
				out.write("2");//未关注
				out.flush();
				out.close();
			} catch (Exception e) {
				PrintWriter out = response.getWriter();
				out.write("2");//未关注
				out.flush();
				out.close();
			}		
			PrintWriter out = response.getWriter();
			out.write("1");//已关注
			out.flush();
			out.close();
		} else if(uname!=null) {
			uname = new String(uname.getBytes("iso-8859-1"), "utf-8");
			List<Observer> observers = new ArrayList<Observer>();
			observers = os.find(uname, 1);
			Collections.reverse(observers);
			JsonFactory factory = new JsonFactory();
			PrintWriter out = response.getWriter();
			JsonGenerator generator = factory.createJsonGenerator(out);
			out.append("[");
			for(int i=0; i<observers.size(); i++) {
				new ObjectMapper().writeValue(generator, observers.get(i));
				out.append(",");
			}			
			out.append("]");
			out.flush(); 
			out.close();
		} else if(username!=null) {
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			List<Observer> fans = new ArrayList<Observer>();
			fans = os.find(username,2);
			Collections.reverse(fans);
			JsonFactory factory = new JsonFactory();
			PrintWriter out = response.getWriter();
			JsonGenerator generator = factory.createJsonGenerator(out);
			out.append("[");
			for(int i=0; i<fans.size(); i++) {
				new ObjectMapper().writeValue(generator, fans.get(i));
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
