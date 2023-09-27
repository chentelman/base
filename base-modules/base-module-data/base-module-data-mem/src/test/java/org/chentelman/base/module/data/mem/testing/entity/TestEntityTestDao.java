package org.chentelman.base.module.data.mem.testing.entity;

import org.chentelman.base.module.data.mem.testing.dao.TestDao;
import org.chentelman.base.testing.data.testdao.BaseTestDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class TestEntityTestDao extends BaseTestDaoImpl<TestEntity, Long> {

	public TestEntityTestDao(TestDao repository, BaseObjectService objectService) {
		super(Long.class, TestEntity.class, repository, objectService);
	}

	@Override
	public String getName () {
		return "TestEntity";
	}

}



