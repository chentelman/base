package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseEnumEntity;
import org.chentelman.base.module.data.mem.dao.BaseEnumDao;
import org.springframework.stereotype.Component;

@Component
public class BaseTestEnumDao extends BaseEnumDao<BaseEnumEntity> {

	public BaseTestEnumDao() {
		super(BaseEnumEntity.class);
	}
}



