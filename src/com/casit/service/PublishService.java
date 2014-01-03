package com.casit.service;

import java.util.List;

import com.casit.bean.entity.PubContent;
import com.casit.service.base.DAO;

public interface PublishService extends DAO {

	List<PubContent> find(String name);
	
}
