package com.casit.tee686.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.casit.bean.entity.PayOrder;
import com.casit.service.OrderService;
import com.casit.tee686.pay.utils.GenerateMerSign;
import com.chinaums.pay.api.PayException;
import com.chinaums.pay.api.entities.NoticeEntity;
import com.chinaums.pay.api.impl.DefaultSecurityService;
import com.chinaums.pay.api.impl.UMSPayServiceImpl;

/**
 * Servlet implementation class NotifyReceiverServlet
 */
@Component //@Controller @Scope("prototype")
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
		System.out.println("���յ�֪ͨ!");		
		noticeEntity.parseFromHttpServletRequest(request);
		String signature = noticeEntity.getSignature();        
        boolean verified = false;
        //ʹ��֧������Կ��ǩ��
        try {
            verified = dss.verify(signature, GenerateMerSign.getMerSign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //��֤ǩ��ͨ��
        if (verified) {
        	//��ݽ���״̬����ҵ���߼�
        	setOrder();
        	os.save(order);
        	//������״̬�ɹ�������ҵ���߼��ɹ�����дsuccess
        	try {
				String result= umsImpl.getNoticeRespString(noticeEntity);
				out.print(result);
			} catch (PayException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        } else {
        	System.out.println("����֧����ϵͳ֪ͨ��֤ǩ��ʧ�ܣ����飡");
            out.print("fail");
        }
	}

	/**
	 * ����order������ֵ
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
