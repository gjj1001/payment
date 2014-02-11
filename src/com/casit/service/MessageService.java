package com.casit.service;

import java.util.List;

import com.casit.bean.entity.Message;
import com.casit.service.base.DAO;

public interface MessageService extends DAO {

	List<Message> find(String uname, String username);

	void delete(String uname, String username, String sendtime);

	void update(String record_path, String send_date);

}
