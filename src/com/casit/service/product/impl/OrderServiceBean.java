package com.casit.service.product.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.casit.service.base.DaoSupport;
import com.casit.service.product.OrderService;
@Service @Transactional
public class OrderServiceBean extends DaoSupport implements OrderService {

	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public String getFirstResult() {
		Query query = em.createNativeQuery("select orderdate from PayOrder order by orderId desc limit 0,1");
		String orderDate = (String) query.getSingleResult();
		return orderDate;	
		
	}

}
