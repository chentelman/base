package org.chentelman.base.example.service;

import org.chentelman.base.example.converter.BaseTestDomainConverter;
import org.chentelman.base.example.converter.BaseTestEntityConverter;
import org.chentelman.base.example.dao.BaseTestMemPartitionedDao;
import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemConvertPartitionedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemPartitionedConvertService extends BaseMemConvertPartitionedServiceImpl<BaseTestDomain, BaseTestEntity, Long, String> {

	public BaseTestMemPartitionedConvertService(BaseTestMemPartitionedDao dao) {
		super(BaseTestDomain.class, dao, new BaseTestDomainConverter(), new BaseTestEntityConverter());
	}
}



