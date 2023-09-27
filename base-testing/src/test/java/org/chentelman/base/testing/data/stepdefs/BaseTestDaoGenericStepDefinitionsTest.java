package org.chentelman.base.testing.data.stepdefs;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.reset;

import org.junit.jupiter.api.Test;

import io.cucumber.java.After;

class BaseTestDaoGenericStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoGenericStepDefinitions stepDefs = new BaseTestDaoGenericStepDefinitions (service);

	@After
	void clear () {
		reset(dao);
		service.clear();
	}

	@Test
	void setLatest () {
		assertNull (service.getLatestListable());
		stepDefs.setLatest("valid");
		assertNotNull (service.getLatestListable());
	}

}



