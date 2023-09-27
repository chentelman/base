package org.chentelman.base.example.service;

import org.chentelman.base.example.dao.BaseTestEnumDao;
import org.chentelman.base.example.entity.BaseEnumEntity;
import org.chentelman.base.module.data.mem.service.BaseEnumServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestEnumService extends BaseEnumServiceImpl<BaseEnumEntity> {

	public BaseTestEnumService (BaseTestEnumDao dao) {
		super (BaseEnumEntity.class, dao);
	}
}



