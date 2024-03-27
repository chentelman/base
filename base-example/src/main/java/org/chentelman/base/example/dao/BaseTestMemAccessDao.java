package org.chentelman.base.example.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.dao.BaseMemAccessDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemAccessDao extends BaseMemAccessDaoImpl<BaseTestEntity, Long> {

	/**
	 * create a default concurrent hash map for the memory access storage
	 */
	public BaseTestMemAccessDao() {
		super(new ConcurrentHashMap<>());
	}

	/**
	 * return the internal storage data
	 * to enable initialization of the dao
	 * for testing
	 *
	 * @return internal storage data
	 */
	public Map<Long, BaseTestEntity> getInternalData() {
		return data;
	}
}



