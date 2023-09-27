package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.testing.data.testdao.BaseTestPartitionedDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedTestDao extends BaseTestPartitionedDaoImpl<BaseTestEntity, Long, String> {

	public BaseTestMemPartitionedTestDao(BaseTestMemPartitionedDao dao, BaseObjectService builderService) {
		super(Long.class, BaseTestEntity.class, String.class, dao, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}
}



