package org.chentelman.base.example.controller;

import org.chentelman.base.testing.controller.testresults.BaseTestPartitionedJsonRequestImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedAccessConvertDefaultJsonRequest extends BaseTestPartitionedJsonRequestImpl {

	@Override
	public String getPath() {
		return "/BaseTestMemPartitionedAccessConvertDefaultController";
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



