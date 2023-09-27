package org.chentelman.base.module.data.mem.testing.dao;

import org.chentelman.base.module.data.mem.dao.BaseEnumDao;
import org.chentelman.base.module.data.mem.testing.entity.TestEnum;
import org.springframework.stereotype.Component;

@Component
public class TestEnumDao extends BaseEnumDao<TestEnum> {

	public TestEnumDao() {
		super(TestEnum.class);
	}
}



