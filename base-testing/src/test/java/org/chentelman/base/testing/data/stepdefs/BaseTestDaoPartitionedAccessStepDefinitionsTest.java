package org.chentelman.base.testing.data.stepdefs;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import io.cucumber.java.After;

class BaseTestDaoPartitionedAccessStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoPartitionedAccessStepDefinitions stepDefs = new BaseTestDaoPartitionedAccessStepDefinitions (service);

	@After
	void clear () {
		reset(dao);
		service.clear();
	}

	/*
	 * find all by id operation
	 */

	@Test
	void findAll () {
		stepDefs.findAll("valid", "101");

		verify(dao, times(1)).findAllById("101", true);
	}

	@Test
	void findAllLatest () {
		service.getLatestListable("valid");
		stepDefs.findAllLatest("102");

		verify(dao, times(1)).findAllById("102", true);
	}

	/*
	 * find page by id operation
	 */

	@Test
	void findPage () {
		stepDefs.findPage(2, 3, "valid", "103");

		verify(dao, times(1)).findAllById(PageRequest.of(2, 3), "103", true);
	}

	@Test
	void findPageLatest () {
		service.getLatestListable("valid");
		stepDefs.findPageLatest(3, 4, "104");

		verify(dao, times(1)).findAllById(PageRequest.of(3, 4), "104", true);
	}

	/*
	 * find all by partition operation
	 */

	@Test
	void findAllByPartition () {
		stepDefs.findAllByPartition("valid", "partition2");

		verify(dao, times(1)).findAll("partition2", true);
	}

	@Test
	void findAllByPartitionLatest () {
		service.getLatestListable("valid");
		stepDefs.findAllByPartitionLatest("partition3");

		verify(dao, times(1)).findAll("partition3", true);
	}

	/*
	 * find page by partition operation
	 */

	@Test
	void findPageByPartition () {
		stepDefs.findPageByPartition(4, 5, "valid", "partition4");

		verify(dao, times(1)).findAll("partition4", PageRequest.of(4, 5), true);
	}

	@Test
	void findPageByPartitionLatest () {
		service.getLatestListable("valid");
		stepDefs.findPageByPartitionLatest(5, 6, "partition5");

		verify(dao, times(1)).findAll("partition5", PageRequest.of(5, 6), true);
	}

	/*
	 * count operation
	 */

	@Test
	void count () {
		stepDefs.count(12, "valid", "partition6");

		verify(dao, times(1)).count("partition6", 12);
	}

	@Test
	void countReverse () {
		stepDefs.count("valid", "partition7", 13);

		verify(dao, times(1)).count("partition7", 13);
	}

	@Test
	void countLatest () {
		service.getLatestListable("valid");
		stepDefs.countLatest(14, "partition8");

		verify(dao, times(1)).count("partition8", 14);
	}

	@Test
	void countLatestReverse () {
		service.getLatestListable("valid");
		stepDefs.countLatest("partition9", 15);

		verify(dao, times(1)).count("partition9", 15);
	}

	/*
	 * find by id operation
	 */

	@Test
	void findById () {
		stepDefs.findById("valid", "21", "partition2");

		verify(dao, times(1)).findById("partition2", "21", true);
	}

	@Test
	void findByIdLatest () {
		service.getLatestListable("valid");
		stepDefs.findByIdLatest("22", "partition3");

		verify(dao, times(1)).findById("partition3", "22", true);
	}

	@Test
	void findByIdFailure () {
		stepDefs.findByIdFailure("valid", "23", "partition4");

		verify(dao, times(1)).findById("partition4", "23", false);
	}

	@Test
	void findByIdLatestFailure () {
		service.getLatestListable("valid");
		stepDefs.findByIdLatestFailure("24", "partition5");

		verify(dao, times(1)).findById("partition5", "24", false);
	}
}



