package com.casit.tee686.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casit.bean.product.PayOrder;
import com.casit.service.product.OrderService;
import com.casit.tee686.pay.utils.GenerateMerSign;
import com.chinaums.pay.api.PayException;
import com.chinaums.pay.api.entities.NoticeEntity;
import com.chinaums.pay.api.impl.DefaultSecurityService;
import com.chinaums.pay.api.impl.UMSPayServiceImpl;

/**
 * Servlet implementation class NotifyReceiverServlet
 */
@WebServlet("/servlet/NotifyReceiverServlet") //@Controller @Scope("prototype")
public class NotifyReceiverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UMSPayServiceImpl umsImpl = new UMSPayServiceImpl();
	NoticeEntity noticeEntity = new NoticeEntity();
	DefaultSecurityService dss = new DefaultSecurityService();
	private PayOrder order = new PayOrder();
	@Resource
	OrderService os;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotifyReceiverServlet() {
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
		System.out.println("接收到通知!");		
		noticeEntity.parseFromHttpServletRequest(request);
		String signature = noticeEntity.getSignature();        
        boolean verified = false;
        //使用支付宝公钥验签名
        try {
            verified = dss.verify(signature, GenerateMerSign.getMerSign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //验证签名通过
        if (verified) {
        	//根据交易状态处理业务逻辑
        	setOrder();
        	os.save(order);
        	//当交易状态成功，处理业务逻辑成功。回写success
        	try {
				String result= umsImpl.getNoticeRespString(noticeEntity);
				out.print(result);
			} catch (PayException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        } else {
        	System.out.println("接收支付宝系统通知验证签名失败，请检查！");
            out.print("fail");
        }
	}

	/**
	 * 设置order各属性值
	 */
	private void setOrder() {
		order.setOrderTime(noticeEntity.getOrderTime());
		order.setOrderDate(noticeEntity.getOrderDate());
		order.setMerOrderId(noticeEntity.getMerOrderId());
		order.setTransType(noticeEntity.getTransType());
		order.setTransAmt(noticeEntity.getTransAmt());
		order.setTransId(noticeEntity.getTransId());
		order.setTransState(noticeEntity.getTransState());
		order.setAccount(noticeEntity.getAccount());
	}	
	
}
