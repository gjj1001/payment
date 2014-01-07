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

import com.casit.bean.entity.Comment;
import com.casit.service.CommentService;

/**
 * Servlet implementation class UserCommentServlet
 */
@Component
public class UserCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Resource
	CommentService cs;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pubtime = request.getParameter("pubtime");
		String comtime = request.getParameter("comtime");
		StringBuffer data = new StringBuffer();
		byte[] buffer = new byte[1024];
		while(request.getInputStream().read(buffer) != -1) {
			data.append(new String(buffer, "utf-8"));
		}
		System.out.println(data.toString());
		System.out.println(pubtime);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		if(pubtime!=null) { 
//			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//			orderby.put("pubId", "desc");
			System.out.println("查询评论"); 
			List<Comment> comments = new ArrayList<Comment>();
			comments = cs.find("pubtime", pubtime);		
			JsonFactory factory = new JsonFactory();
			PrintWriter out = response.getWriter();
			JsonGenerator generator = factory.createJsonGenerator(out);
			out.append("[");
			for(int i=0; i<comments.size(); i++) {
				new ObjectMapper().writeValue(generator, comments.get(i));
				out.append(",");
			}			
			out.append("]");
			out.flush(); 
			out.close();
			System.out.println("发表评论"); 
			
		} else if(comtime!=null) {
			cs.delete(comtime);
			PrintWriter writer = response.getWriter();
			writer.write("删除成功");
			writer.flush(); 
			writer.close(); 
		} else { 		

//			List<Comment> comments = new ArrayList<Comment>();
			Comment value = new ObjectMapper().readValue(data.toString(), Comment.class);			
			/*int id = cs.getScrollData(Comment.class).getTotalNumber().intValue();
			Comment comment = cs.find(Comment.class, id);
			int duration = Integer.parseInt(value.getComtime().substring(value.getComtime().lastIndexOf(":")+1))
					- Integer.parseInt(comment.getComtime().substring(comment.getComtime().lastIndexOf(":")+1));
			System.out.println("本次发表时间:"+Long.parseLong(value.getComtime().substring(value.getComtime().lastIndexOf(":")+1)));
			System.out.println("上次发表时间:"+Long.parseLong(comment.getComtime().substring(comment.getComtime().lastIndexOf(":")+1)));
			System.out.println("两次发表时间间隔为:"+duration+"s");*/			
			cs.save(value);
			PrintWriter writer = response.getWriter();
			writer.write("评论成功");
			writer.flush(); 
			writer.close(); 
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
