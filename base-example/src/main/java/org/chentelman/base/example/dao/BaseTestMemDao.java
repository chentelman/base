package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.mem.dao.BaseMemDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemDao extends BaseMemDaoImpl<BaseTestEntity, Long> {
	private static final long serialVersionUID = 1L;

}



