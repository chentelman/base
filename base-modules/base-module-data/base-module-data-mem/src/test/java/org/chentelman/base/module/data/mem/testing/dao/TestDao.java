package org.chentelman.base.module.data.mem.testing.dao;

import org.chentelman.base.module.data.mem.dao.BaseMemDaoImpl;
import org.chentelman.base.module.data.mem.testing.entity.TestEntity;
import org.springframework.stereotype.Component;

@Component
public class TestDao extends BaseMemDaoImpl<TestEntity, Long> {
	private static final long serialVersionUID = 1L;

	public TestDao () {
		super();
	}
}



