package org.chentelman.base.example.controller;

import org.chentelman.base.testing.controller.testresults.BaseTestPartitionedJsonRequestImpl;
import org.springframework.stereotype.Component;

@Component
public class BaseTestMemPartitionedAccessDefaultConvertJsonRequest extends BaseTestPartitionedJsonRequestImpl {

	@Override
	public String getPath() {
		return "/BaseTestMemPartitionedAccessDefaultConvertController";
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



