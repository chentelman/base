package org.chentelman.base.testing.data.stepdefs;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import io.cucumber.java.After;

class BaseTestDaoAccessStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoAccessStepDefinitions stepDefs = new BaseTestDaoAccessStepDefinitions (service);

	@After
	void clear () {
		reset(dao);
		service.clear();
	}

	/*
	 * find all operation
	 */

	@Test
	void findAll () {
		stepDefs.findAll("valid");
		verify(dao, times(1)).findAll(true);
	}

	@Test
	void findAllLatest () {
		service.getLatestListable("valid");
		stepDefs.findAllLatest();
		verify(dao, times(1)).findAll(true);
	}

	/*
	 * find page operation
	 */

	@Test
	void findPage () {
		stepDefs.findPage(2, 3, "valid");

		verify(dao, times(1)).findAll(PageRequest.of(2, 3), true);
	}

	@Test
	void findPageLatest () {
		service.getLatestListable("valid");
		stepDefs.findPageLatest(3, 4);

		verify(dao, times(1)).findAll(PageRequest.of(3, 4), true);
	}

	/*
	 * find by id operation
	 */

	@Test
	void findById () {
		stepDefs.findById("valid", "1");

		verify(dao, times(1)).findById("1", true);
	}

	@Test
	void findByIdLatest () {
		service.getLatestListable("valid");
		stepDefs.findByIdLatest("2");

		verify(dao, times(1)).findById("2", true);
	}

	@Test
	void findByIdFailure () {
		stepDefs.findByIdFailure("valid", "3");

		verify(dao, times(1)).findById("3", false);
	}

	@Test
	void findByIdLatestFailure () {
		service.getLatestListable("valid");
		stepDefs.findByIdLatestFailure("4");

		verify(dao, times(1)).findById("4", false);
	}

	/*
	 * find latest operation
	 */

	@Test
	void findLatest () {
		stepDefs.findLatest("valid");

		verify(dao, times(1)).findLatest(true);
	}

	@Test
	void findLatestLatest () {
		service.getLatestListable("valid");
		stepDefs.findLatestLatest();

		verify(dao, times(1)).findLatest(true);
	}

	@Test
	void findLatestFailure () {
		stepDefs.findLatestFailure("valid");

		verify(dao, times(1)).findLatest(false);
	}

	@Test
	void findLatestLatestFailure () {
		service.getLatestListable("valid");
		stepDefs.findLatestLatestFailure();

		verify(dao, times(1)).findLatest(false);
	}

	/*
	 * count operation
	 */

	@Test
	void count () {
		stepDefs.count(11, "valid");

		verify(dao, times(1)).count(11);
	}

	@Test
	void countReversed () {
		stepDefs.count(22, "valid");

		verify(dao, times(1)).count(22);
	}

	@Test
	void countLatest () {
		service.getLatestListable("valid");
		stepDefs.countLatest(33);

		verify(dao, times(1)).count(33);
	}
}



