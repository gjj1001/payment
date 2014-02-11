package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.casit.bean.entity.Message;
import com.casit.service.MessageService;

/**
 * Servlet implementation class UserRelationshipServlet
 */
@Component
public class UserMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Resource
    MessageService ms;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String uname = request.getParameter("send_person");//发送者
		String username = request.getParameter("reply_person");//接收者
		String sendtime = request.getParameter("send_date");
		String del = request.getParameter("del");
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//		orderby.put("pubId", "desc");
		if(del!=null) {
			uname = new String(uname.getBytes("iso-8859-1"), "utf-8");
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			ms.delete(uname, username,sendtime);
			PrintWriter out = response.getWriter();
			out.write("删除成功");	
			out.flush();
			out.close();
		} else {
			uname = new String(uname.getBytes("iso-8859-1"), "utf-8");
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			List<Message> msgs = new ArrayList<Message>();
			msgs = ms.find(uname, username);
//			Collections.reverse(fans);
			JsonFactory factory = new JsonFactory();
			PrintWriter out = response.getWriter();
			JsonGenerator generator = factory.createJsonGenerator(out);
			out.append("[");
			for(int i=0; i<msgs.size(); i++) {
				new ObjectMapper().writeValue(generator, msgs.get(i));
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
