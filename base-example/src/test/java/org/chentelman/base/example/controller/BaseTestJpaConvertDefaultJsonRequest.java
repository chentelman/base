package org.chentelman.base.example.controller;

import org.chentelman.base.testing.controller.testresults.BaseTestJsonRequestImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestJpaConvertDefaultJsonRequest extends BaseTestJsonRequestImpl {

	@Override
	public String getPath() {
		return "/BaseTestJpaConvertDefaultController";
	}

	@Override
	public String getKeyName() {
		return "id";
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

}



