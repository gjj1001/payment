package com.casit.service;

import java.util.List;

import com.casit.bean.entity.Collection;
import com.casit.service.base.DAO;

public interface CollectionService extends DAO {

	List<Collection> find(String name);

	void delete(String sendtime);

}
