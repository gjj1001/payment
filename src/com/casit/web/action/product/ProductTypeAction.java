package com.casit.web.action.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.casit.bean.QueryResult;
import com.casit.bean.product.PayOrder;
import com.casit.service.product.OrderService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class ProductTypeAction { 
	
	@Resource 
	private OrderService os;
	
	private QueryResult<PayOrder> qr;
	private int firstIndex = 0;
	private int maxResult = 10;
	private Integer parentId = 0;
	private boolean query;
	private String date;
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public QueryResult<PayOrder> getQr() {
		return qr;
	}

	public void setQr(QueryResult<PayOrder> qr) {
		this.qr = qr;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public String execute() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orderId", "asc");
		int pageIndex = firstIndex*maxResult;
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
//		params.add(true);
		if(true==query) {
			jpql.append("o.orderDate like ?"+(params.size()+1));
			params.add("%"+date+"%");
		}
		/*else {
			if(parentId!=null&&parentId>0) {
				jpql.append(" and o.parent.typeId=?"+(params.size()+1));
				params.add(parentId);
			}
			else{
				jpql.append(" and o.parent is null");
			}
		}*/
		qr = os.getScrollData(PayOrder.class, pageIndex, maxResult, jpql.toString(), params.toArray(), orderby);
		List<PayOrder> payOrder = qr.getResultList();
		int totalPage = (int) (qr.getTotalNumber()%maxResult==0? qr.getTotalNumber()/maxResult: qr.getTotalNumber()/maxResult+1);
		ActionContext.getContext().put("payOrder", payOrder);		
		ActionContext.getContext().put("currentPage", firstIndex);
		ActionContext.getContext().put("totalPage", totalPage);
		return "list";
	}
	
}
