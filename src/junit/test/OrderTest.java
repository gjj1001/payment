package junit.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.casit.bean.product.PayOrder;
import com.casit.service.product.OrderService;

public class OrderTest {

	private static ApplicationContext cxt;
	private static OrderService os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cxt = new ClassPathXmlApplicationContext("beans.xml");
		os = (OrderService) cxt.getBean("orderServiceBean");
	}

	@Test
	public void testSave() {
		PayOrder order = new PayOrder();
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		String date = format.format(new Date());
		order.setOrderDate(date);
		order.setTransAmt("600");
		os.save(order);
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
}
