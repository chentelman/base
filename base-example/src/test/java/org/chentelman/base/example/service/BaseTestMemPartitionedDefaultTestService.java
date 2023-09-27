package org.chentelman.base.example.service;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.service.testservice.BaseTestPartitionedServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedDefaultTestService extends BaseTestPartitionedServiceImpl<BaseTestEntity, Long, String> {

	public BaseTestMemPartitionedDefaultTestService (BaseTestMemPartitionedDefaultService service, BaseObjectService builderService) {
		super(Long.class, BaseTestEntity.class, String.class, service, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}

}



