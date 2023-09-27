package org.chentelman.base.testing.data.stepdefs;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import io.cucumber.java.After;

class BaseTestDaoCreateStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoCreateStepDefinitions stepDefs = new BaseTestDaoCreateStepDefinitions (service);

	@After
	void clear () {
		reset(dao);
		service.clear();
	}

	/*
	 * add operation
	 */

	@Test
	void add () {
		stepDefs.add("valid", null);

		verify(dao, times(1)).add(null, true);
	}

	@Test
	void addLatest () {
		service.getLatestListable("valid");
		stepDefs.addLatest(null);

		verify(dao, times(1)).add(null, true);
	}

	@Test
	void addFail () {
		stepDefs.addFail("valid", null);

		verify(dao, times(1)).add(null, false);
	}

	@Test
	void addFailLatest () {
		service.getLatestListable("valid");
		stepDefs.addFailLatest(null);

		verify(dao, times(1)).add(null, false);
	}

}



