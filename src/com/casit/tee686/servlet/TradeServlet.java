package com.casit.tee686.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casit.tee686.pay.utils.GenerateMerSign;


/**
 * Servlet implementation class TradeServlet
 */
@WebServlet("/servlet/TradeServlet")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String httpurl = "https://116.228.21.162:8603/merFrontMgr/orderBusinessServlet";
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat timeformat = new SimpleDateFormat("HHmmss");
	private String orderTime = timeformat.format(Calendar.getInstance().getTime());
	private String orderDate = dateformat.format(Calendar.getInstance().getTime());
	private String MerOrderId = System.currentTimeMillis() + "";
	private String TransType = "NoticePay";//��ѽ���
	private String TransAmt = "600";//��λ����
	private String MerId = "";//�̻���
	private String MerTermId = "";//�ն˺�
	private String NotifyUrl = "http://210.75.239.227/payment/servlet/NotifyReceiverServlet";
	private String Reserve = null;
	private String orderDesc;
	private String merSign = GenerateMerSign.getMerSign();
	private String EffectiveTime = "120";//������Ч�ڣ��룩��0Ϊ������Ч
	/**
     * @see HttpServlet#HttpServlet()
     */
    public TradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		orderDesc = request.getParameter("orderDesc");
		try {			
			request.setAttribute("orderTime", orderTime);
			request.setAttribute("orderDate", orderDate);
			request.setAttribute("MerOrderId", MerOrderId);
			request.setAttribute("TransType", TransType);
			request.setAttribute("TransAmt", TransAmt);
			request.setAttribute("MerId", MerId);
			request.setAttribute("MerTermId", MerTermId);
			request.setAttribute("NotifyUrl", NotifyUrl);
			request.setAttribute("Reserve", Reserve);
			request.setAttribute("orderDesc", orderDesc);
			request.setAttribute("merSign", merSign);
			request.setAttribute("EffectiveTime", EffectiveTime);
			request.getRequestDispatcher(httpurl).forward(request, response);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
