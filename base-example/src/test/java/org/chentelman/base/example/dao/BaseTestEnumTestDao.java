package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseEnumEntity;
import org.chentelman.base.module.core.data.BaseCommonDao;
import org.chentelman.base.testing.data.testdao.BaseTestDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class BaseTestEnumTestDao extends BaseTestDaoImpl<BaseEnumEntity, String> {

	public BaseTestEnumTestDao(BaseCommonDao<BaseEnumEntity, String> dao, BaseObjectService builderService) {
		super(String.class, BaseEnumEntity.class, dao, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}
}



