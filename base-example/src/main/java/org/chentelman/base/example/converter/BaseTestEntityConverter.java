package org.chentelman.base.example.converter;

import java.util.function.Function;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestEntity;

public class BaseTestEntityConverter implements Function <BaseTestDomain, BaseTestEntity> {

	@Override
	public BaseTestEntity apply (BaseTestDomain domain) {
		BaseTestEntity entity = new BaseTestEntity ();

		entity.setId  (domain.getId());
		entity.setCode(domain.getCode());
		entity.setName(domain.getName());

		return entity;
	}
}



