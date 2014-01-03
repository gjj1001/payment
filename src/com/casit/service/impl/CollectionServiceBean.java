package com.casit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.entity.Collection;
import com.casit.service.CollectionService;
import com.casit.service.base.DaoSupport;
@Service @Transactional
public class CollectionServiceBean extends DaoSupport implements
		CollectionService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Collection> find(String name) {
		Query query = em.createQuery("select o from Collection o where o.uname=?1");
		query.setParameter(1, name);
		List<Collection> collections = new ArrayList<Collection>();
		collections = query.getResultList();
		return collections;
	}
	@Override
	public void delete(String sendtime) {
		Query query = em.createQuery("delete from Collection o where o.sendtime=?1");
		query.setParameter(1, sendtime);
		query.executeUpdate();
	}
}
