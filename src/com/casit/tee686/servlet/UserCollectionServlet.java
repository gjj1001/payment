package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.casit.bean.entity.Collection;
import com.casit.service.CollectionService;

/**
 * Servlet implementation class UserCollectionServlet
 */
@Component
public class UserCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource
	CollectionService cs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");	
		String uname = request.getParameter("uname");
		String sendtime = request.getParameter("sendtime");
		System.out.println("sendtime:"+sendtime);
		if(uname!=null) {
			uname = new String(uname.getBytes("iso-8859-1"),"utf-8");
			System.out.println("username:"+uname);
			List<Collection> list = cs.find(uname);
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
		} else if(sendtime!=null) {
//			sendtime = new String(sendtime.getBytes("iso-8859-1"),"utf-8");
			cs.delete(sendtime);
			PrintWriter out = response.getWriter();
			out.write("删除成功");
			out.flush();
			out.close();
		} else {
			StringBuffer data = new StringBuffer();
			byte[] buffer = new byte[1024];
			while(request.getInputStream().read(buffer) != -1) {
				data.append(new String(buffer, "utf-8"));
			}
			System.out.println(data.toString());
			/*if(data.indexOf("file")!=-1) {
				file = data.substring(data.indexOf("file"));
				data = data.delete(data.indexOf("file"), data.length());
				System.out.println(file);
				System.out.println(data.toString());
			}*/
			Collection collection = new ObjectMapper().readValue(data.toString(), Collection.class);		
			cs.save(collection);
			PrintWriter out = response.getWriter();
			out.write("收藏成功");
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
