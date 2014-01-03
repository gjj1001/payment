package com.casit.service;

import com.casit.bean.entity.UserInfo;
import com.casit.service.base.DAO;

public interface UserService extends DAO {

	public boolean isUserExist(String name);
	public boolean isPwdExit(String pwd);
	public UserInfo find(String name);
	public UserInfo queryUserById(int id);
	public boolean isTelExist(String tel);
}
