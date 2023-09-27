package org.chentelman.base.example.service;

import org.chentelman.base.example.dao.BaseTestMemAccessDao;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemDefaultAccessServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemAccessDefaultService extends BaseMemDefaultAccessServiceImpl<BaseTestEntity, Long> {

	public BaseTestMemAccessDefaultService(BaseTestMemAccessDao dao) {
		super(BaseTestEntity.class, dao);
	}
}



