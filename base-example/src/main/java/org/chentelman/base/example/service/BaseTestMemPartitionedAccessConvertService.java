package org.chentelman.base.example.service;

import org.chentelman.base.example.converter.BaseTestDomainConverter;
import org.chentelman.base.example.dao.BaseTestMemPartitionedAccessDao;
import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemConvertPartitionedAccessServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemPartitionedAccessConvertService extends BaseMemConvertPartitionedAccessServiceImpl<BaseTestDomain, BaseTestEntity, Long, String> {

	public BaseTestMemPartitionedAccessConvertService(BaseTestMemPartitionedAccessDao dao) {
		super(BaseTestDomain.class, dao, new BaseTestDomainConverter());
	}
}



