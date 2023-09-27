package org.chentelman.base.example.service;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.service.testservice.BaseTestServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestJpaConvertTestService extends BaseTestServiceImpl<BaseTestDomain, Long> {

	public BaseTestJpaConvertTestService (BaseTestJpaConvertService service, BaseObjectService builderService) {
		super(Long.class, BaseTestDomain.class, service, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}

}



