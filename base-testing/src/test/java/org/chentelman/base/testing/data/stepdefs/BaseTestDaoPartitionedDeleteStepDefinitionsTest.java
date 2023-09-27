package org.chentelman.base.testing.data.stepdefs;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import io.cucumber.java.After;

class BaseTestDaoPartitionedDeleteStepDefinitionsTest extends BaseTestDaoAbstractStepDefinitionsHelper {
	private final BaseTestDaoPartitionedDeleteStepDefinitions stepDefs = new BaseTestDaoPartitionedDeleteStepDefinitions (service);

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
		stepDefs.deleteById("valid", "5", "partition5");

		verify(dao, times(1)).deleteById("partition5", "5", true);
	}

	@Test
	void deleteByIdLatest () {
		service.getLatestListable("valid");
		stepDefs.deleteByIdLatest("6", "partition6");

		verify(dao, times(1)).deleteById("partition6", "6", true);
	}

	@Test
	void deleteByIdFailure () {
		stepDefs.deleteByIdFailure("valid", "7", "partition7");

		verify(dao, times(1)).deleteById("partition7", "7", false);
	}

	@Test
	void deleteByIdLatestFailure () {
		service.getLatestListable("valid");
		stepDefs.deleteByIdLatestFailure("8", "partition8");

		verify(dao, times(1)).deleteById("partition8", "8", false);
	}

	/*
	 * delete by partition key operation
	 */

	@Test
	void deleteLatest () {
		stepDefs.deleteLatest("valid", "partition11");

		verify(dao, times(1)).deleteByPartitionKey("partition11", true);
	}

	@Test
	void deleteLatestLatest () {
		service.getLatestListable("valid");
		stepDefs.deleteLatestLatest("partition12");

		verify(dao, times(1)).deleteByPartitionKey("partition12", true);
	}

	@Test
	void deleteLatestFailure () {
		stepDefs.deleteLatestFailure("valid", "partition13");

		verify(dao, times(1)).deleteByPartitionKey("partition13", false);
	}

	@Test
	void deleteLatestLatestFailure () {
		service.getLatestListable("valid");
		stepDefs.deleteLatestLatestFailure("partition14");

		verify(dao, times(1)).deleteByPartitionKey("partition14", false);
	}
}



