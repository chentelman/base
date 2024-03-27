package org.chentelman.base.example.dao;

import java.util.concurrent.ConcurrentHashMap;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.dao.BaseMemPartitionedDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedDao extends BaseMemPartitionedDaoImpl<BaseTestEntity, Long, String> {

	/**
	 * create a default concurrent hash map for the memory access storage
	 */
	public BaseTestMemPartitionedDao() {
		super(new ConcurrentHashMap<>(), k -> new ConcurrentHashMap<>());
	}

}



