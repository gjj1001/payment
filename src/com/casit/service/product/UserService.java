package com.casit.service.product;

import com.casit.bean.product.UserInfo;
import com.casit.service.base.DAO;

public interface UserService extends DAO {

	public boolean isUserExist(String name);
	public boolean isPwdExit(String pwd);
	public UserInfo find(String name);
	public boolean isTelExist(String tel);
}
