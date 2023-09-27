package org.chentelman.base.testing.data.stepdefs;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import io.cucumber.java.After;

class BaseTestDaoDeleteStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoDeleteStepDefinitions stepDefs = new BaseTestDaoDeleteStepDefinitions (service);

	@After
	void clear () {
		reset(dao);
		service.clear();
	}

	/*
	 * delete by id operation
	 */

	@Test
	void deleteById () {
		stepDefs.deleteById("valid", "5");

		verify(dao, times(1)).deleteById("5", true);
	}

	@Test
	void deleteByIdLatest () {
		service.getLatestListable("valid");
		stepDefs.deleteByIdLatest("6");

		verify(dao, times(1)).deleteById("6", true);
	}

	@Test
	void deleteByIdFailure () {
		stepDefs.deleteByIdFailure("valid", "7");

		verify(dao, times(1)).deleteById("7", false);
	}

	@Test
	void deleteByIdFailureLatest () {
		service.getLatestListable("valid");
		stepDefs.deleteByIdFailureLatest("7");

		verify(dao, times(1)).deleteById("7", false);
	}

	/*
	 * delete latest operation
	 */

	@Test
	void deleteLatest () {
		stepDefs.deleteLatest("valid");

		verify(dao, times(1)).deleteLatest(true);
	}

	@Test
	void deleteLatestLatest () {
		service.getLatestListable("valid");
		stepDefs.deleteLatestLatest();

		verify(dao, times(1)).deleteLatest(true);
	}

	@Test
	void deleteLatestFailure () {
		stepDefs.deleteLatestFailure("valid");

		verify(dao, times(1)).deleteLatest(false);
	}

	@Test
	void deleteLatestLatestFailure () {
		service.getLatestListable("valid");
		stepDefs.deleteLatestLatestFailure();

		verify(dao, times(1)).deleteLatest(false);
	}

	/*
	 * delete all
	 */

	@Test
	void deleteAll () {
		stepDefs.deleteAll("valid");

		verify(dao, times(1)).deleteAll();
	}

	@Test
	void deleteAllLatest () {
		service.getLatestListable("valid");
		stepDefs.deleteAllLatest();

		verify(dao, times(1)).deleteAll();
	}

}



