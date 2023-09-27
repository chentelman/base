package org.chentelman.base.example.service;

import org.chentelman.base.example.dao.BaseTestMemPartitionedAccessDao;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.service.BaseMemDefaultPartitionedAccessServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseTestMemPartitionedAccessDefaultService extends BaseMemDefaultPartitionedAccessServiceImpl<BaseTestEntity, Long, String> {

	public BaseTestMemPartitionedAccessDefaultService(BaseTestMemPartitionedAccessDao dao) {
		super(BaseTestEntity.class, dao);
	}
}



