package org.chentelman.base.testing.data.stepdefs;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import io.cucumber.java.After;

class BaseTestDaoAssertStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoAssertStepDefinitions stepDefs = new BaseTestDaoAssertStepDefinitions (service);

	@After
	void clear () {
		reset(dao);
		service.clear();
	}

	/*
	 * assert count operation
	 */

	@Test
	void assertCount () {
		when (dao.arrayCount(3)).thenReturn(true);

		stepDefs.assertCount("valid", 3);

		verify(dao, times(1)).arrayCount(3);
	}

	@Test
	void assertCountLatest () {
		when (dao.arrayCount(4)).thenReturn(true);

		service.getLatestListable("valid");
		stepDefs.assertCount("valid", 4);

		verify(dao, times(1)).arrayCount(4);
	}

	/*
	 * assert not count operation
	 */

	@Test
	void assertNotCount () {
		when (dao.arrayCount(5)).thenReturn(false);

		stepDefs.assertNotCount("valid", 5);

		verify(dao, times(1)).arrayCount(5);
	}

	@Test
	void assertNotCountLatest () {
		when (dao.arrayCount(6)).thenReturn(false);

		service.getLatestListable("valid");
		stepDefs.assertNotCount("valid", 6);

		verify(dao, times(1)).arrayCount(6);
	}

	/*
	 * assert latest operation
	 */

	@Test
	void assertLatest () {
		when (dao.latestExists()).thenReturn(true);

		stepDefs.assertLatest("valid");

		verify(dao, times(1)).latestExists();
	}

	@Test
	void assertLatestLatest () {
		when (dao.latestExists()).thenReturn(true);

		service.getLatestListable("valid");
		stepDefs.assertLatestLatest();

		verify(dao, times(1)).latestExists();
	}

}



