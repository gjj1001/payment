package com.casit.web.action.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.casit.bean.QueryResult;
import com.casit.bean.product.Brand;
import com.casit.bean.product.UserInfo;
import com.casit.service.product.BrandService;
import com.casit.service.product.UserService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class BrandAction {
	@Resource
	private UserService us;
	private int firstIndex = 0;
	private int maxResult = 10;
	private QueryResult<UserInfo> qr;
	private boolean query;
	private String name;
	
	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public String execute() throws Exception {
		int pageIndex = firstIndex*maxResult;
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();		
		if(true==query) {
			jpql.append("o.username like ?"+(params.size()+1));
			params.add("%"+name+"%");
		}
		qr = us.getScrollData(UserInfo.class, pageIndex, maxResult, jpql.toString(), params.toArray());
		List<UserInfo> userInfos = qr.getResultList();
		int totalPage = (int) (qr.getTotalNumber()%maxResult==0? qr.getTotalNumber()/maxResult: qr.getTotalNumber()/maxResult+1);
		ActionContext.getContext().put("user", userInfos);		
		ActionContext.getContext().put("currentPage", firstIndex);
		ActionContext.getContext().put("totalPage", totalPage);
		return "userlist";
	}
}
