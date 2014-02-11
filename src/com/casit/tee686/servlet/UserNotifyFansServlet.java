package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.entity.Comment;

import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

/**
 * Servlet implementation class ReplyServlet
 */
@Component
public class UserNotifyFansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JPushClient CLIENT = new JPushClient("29b4bb13d73aa4e3e16c976b", "0c6565d21d7f600ba059b4c6",
			864000);
    private static int sendNo = getRandomSenNo();
    public static final int MAX = Integer.MAX_VALUE / 2;
    public static final int MIN = MAX / 2;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNotifyFansServlet() {
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
			Map<String, Object> extra = new HashMap<String, Object>();
//			extra.put("headimage", value.getHeadimage());
//			extra.put("imagefile", value.getImagefile());
//			extra.put("pubtime", value.getPubtime());
//			extra.put("author", value.getAuthor());
//			extra.put("pubcontent", value.getPubContent());
			extra.put("notify", "fans");
			msgResult = CLIENT.sendNotificationWithTag(sendNo, value.getReply(),
					value.getUsername(), "发布了一篇新公告", 0, extra); 
		}
		if(null==msgResult) {
			System.out.println("与jpush服务器通信失败");
		}  /*else if(msgResult.getErrcode()==1011) {
			PrintWriter writer = response.getWriter();
			writer.write("没有粉丝");
			writer.flush(); 
			writer.close();
		} else if(msgResult.getErrcode()==1003) {
			PrintWriter writer = response.getWriter();
			writer.write("超过最大字数限制，无法通知粉丝");
			writer.flush(); 
			writer.close();
		}*/ else if(msgResult.getErrcode()!=0) {
			System.out.println("errorCode:"+msgResult.getErrcode()+", errorMsg:"+msgResult.getErrmsg()+
					", sendNo:"+msgResult.getSendno());
		} else {
			PrintWriter writer = response.getWriter();
			writer.write("通知粉丝发布公告成功");
			writer.flush(); 
			writer.close(); 
		}
	}

}
