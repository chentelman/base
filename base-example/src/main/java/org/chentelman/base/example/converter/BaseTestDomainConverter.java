package org.chentelman.base.example.converter;

import java.util.function.Function;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestEntity;

public class BaseTestDomainConverter implements Function <BaseTestEntity, BaseTestDomain> {

	@Override
	public BaseTestDomain apply (BaseTestEntity entity) {
		BaseTestDomain domain = new BaseTestDomain ();

		domain.setId  (entity.getId());
		domain.setCode(entity.getCode());
		domain.setName(entity.getName());

		return domain;
	}
}



