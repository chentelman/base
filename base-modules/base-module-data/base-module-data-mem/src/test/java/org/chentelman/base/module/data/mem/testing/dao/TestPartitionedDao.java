package org.chentelman.base.module.data.mem.testing.dao;

import java.util.HashMap;

import org.chentelman.base.module.data.mem.dao.BaseMemPartitionedDaoImpl;
import org.chentelman.base.module.data.mem.testing.entity.TestPartitionedEntity;
import org.springframework.stereotype.Component;

@Component
public class TestPartitionedDao extends BaseMemPartitionedDaoImpl<TestPartitionedEntity, Long, String> {

	public TestPartitionedDao () {
		super(new HashMap<>(), (k) -> new HashMap<>());
	}
}



