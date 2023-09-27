package org.chentelman.base.example.service;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.service.testservice.BaseTestPartitionedServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedAccessConvertTestService extends BaseTestPartitionedServiceImpl<BaseTestDomain, Long, String> {

	public BaseTestMemPartitionedAccessConvertTestService (BaseTestMemPartitionedAccessConvertService service, BaseObjectService builderService) {
		super(Long.class, BaseTestDomain.class, String.class, service, builderService);
	}

	@Override
	public String getName () {
		return getClass().getSimpleName();
	}

}



