package org.chentelman.base.example.controller;

import org.chentelman.base.testing.controller.testresults.BaseTestJsonRequestImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestJpaConvertConvertJsonRequest extends BaseTestJsonRequestImpl {

	@Override
	public String getPath() {
		return "/BaseTestJpaConvertConvertController";
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



