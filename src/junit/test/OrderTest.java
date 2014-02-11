package junit.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.casit.bean.entity.Comment;
import com.casit.bean.entity.Message;
import com.casit.bean.entity.PayOrder;
import com.casit.bean.entity.UserInfo;
import com.casit.service.CollectionService;
import com.casit.service.CommentService;
import com.casit.service.MessageService;
import com.casit.service.OrderService;
import com.casit.service.UserService;

public class OrderTest {

	private static ApplicationContext cxt;
	private static OrderService os;
	private static UserService us;
	private static CommentService cs;
	private static CollectionService cols;
	private static MessageService ms;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cxt = new ClassPathXmlApplicationContext("beans.xml");
		os = (OrderService) cxt.getBean("orderServiceBean");
		us = (UserService) cxt.getBean("userServiceBean");
		cs = (CommentService) cxt.getBean("commentServiceBean");
		cols = (CollectionService) cxt.getBean("collectionServiceBean");
		ms = (MessageService) cxt.getBean("messageServiceBean");
	}

	@Test
	public void testSave() {		
		SimpleDateFormat formattime = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMdd");		
		for(int i=0; i<10; i++) {
			PayOrder order = new PayOrder();
			String time = formattime.format(new Date());
			String date = formatdate.format(new Date());
			order.setOrderTime(time);
			order.setOrderDate(date);
			order.setTransAmt("600");
			os.save(order);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	@Test
	public void testGetFirstResult() {
		System.out.println(os.getFirstResult());
	}
	
	@Test
	public void testRequestForPost() throws IOException {
		URL url = new URL("http://192.168.1.45:19840/payment/servlet/CallbackServlet");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = null;
			while((result=reader.readLine())!=null) {
				System.out.println(result);
			}
			reader.close();
		}
		conn.disconnect();
	}
	
	@Test
	public void testJson() throws IOException {
		
		URL url = new URL("http://localhost:19840/payment/UserInfoServlet?uname=xiner");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		StringBuffer json = new StringBuffer();
		ObjectMapper objectMapper = new ObjectMapper();
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = null;
			while((result=reader.readLine())!=null) {
				System.out.println(result);
				json.append(result);
			}
			reader.close();
		}
		conn.disconnect();
		System.out.println(json.toString());
		UserInfo userInfo = objectMapper.readValue(json.toString(), UserInfo.class);
		String str = objectMapper.writeValueAsString(userInfo);
		System.out.println(str);
		System.out.println(userInfo.getUsername());
	}
	
	@Test
	public void testCreate() {
		us.save(new UserInfo("+8615680557316", "高", "123", "MAN",
				"19841001", "四川", "成都", "20131018", "QQ",""));
	}
	
	@Test
	public void testFind() {
		List<Comment> comments = new ArrayList<Comment>();
//		comments = cs.find("content", "好");
		comments = cs.find("pubtime", "2014-01-10,09:39:27");
		System.out.println(comments.size());
		
		for(int i=0; i<comments.size(); i++) {
			System.out.println(comments.get(i));
		}			
		
	}
	
	@Test
	public void deleteCollection() {
		cols.delete("2013-12-27,11:42:10");
	}
	
	@Test
	public void comSave() {
		Comment comment = new Comment();
		cs.save(comment);
	}
	
	@Test
	public void findMessages() {
		for(Message message : ms.find("士力加", "gao")) {
			System.out.println(message.getSend_person());
		}
	}
}
