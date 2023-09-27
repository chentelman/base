package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.testing.data.testdao.BaseTestDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class BaseTestJpaTestDao extends BaseTestDaoImpl<BaseTestEntity, Long> {

	public BaseTestJpaTestDao(BaseTestJpaDao dao, BaseObjectService builderService) {
		super(Long.class, BaseTestEntity.class, dao, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}
}



