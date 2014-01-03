package com.casit.service.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.entity.Comment;
import com.casit.service.CommentService;
import com.casit.service.base.DaoSupport;
@Service @Transactional
public class CommentServiceBean extends DaoSupport implements CommentService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> find(String obj, String parameter) {
		Query query = em.createQuery("select o from Comment o where o."+obj+"=?1");
		query.setParameter(1, parameter);
		List<Comment> comments = query.getResultList();
		return comments;
	}
}
