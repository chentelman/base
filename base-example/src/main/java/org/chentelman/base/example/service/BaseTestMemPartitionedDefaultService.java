package org.chentelman.base.example.service;

import org.chentelman.base.example.dao.BaseTestMemPartitionedDao;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemDefaultPartitionedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemPartitionedDefaultService extends BaseMemDefaultPartitionedServiceImpl<BaseTestEntity, Long, String> {

	public BaseTestMemPartitionedDefaultService(BaseTestMemPartitionedDao dao) {
		super(BaseTestEntity.class, dao);
	}
}



