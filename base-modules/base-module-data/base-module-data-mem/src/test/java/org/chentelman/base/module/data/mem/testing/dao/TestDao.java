package org.chentelman.base.module.data.mem.testing.dao;

import java.util.HashMap;

import org.chentelman.base.module.data.mem.dao.BaseMemDaoImpl;
import org.chentelman.base.module.data.mem.testing.entity.TestEntity;
import org.springframework.stereotype.Component;

@Component
public class TestDao extends BaseMemDaoImpl<TestEntity, Long> {

	public TestDao () {
		super(new HashMap<>());
	}
}



