package org.chentelman.base.module.data.jpa.testing.entity;

import org.chentelman.base.module.data.jpa.testing.repository.TestRepository;
import org.chentelman.base.testing.data.testdao.BaseTestDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class TestEntityTestDao extends BaseTestDaoImpl<TestEntity, Long> {

	public TestEntityTestDao(TestRepository repository, BaseObjectService objectService) {
		super(Long.class, TestEntity.class, repository, objectService);
	}

}



