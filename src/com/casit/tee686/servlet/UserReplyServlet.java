package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.entity.Comment;
import com.casit.bean.entity.Message;
import com.casit.service.CommentService;
import com.casit.service.MessageService;

import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

/**
 * Servlet implementation class ReplyServlet
 */
@Component
public class UserReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JPushClient CLIENT = new JPushClient("29b4bb13d73aa4e3e16c976b", "0c6565d21d7f600ba059b4c6",
			864000);
    private static int sendNo = getRandomSenNo();
    public static final int MAX = Integer.MAX_VALUE / 2;
    public static final int MIN = MAX / 2;
    @Resource
    CommentService cs;
    @Resource
    MessageService ms;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	private static int getRandomSenNo() {
		// TODO Auto-generated method stub
		return (int) (MIN+Math.random()*(MAX-MIN));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String audio = request.getParameter("audio");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		MessageResult msgResult = null;
		StringBuffer data = new StringBuffer();
		byte[] buffer = new byte[1024];
		while(request.getInputStream().read(buffer) != -1) {
			data.append(new String(buffer, "utf-8"));
		}
		System.out.println(data.toString());
		Comment value = new ObjectMapper().readValue(data.toString(), Comment.class);
		if(value!=null) {			
			sendNo++;
//			Map<String, Object> extra = new HashMap<String, Object>();
//			extra.put("headimage", value.getHeadimage());
//			extra.put("comtime", value.getComtime());
			msgResult = CLIENT.sendNotificationWithAlias(sendNo, value.getReply(),
					value.getUsername(), "回复了您的评论"); 
		}
		if(null==msgResult) {
			System.out.println("与jpush服务器通信失败");
		} /* else if(msgResult.getErrcode()==1011) {
			PrintWriter writer = response.getWriter();
			writer.write("此用户已注销，暂时不能回复");
			writer.flush(); 
			writer.close();
		} else if(msgResult.getErrcode()==1003) {
			PrintWriter writer = response.getWriter();
			writer.write("超过最大字数限制，回复失败");
			writer.flush(); 
			writer.close();
		}*/ else if(msgResult.getErrcode()!=0) {
			System.out.println("errorCode:"+msgResult.getErrcode()+", errorMsg:"+msgResult.getErrmsg()+
					", sendNo:"+msgResult.getSendno());
		} else {
			cs.save(value);
			Message message = new Message(value.getComContent(), value.getUsername(), value.getComtime(),
					value.getHeadimage());
			message.setReply_person(value.getReply());
			ms.save(message);
			PrintWriter writer = response.getWriter();
			writer.write("回复成功");
			writer.flush(); 
			writer.close(); 
		}
	}

}
