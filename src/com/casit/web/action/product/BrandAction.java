package com.casit.web.action.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.casit.bean.QueryResult;
import com.casit.bean.product.Brand;
import com.casit.service.product.BrandService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class BrandAction {
	@Resource
	private BrandService brandService;
	private int firstIndex = 0;
	private int maxResult = 10;
	private QueryResult<Brand> qr;
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
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if(true==query) {
			jpql.append(" and o.name like ?"+(params.size()+1));
			params.add("%"+name+"%");
		}
		qr = brandService.getScrollData(Brand.class, pageIndex, maxResult, jpql.toString(), params.toArray());
		List<Brand> brand = qr.getResultList();
		int totalPage = (int) (qr.getTotalNumber()%maxResult==0? qr.getTotalNumber()/maxResult: qr.getTotalNumber()/maxResult+1);
		ActionContext.getContext().put("brand", brand);		
		ActionContext.getContext().put("currentPage", firstIndex);
		ActionContext.getContext().put("totalPage", totalPage);
		return "brandlist";
	}
}
