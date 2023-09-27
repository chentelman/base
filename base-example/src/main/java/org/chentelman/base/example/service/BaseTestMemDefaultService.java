package org.chentelman.base.example.service;

import org.chentelman.base.example.dao.BaseTestMemDao;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemDefaultServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemDefaultService extends BaseMemDefaultServiceImpl<BaseTestEntity, Long> {

	public BaseTestMemDefaultService(BaseTestMemDao dao) {
		super(BaseTestEntity.class, dao);
	}
}



