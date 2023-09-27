package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.dao.BaseMemAccessDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemAccessDao extends BaseMemAccessDaoImpl<BaseTestEntity, Long> {
	private static final long serialVersionUID = 1L;

}



