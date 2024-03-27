package org.chentelman.base.example.dao;

import java.util.Map;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.testing.data.testdao.BaseTestPartitionedDaoImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedAccessTestDao extends BaseTestPartitionedDaoImpl<BaseTestEntity, Long, String> {

	public BaseTestMemPartitionedAccessTestDao(BaseTestMemPartitionedAccessDao dao, BaseObjectService builderService) {
		super(Long.class, BaseTestEntity.class, String.class, dao, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}

	@Override
	protected Map<String, Map<Long, BaseTestEntity>> getPartitionedMap() {
		BaseTestMemPartitionedAccessDao dao = castTestable ("not a BaseTestMemPartitionedAccessDao");
		return dao.getInternalData();
	}
}



