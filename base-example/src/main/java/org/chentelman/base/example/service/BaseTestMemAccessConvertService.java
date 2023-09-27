package org.chentelman.base.example.service;

import org.chentelman.base.example.converter.BaseTestDomainConverter;
import org.chentelman.base.example.dao.BaseTestMemAccessDao;
import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemConvertAccessServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemAccessConvertService extends BaseMemConvertAccessServiceImpl<BaseTestDomain, BaseTestEntity, Long> {

	public BaseTestMemAccessConvertService(BaseTestMemAccessDao dao) {
		super(BaseTestDomain.class, dao, new BaseTestDomainConverter());
	}
}



