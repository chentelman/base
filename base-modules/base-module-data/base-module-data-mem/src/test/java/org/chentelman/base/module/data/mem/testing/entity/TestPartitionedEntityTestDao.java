package org.chentelman.base.module.data.mem.testing.entity;

import org.chentelman.base.module.data.mem.testing.dao.TestPartitionedDao;
import org.chentelman.base.testing.data.testdao.BaseTestPartitionedDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class TestPartitionedEntityTestDao extends BaseTestPartitionedDaoImpl<TestPartitionedEntity, Long, String> {

	public TestPartitionedEntityTestDao(TestPartitionedDao dao, BaseObjectService objectService) {
		super(Long.class, TestPartitionedEntity.class, String.class, dao, objectService);
	}

	@Override
	public String getName () {
		return "TestPartitionedEntity";
	}

}



