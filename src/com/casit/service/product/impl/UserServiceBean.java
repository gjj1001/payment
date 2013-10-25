package com.casit.service.product.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.product.UserInfo;
import com.casit.service.base.DaoSupport;
import com.casit.service.product.UserService;
@Service @Transactional
public class UserServiceBean extends DaoSupport implements UserService {
	private String uname;
	private String mobile;
	
	@Override
	public boolean isUserExist(String name) {		
		Query query = em.createNativeQuery("select username from userinfo where username="+"\""+name+"\"");
		try{
			uname = (String)query.getSingleResult();
		} catch(NoResultException e) {
			e.printStackTrace();
			return false;
		}
		return name.equals(uname);
		
	}

	@Override
	public boolean isPwdExit(String pwd) {
		Query query = em.createNativeQuery("select pwd from userinfo where pwd="+pwd);
		String password = (String)query.getSingleResult();
		if(pwd.equals(password)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isTelExist(String tel) {		
		Query query = em.createNativeQuery("select mobile from userinfo where mobile="+"\""+tel+"\"");
		try{
			mobile = (String)query.getSingleResult();
		} catch(NoResultException e) {
			e.printStackTrace();
			return false;
		}
		return tel.equals(mobile);
	}
			

	@Override
	public UserInfo find(String name) {
		Query query = em.createQuery("select o from UserInfo o where o.username=?1");
		query.setParameter(1, name);
		UserInfo userinfo = (UserInfo)query.getSingleResult();
		return userinfo;
	}

}
