package com.casit.service;

import com.casit.bean.entity.UserInfo;
import com.casit.service.base.DAO;

public interface UserService extends DAO {

	public boolean isPwdExit(String pwd);
	public UserInfo find(String name);
	public UserInfo queryUserById(int id);
	public boolean isTelExist(String tel);
	void updateTp(int num, String uname);
	void updateTm(int num, String uname);
	boolean isUserExist(String name, String pwd);
	boolean isUserExist(String name);
}
