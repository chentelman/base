package org.chentelman.base.module.data.mem.testing.dao;

import org.chentelman.base.module.data.mem.dao.BaseMemPartitionedDaoImpl;
import org.chentelman.base.module.data.mem.testing.entity.TestPartitionedEntity;
import org.springframework.stereotype.Component;

@Component
public class TestPartitionedDao extends BaseMemPartitionedDaoImpl<TestPartitionedEntity, Long, String> {
	private static final long serialVersionUID = 1L;

	public TestPartitionedDao () {
		super();
	}
}



