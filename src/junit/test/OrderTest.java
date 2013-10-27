package junit.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.casit.bean.product.PayOrder;
import com.casit.bean.product.UserInfo;
import com.casit.service.product.OrderService;
import com.casit.service.product.UserService;

public class OrderTest {

	private static ApplicationContext cxt;
	private static OrderService os;
	private static UserService us;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cxt = new ClassPathXmlApplicationContext("beans.xml");
		os = (OrderService) cxt.getBean("orderServiceBean");
		us = (UserService) cxt.getBean("userServiceBean");
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
				"19841001", "四川", "成都", "20131018", "QQ"));
	}
}
