package com.casit.service;

import java.util.List;

import com.casit.bean.entity.Observer;
import com.casit.service.base.DAO;

public interface ObserverService extends DAO {

	List<Observer> find(String name, int flag);
	
	Observer find(String uname, String username);

	void delete(String uname, String username);

	void add(String uname, String username, String headimage, String headimg);
	
}
