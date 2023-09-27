package org.chentelman.base.example.service;

import org.chentelman.base.example.entity.BaseEnumEntity;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.service.testservice.BaseTestServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestEnumTestService extends BaseTestServiceImpl<BaseEnumEntity, String> {

	public BaseTestEnumTestService (BaseTestEnumService service, BaseObjectService builderService) {
		super(String.class, BaseEnumEntity.class, service, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}

}



