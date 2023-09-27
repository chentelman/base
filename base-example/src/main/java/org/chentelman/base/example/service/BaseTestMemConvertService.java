package org.chentelman.base.example.service;

import org.chentelman.base.example.converter.BaseTestDomainConverter;
import org.chentelman.base.example.converter.BaseTestEntityConverter;
import org.chentelman.base.example.dao.BaseTestMemDao;
import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemConvertServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemConvertService extends BaseMemConvertServiceImpl<BaseTestDomain, BaseTestEntity, Long> {

	public BaseTestMemConvertService(BaseTestMemDao dao) {
		super(BaseTestDomain.class, dao, new BaseTestDomainConverter(), new BaseTestEntityConverter());
	}
}



