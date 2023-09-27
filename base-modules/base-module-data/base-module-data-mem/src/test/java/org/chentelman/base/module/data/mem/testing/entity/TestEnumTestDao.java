package org.chentelman.base.module.data.mem.testing.entity;

import org.chentelman.base.module.data.mem.testing.dao.TestEnumDao;
import org.chentelman.base.testing.data.testdao.BaseTestDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class TestEnumTestDao extends BaseTestDaoImpl<TestEnum, String> {

	public TestEnumTestDao(TestEnumDao repository, BaseObjectService objectService) {
		super(String.class, TestEnum.class, repository, objectService);
	}

	@Override
	public String getName () {
		return "TestEnum";
	}

}



