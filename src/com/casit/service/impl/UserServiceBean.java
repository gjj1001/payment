package com.casit.service.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.entity.UserInfo;
import com.casit.service.UserService;
import com.casit.service.base.DaoSupport;
@Service @Transactional
public class UserServiceBean extends DaoSupport implements UserService {
	private String uname;
	private String mobile;
	private String password;
	
	@Override
	public boolean isUserExist(String name, String pwd) {	
		Query query = em.createQuery("select o from UserInfo o where o.username=?1");
		query.setParameter(1, name);
		try{
			UserInfo user = (UserInfo)query.getSingleResult();
			uname = user.getUsername();
			password = user.getPwd();
			System.out.println("uname:"+uname+" password:"+password);
		} catch(NoResultException e) {
			e.printStackTrace();
			return false;
		}
		return name.equals(uname)?(pwd.equals(password)?true:false):false;
		
	}

	@Override
	public boolean isUserExist(String name) {
		Query query = em.createNativeQuery("select username from userinfo where username="+"\""+name+"\"");
		try {
			uname = (String)query.getSingleResult();
			System.out.println(uname);
		} catch(NoResultException e) {
			e.printStackTrace();
			return false;
		}
		return name.equals(uname);
	}
	
	@Override
	public boolean isPwdExit(String pwd) {
		Query query = em.createNativeQuery("select pwd from userinfo where pwd="+"\""+pwd+"\"");
		try {
			password = (String)query.getSingleResult();
			System.out.println(password);
		} catch(NoResultException e) {
			e.printStackTrace();
			return false;
		}
		return pwd.equals(password);
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

	@Override
	public UserInfo queryUserById(int id) {
		Query query = em.createQuery("select o from UserInfo o where o.userid=?1");
		query.setParameter(1, id);
		UserInfo userinfo = (UserInfo)query.getSingleResult();
		return userinfo;
	}
	
	@Override
	public void updateTp(int num, String uname) {
		Query query = em.createQuery("update UserInfo o set o.tp=o.tp+?1 where o.username=?2");
		query.setParameter(1, num);
		query.setParameter(2, uname);
		query.executeUpdate();
	}
	
	@Override
	public void updateTm(int num, String uname) {
		Query query = em.createQuery("update UserInfo o set o.tm=o.tm+?1 where o.username=?2");
		query.setParameter(1, num);
		query.setParameter(2, uname);
		query.executeUpdate();
	}

}
