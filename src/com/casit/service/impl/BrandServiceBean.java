package com.casit.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.entity.Brand;
import com.casit.service.BrandService;
import com.casit.service.base.DaoSupport;
@Service @Transactional
public class BrandServiceBean extends DaoSupport implements BrandService {

	@Override
	public void save(Object entity) {
		((Brand)entity).setCode(UUID.randomUUID().toString());
		super.save(entity);
	}
	
}
