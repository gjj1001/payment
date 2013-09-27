package com.casit.service.product.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casit.service.base.DaoSupport;
import com.casit.service.product.ProductTypeService;

@Service @Transactional
public class ProductTypeServiceBean extends DaoSupport implements ProductTypeService {

	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		if(entityids!=null && entityids.length>0) {			
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<entityids.length; i++) {
				jpql.append("?").append(i+2).append(",");
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update ProductType o set o.visable=?1 where o.typeId in ("+jpql.toString()+")")
			.setParameter(1, false);
			for(int i=0; i<entityids.length; i++) {
				query.setParameter(i+2, entityids[i]);
			}
			query.executeUpdate();
		}
	}
	
}
