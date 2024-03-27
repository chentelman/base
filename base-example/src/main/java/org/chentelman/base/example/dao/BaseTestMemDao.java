package org.chentelman.base.example.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.dao.BaseMemDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemDao extends BaseMemDaoImpl<BaseTestEntity, Long> {

	/**
	 * create a default concurrent hash map for the memory access storage
	 */
	public BaseTestMemDao(Map<Long, BaseTestEntity> data) {
		super(new ConcurrentHashMap<>());
	}
}



