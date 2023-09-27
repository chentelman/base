package org.chentelman.base.testing.data.stepdefs;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import io.cucumber.java.After;

class BaseTestDaoUpdateStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoUpdateStepDefinitions stepDefs = new BaseTestDaoUpdateStepDefinitions (service);

	@After
	void clear () {
		reset(dao);
		service.clear();
	}

	/*
	 * update operation
	 */

	@Test
	void updateStraight () {
		stepDefs.update("valid", null);

		verify(dao, times(1)).update(null, true);
	}

	@Test
	void updateStraightLatest () {
		service.getLatestListable("valid");
		stepDefs.updateLatest(null);

		verify(dao, times(1)).update(null, true);
	}

	@Test
	void updateStraightFailure () {
		stepDefs.updateFailure("valid", null);

		verify(dao, times(1)).update(null, false);
	}

	@Test
	void updateStraightFailureLatest () {
		service.getLatestListable("valid");
		stepDefs.updateFailureLatest(null);

		verify(dao, times(1)).update(null, false);
	}

	/*
	 * update operation
	 */

	@Test
	void updateLatest () {
		stepDefs.updateLatest("valid", null);

		verify(dao, times(1)).updateLatest(null, true);
	}

	@Test
	void updateLatestLatest () {
		service.getLatestListable("valid");
		stepDefs.updateLatestLatest(null);

		verify(dao, times(1)).updateLatest(null, true);
	}

	@Test
	void updateLatestFailure () {
		stepDefs.updateLatestFailure("valid", null);

		verify(dao, times(1)).updateLatest(null, false);
	}

	@Test
	void updateLatestLatestFailure () {
		service.getLatestListable("valid");
		stepDefs.updateLatestLatestFailure(null);

		verify(dao, times(1)).updateLatest(null, false);
	}
}



