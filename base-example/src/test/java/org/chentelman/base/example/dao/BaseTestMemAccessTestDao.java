package org.chentelman.base.example.dao;

import java.util.Map;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.testing.data.testdao.BaseTestDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemAccessTestDao extends BaseTestDaoImpl<BaseTestEntity, Long> {

	public BaseTestMemAccessTestDao(BaseTestMemAccessDao dao, BaseObjectService builderService) {
		super(Long.class, BaseTestEntity.class, dao, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}

	@Override
	protected final Map<Long, BaseTestEntity> getMap() {
		BaseTestMemAccessDao dao = castTestable ("not a BaseTestMemAccessDao");
		return dao.getInternalData();
	}
}



