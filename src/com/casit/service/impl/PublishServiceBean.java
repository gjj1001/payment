package com.casit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.entity.PubContent;
import com.casit.service.PublishService;
import com.casit.service.base.DaoSupport;

@Service @Transactional
public class PublishServiceBean extends DaoSupport implements PublishService {

	@SuppressWarnings("unchecked")
	@Override
	public List<PubContent> find(String name) {
		Query query = em.createQuery("select o from PubContent o where o.username=?1");
		query.setParameter(1, name);
		List<PubContent> pubContents = new ArrayList<PubContent>();
		pubContents = query.getResultList();
		return pubContents;
	}
	
	public void delete(String sendtime) {
		Query query = em.createQuery("delete from PubContent o where o.sendtime=?1");
		query.setParameter(1, sendtime);
		query.executeUpdate();
	}
}
