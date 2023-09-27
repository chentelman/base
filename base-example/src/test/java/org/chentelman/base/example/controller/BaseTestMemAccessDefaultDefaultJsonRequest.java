package org.chentelman.base.example.controller;

import org.chentelman.base.testing.controller.testresults.BaseTestJsonRequestImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemAccessDefaultDefaultJsonRequest extends BaseTestJsonRequestImpl {

	@Override
	public String getPath() {
		return "/BaseTestMemAccessDefaultDefaultController";
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



