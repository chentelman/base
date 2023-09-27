package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.dao.BaseMemPartitionedDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedDao extends BaseMemPartitionedDaoImpl<BaseTestEntity, Long, String> {
	private static final long serialVersionUID = 1L;

}



