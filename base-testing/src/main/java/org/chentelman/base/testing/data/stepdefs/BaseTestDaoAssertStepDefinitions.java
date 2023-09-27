package org.chentelman.base.testing.data.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestDao;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for checking the results of a retrieved {@link BaseEntity}
 */
public class BaseTestDaoAssertStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoAssertStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	/*
	 * assert data operation
	 */

	private void assertData (BaseTestDao dao, DataTable data, boolean status) {
		assertEquals(status, dao.dataMatches(data));
	}

	@When("the latest {string} data entry matches")
	public void assertData (String name, DataTable data) {
		assertData(this.baseTestDaoListableService.getListable(name), data, true);
	}

	@When("the latest data entry matches")
	public void assertDataLatest (DataTable data) {
		assertData(this.baseTestDaoListableService.getLatestListable(), data, true);
	}

	@When("the latest {string} data entry does not match")
	public void assertNotData (String name, DataTable data) {
		assertData(this.baseTestDaoListableService.getListable(name), data, false);
	}

	@When("the latest data entry does not match")
	public void assertNotDataLatest (DataTable data) {
		assertData(this.baseTestDaoListableService.getLatestListable(), data, false);
	}

	/*
	 * assert data in array operation
	 */

	private void assertArrayData (BaseTestDao dao, int index, DataTable data, boolean status) {
		assertEquals(status, dao.arrayMatches(index, data));
	}

	@When("the latest {string} data entry at index {int} matches")
	public void assertArrayData (String name, int index, DataTable data) {
		assertArrayData(this.baseTestDaoListableService.getListable(name), index, data, true);
	}

	@When("the latest data entry at index {int} matches")
	public void assertArrayDataLatest (int index, DataTable data) {
		assertArrayData(this.baseTestDaoListableService.getLatestListable(), index, data, true);
	}

	@When("the latest {string} data entry at index {int} does not match")
	public void assertArrayNotData (String name, int index, DataTable data) {
		assertArrayData(this.baseTestDaoListableService.getListable(name), index, data, false);
	}

	@When("the latest data entry at index {int} does not match")
	public void assertArrayNotDataLatest (int index, DataTable data) {
		assertArrayData(this.baseTestDaoListableService.getLatestListable(), index, data, false);
	}

	/*
	 * assert array contains data operation
	 */

	private void assertArrayContains (BaseTestDao dao, DataTable data, boolean status) {
		assertEquals(status, dao.arrayContains (data));
	}

	@When("the latest {string} data entries contain")
	public void assertArrayContains (String name, DataTable data) {
		assertArrayContains(this.baseTestDaoListableService.getListable(name), data, true);
	}

	@When("the latest data entries contain")
	public void assertArrayContainsLatest (DataTable data) {
		assertArrayContains(this.baseTestDaoListableService.getLatestListable(), data, true);
	}

	@When("the latest {string} data entries do not contain")
	public void assertArrayNotContains (String name, DataTable data) {
		assertArrayContains(this.baseTestDaoListableService.getListable(name), data, false);
	}

	@When("the latest data entries do not contain")
	public void assertArrayNotContainsLatest (DataTable data) {
		assertArrayContains(this.baseTestDaoListableService.getLatestListable(), data, false);
	}

	/*
	 * assert count operation
	 */

	private void assertCount (BaseTestDao dao, int count) {
		assertTrue(dao.arrayCount(count));
	}

	@Then("the number of latest {string} data entries are {int}")
	@Then("the number of latest {string} data entries is {int}")
	@When("the count of latest {string} data entries is {int}")
	public void assertCount (String name, int count) {
		assertCount(this.baseTestDaoListableService.getListable(name), count);
	}

	@Then("the number of latest data entries are {int}")
	@Then("the number of latest data entries is {int}")
	@When("the count of latest data entries is {int}")
	public void assertCountLatest (int count) {
		assertCount(this.baseTestDaoListableService.getLatestListable(), count);
	}

	/*
	 * assert not count operation
	 */

	private void assertNotCount (BaseTestDao dao, int count) {
		assertFalse(dao.arrayCount(count));
	}

	@Then("the number of latest {string} data entries are not {int}")
	@Then("the number of latest {string} data entries is not {int}")
	@When("the count of latest {string} data entries is not {int}")
	public void assertNotCount (String name, int count) {
		assertNotCount(this.baseTestDaoListableService.getListable(name), count);
	}

	@Then("the number of latest data entries are not {int}")
	@Then("the number of latest data entries is not {int}")
	@When("the count of latest data entries is not {int}")
	public void assertNotCountLatest (int count) {
		assertNotCount(this.baseTestDaoListableService.getLatestListable(), count);
	}

	/*
	 * assert latest operation
	 */

	private void assertLatest (BaseTestDao dao) {
		assertTrue(dao.latestExists());
	}

	@Given("the latest {string} data entry is present")
	public void assertLatest (String name) {
		assertLatest(this.baseTestDaoListableService.getListable(name));
	}

	@Given("the latest data entry is present")
	public void assertLatestLatest () {
		assertLatest(this.baseTestDaoListableService.getLatestListable());
	}

}



