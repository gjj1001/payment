/**
 * 
 */
package com.casit.tee686.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Jason
 *������DelegatingFilterProxy����Ĵ��?ͨ��������������ҵ�ʵ�ʵ�Servlet�����ҵ���߼����ܡ� 
 */
public class DelegatingServletProxy extends GenericServlet {
	private String targetBean;  
    private Servlet proxy;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2220948331238558743L;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		proxy.service(req, res);		
	}

	@Override
	public void init() throws ServletException {
		this.targetBean = getServletName();  
        getServletBean();  
        proxy.init(getServletConfig());
	}

	private void getServletBean() {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (Servlet) wac.getBean(targetBean);
		
	}

}
