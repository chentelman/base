package org.chentelman.base.example.controller;

import org.chentelman.base.testing.controller.testresults.BaseTestJsonRequestImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemConvertDefaultJsonRequest extends BaseTestJsonRequestImpl {

	@Override
	public String getPath() {
		return "/BaseTestMemConvertDefaultController";
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



