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

import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

import com.casit.bean.entity.Message;
import com.casit.service.MessageService;

/**
 * Servlet implementation class UserConTextServlet
 */
@Component
public class UserConTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JPushClient CLIENT = new JPushClient("29b4bb13d73aa4e3e16c976b", "0c6565d21d7f600ba059b4c6",
			864000);
    private static int sendNo = getRandomSenNo();
    public static final int MAX = Integer.MAX_VALUE / 2;
    public static final int MIN = MAX / 2;   
    
    @Resource
    MessageService ms;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserConTextServlet() {
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
		MessageResult msgResult = null;
		StringBuffer data = new StringBuffer();
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		byte[] buffer = new byte[1024];
		while(request.getInputStream().read(buffer) != -1) {
			data.append(new String(buffer, "utf-8"));
		}
		System.out.println(data.toString());
		Message value = new ObjectMapper().readValue(data.toString(), Message.class);
		if(value!=null) {			
			sendNo++;
//			Map<String, Object> extra = new HashMap<String, Object>();
//			extra.put("headimage", value.getBitmap());
//			extra.put("comtime", value.getSend_date());
			msgResult = CLIENT.sendNotificationWithAlias(sendNo, value.getReply_person(),
					value.getSend_person(), "给您发来一条消息"); 
		}
		if(null==msgResult) {
			System.out.println("与jpush服务器通信失败");
			PrintWriter writer = response.getWriter();
			writer.write("回复失败");
			writer.flush(); 
			writer.close(); 
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
		}*/else if (msgResult.getErrcode()==1011) {
			System.out.println("errorCode:"+msgResult.getErrcode()+", errorMsg:"+msgResult.getErrmsg()+
					", sendNo:"+msgResult.getSendno());
			ms.save(value);
			PrintWriter writer = response.getWriter();
			writer.write(value.getReply_person()+"已注销账户，暂时无法收到你的消息");
			writer.flush(); 
			writer.close(); 
		} else if(msgResult.getErrcode()!=0) {
			System.out.println("errorCode:"+msgResult.getErrcode()+", errorMsg:"+msgResult.getErrmsg()+
					", sendNo:"+msgResult.getSendno());
			PrintWriter writer = response.getWriter();
			writer.write("回复失败");
			writer.flush(); 
			writer.close(); 
		} else {
			ms.save(value);
			PrintWriter writer = response.getWriter();
			writer.write("回复成功");
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
