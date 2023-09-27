package org.chentelman.base.example.service;

import org.chentelman.base.example.dao.BaseTestJpaDao;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.jpa.service.BaseJpaDefaultServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestJpaDefaultService extends BaseJpaDefaultServiceImpl<BaseTestEntity, Long> {

	public BaseTestJpaDefaultService (BaseTestJpaDao dao) {
		super(BaseTestEntity.class, dao);
	}
}



