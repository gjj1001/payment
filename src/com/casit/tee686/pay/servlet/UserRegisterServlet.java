package com.casit.tee686.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.casit.bean.product.UserInfo;
import com.casit.service.product.UserService;

/**
 * 用户注册
 */
@Component 
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String UID = "username";//用户名
	public static String PWD = "pwd";//密码
	public static String BIR = "birthday";//生日
	public static String PVC = "province";//省份
	public static String CITY = "city";//城市
	public static String SEX = "sex";//性别
	public static String REG = "regtime";//注册时间
	public static String PIC = "head_image_url";//头像地址
	public static String TEL = "mobile";//电话
	public static String PLA = "platform";//第三方登陆平台
	
	@Resource
	UserService us;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/*String username = new String(request.getParameter(UID).getBytes("iso-8859-1"),"utf-8");
		String password = new String(request.getParameter(PWD).getBytes("iso-8859-1"),"utf-8");
		String birthday = request.getParameter(BIR);
		String province = new String(request.getParameter(PVC).getBytes("iso-8859-1"),"utf-8");
		String city = new String(request.getParameter(CITY).getBytes("iso-8859-1"),"utf-8");
		String sex = request.getParameter(SEX);
		String tel = request.getParameter(TEL);
		String regtime = request.getParameter(REG);
		String picurl = request.getParameter(PIC);
		String platform = request.getParameter(PLA);*/
		StringBuffer data = new StringBuffer();
		byte[] buffer = new byte[1024];
		while(request.getInputStream().read(buffer) != -1) {
			data.append(new String(buffer, "utf-8"));
		}
		System.out.println(data.toString());
		UserInfo userInfo = new ObjectMapper().readValue(data.toString(), UserInfo.class);	
		response.setContentType("txt/plain");
		response.setCharacterEncoding("utf-8");
		if(us.isUserExist(userInfo.getUsername())) {
			PrintWriter out = response.getWriter();
			out.write("该用户已存在，无需再次注册");
			out.flush();
			out.close();
		} else {
			us.save(userInfo);
			PrintWriter out = response.getWriter();
			out.write("注册成功");
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
