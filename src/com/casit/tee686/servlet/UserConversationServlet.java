package com.casit.tee686.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.casit.bean.entity.Message;
import com.casit.service.MessageService;

import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

/**
 * Servlet implementation class ReplyServlet
 */
@Component
public class UserConversationServlet extends HttpServlet {
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
    public UserConversationServlet() {
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
		String audioFlag = request.getParameter("audioflag");
		System.out.println(audioFlag);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		MessageResult msgResult = null;
		String uploadAudioDir = null;		
		String recordTime = request.getParameter("recordtime");
		String reply_person = request.getParameter("reply_person");
		String send_person = request.getParameter("send_person");
		String bitmap = request.getParameter("bitmap");//用户头像
		String send_date = request.getParameter("send_date");
		if (audioFlag==null) {			
			// String filepath = getServletContext().getRealPath("/files");
			File uploadaudio = new File(request.getSession()
					.getServletContext()
					.getRealPath(File.separatorChar + "upload_audio"));
			try {
				if (!uploadaudio.exists()) {
					uploadaudio.mkdirs();
				}
				File audiofile = new File(uploadaudio.getPath()
						+ File.separatorChar + System.currentTimeMillis()
						+ ".pcm");
				FileOutputStream fos = new FileOutputStream(audiofile);
				System.out.println(uploadaudio.getPath());
				byte[] buffer = new byte[2048];
				int len = 0;
				while ((len = request.getInputStream().read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}				
				fos.flush();
				fos.close();
				uploadAudioDir = request.getRequestURL().toString().substring(0,
								request.getRequestURL().toString().lastIndexOf("/") + 1)
						+ "upload_audio/"+ audiofile.getPath().substring(audiofile.getPath().
								lastIndexOf(File.separator) + 1);
				System.out.println(uploadAudioDir);
				List<Message> msgs = new ArrayList<Message>();
				msgs = ms.getScrollData(Message.class).getResultList();
				Message message = msgs.get(msgs.size()-1);
				send_date = message.getSend_date();
				System.out.println(send_date);
				ms.update(uploadAudioDir, send_date);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				responseInfo(response);
			} catch (IOException e) {
				e.printStackTrace();
				responseInfo(response);
			} catch (SecurityException e) {
				e.printStackTrace();
				responseInfo(response);
			}
		} else {
			System.out.println("recordTime:" + recordTime);
			reply_person = new String(reply_person.getBytes("iso-8859-1"),"utf-8");
			send_person = new String(send_person.getBytes("iso-8859-1"),"utf-8");
			Message message = new Message();
//			message.setRecord_path((uploadAudioDir));
			message.setBitmap(bitmap);
			message.setRecordTime(Long.valueOf(recordTime));
			message.setReply_person(reply_person);
			message.setSend_date(send_date);
			message.setSend_person(send_person);
			message.setIfyuyin(true);
			message.setSend_ctn("");
			sendNo++;
			msgResult = CLIENT.sendNotificationWithAlias(sendNo, reply_person,
					send_person, "给您发来一条语音");
			if (null == msgResult) {
				System.out.println("与jpush服务器通信失败");
				PrintWriter writer = response.getWriter();
				writer.write("回复失败");
				writer.flush();
				writer.close();
			} /*
			 * else if(msgResult.getErrcode()==1011) { PrintWriter writer =
			 * response.getWriter(); writer.write("此用户已注销，暂时不能回复");
			 * writer.flush(); writer.close(); } else
			 * if(msgResult.getErrcode()==1003) { PrintWriter writer =
			 * response.getWriter(); writer.write("超过最大字数限制，回复失败");
			 * writer.flush(); writer.close(); }
			 */else if (msgResult.getErrcode() == 1011) {
				System.out.println("errorCode:" + msgResult.getErrcode()
						+ ", errorMsg:" + msgResult.getErrmsg() + ", sendNo:"
						+ msgResult.getSendno());
				ms.save(message);
				PrintWriter writer = response.getWriter();
				writer.write(message.getReply_person() + "已注销账户，暂时无法收到你的消息");
				writer.flush();
				writer.close();
			} else if (msgResult.getErrcode() != 0) {
				System.out.println("alias:"+reply_person);
				System.out.println("errorCode:" + msgResult.getErrcode()
						+ ", errorMsg:" + msgResult.getErrmsg() + ", sendNo:"
						+ msgResult.getSendno());
				PrintWriter writer = response.getWriter();
				writer.write("回复失败");
				writer.flush();
				writer.close();
			} else {
				ms.save(message);
				PrintWriter writer = response.getWriter();
				writer.write("回复成功");
				writer.flush();
				writer.close();
			}

		}
		
	}
	
	/**音频上传失败
	 * @param response
	 * @throws IOException
	 */
	private void responseInfo(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write("音频上传失败");
		out.flush();
		out.close();
	}

}
