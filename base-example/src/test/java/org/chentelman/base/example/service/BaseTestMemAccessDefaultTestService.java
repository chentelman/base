package org.chentelman.base.example.service;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.service.testservice.BaseTestServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemAccessDefaultTestService extends BaseTestServiceImpl<BaseTestEntity, Long> {

	public BaseTestMemAccessDefaultTestService (BaseTestMemAccessDefaultService service, BaseObjectService builderService) {
		super(Long.class, BaseTestEntity.class, service, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}

}



