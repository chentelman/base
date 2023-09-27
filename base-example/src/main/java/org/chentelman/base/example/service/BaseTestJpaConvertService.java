package org.chentelman.base.example.service;

import org.chentelman.base.example.converter.BaseTestDomainConverter;
import org.chentelman.base.example.converter.BaseTestEntityConverter;
import org.chentelman.base.example.dao.BaseTestJpaDao;
import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.jpa.service.BaseJpaConvertServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestJpaConvertService extends BaseJpaConvertServiceImpl<BaseTestDomain, BaseTestEntity, Long> {

	public BaseTestJpaConvertService (BaseTestJpaDao dao) {
		super(BaseTestDomain.class, dao, new BaseTestDomainConverter(), new BaseTestEntityConverter());
	}
}



