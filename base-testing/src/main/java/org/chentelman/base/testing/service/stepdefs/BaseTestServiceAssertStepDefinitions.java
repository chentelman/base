package org.chentelman.base.testing.service.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.testservice.BaseTestService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for checking the results of a retrieved {@link BaseEntity}
 */
public class BaseTestServiceAssertStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServiceAssertStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * assert service operation
	 */

	private void assertData (BaseTestService service, DataTable data, boolean status) {
		assertEquals(status, service.dataMatches(data));
	}

	@When("the latest {string} service entry matches")
	public void assertData (String name, DataTable data) {
		assertData(this.baseTestServiceListableService.getListable(name), data, true);
	}

	@When("the latest service entry matches")
	public void assertDataLatest (DataTable data) {
		assertData(this.baseTestServiceListableService.getLatestListable(), data, true);
	}

	@When("the latest {string} service entry does not match")
	public void assertNotData (String name, DataTable data) {
		assertData(this.baseTestServiceListableService.getListable(name), data, false);
	}

	@When("the latest service entry does not match")
	public void assertNotDataLatest (DataTable data) {
		assertData(this.baseTestServiceListableService.getLatestListable(), data, false);
	}

	/*
	 * assert service in array operation
	 */

	private void assertArrayData (BaseTestService service, int index, DataTable data, boolean status) {
		assertEquals(status, service.arrayMatches(index, data));
	}

	@When("the latest {string} service entry at index {int} matches")
	public void assertArrayData (String name, int index, DataTable data) {
		assertArrayData(this.baseTestServiceListableService.getListable(name), index, data, true);
	}

	@When("the latest service entry at index {int} matches")
	public void assertArrayDataLatest (int index, DataTable data) {
		assertArrayData(this.baseTestServiceListableService.getLatestListable(), index, data, true);
	}

	@When("the latest {string} service entry at index {int} does not match")
	public void assertArrayNotData (String name, int index, DataTable data) {
		assertArrayData(this.baseTestServiceListableService.getListable(name), index, data, false);
	}

	@When("the latest service entry at index {int} does not match")
	public void assertArrayNotDataLatest (int index, DataTable data) {
		assertArrayData(this.baseTestServiceListableService.getLatestListable(), index, data, false);
	}

	/*
	 * assert array contains service operation
	 */

	private void assertArrayContains (BaseTestService service, DataTable data, boolean status) {
		assertEquals(status, service.arrayContains (data));
	}

	@When("the latest {string} service entries contain")
	public void assertArrayContains (String name, DataTable data) {
		assertArrayContains(this.baseTestServiceListableService.getListable(name), data, true);
	}

	@When("the latest service entries contain")
	public void assertArrayContainsLatest (DataTable data) {
		assertArrayContains(this.baseTestServiceListableService.getLatestListable(), data, true);
	}

	@When("the latest {string} service entries do not contain")
	public void assertArrayNotContains (String name, DataTable data) {
		assertArrayContains(this.baseTestServiceListableService.getListable(name), data, false);
	}

	@When("the latest service entries do not contain")
	public void assertArrayNotContainsLatest (DataTable data) {
		assertArrayContains(this.baseTestServiceListableService.getLatestListable(), data, false);
	}

	/*
	 * assert count operation
	 */

	private void assertCount (BaseTestService service, int count) {
		assertTrue(service.arrayCount(count));
	}

	@Then("the number of latest {string} service entries are {int}")
	@Then("the number of latest {string} service entries is {int}")
	@When("the count of latest {string} service entries is {int}")
	public void assertCount (String name, int count) {
		assertCount(this.baseTestServiceListableService.getListable(name), count);
	}

	@Then("the number of latest service entries are {int}")
	@Then("the number of latest service entries is {int}")
	@When("the count of latest service entries is {int}")
	public void assertCountLatest (int count) {
		assertCount(this.baseTestServiceListableService.getLatestListable(), count);
	}

	/*
	 * assert not count operation
	 */

	private void assertNotCount (BaseTestService service, int count) {
		assertFalse(service.arrayCount(count));
	}

	@Then("the number of latest {string} service entries are not {int}")
	@Then("the number of latest {string} service entries is not {int}")
	@When("the count of latest {string} service entries is not {int}")
	public void assertNotCount (String name, int count) {
		assertNotCount(this.baseTestServiceListableService.getListable(name), count);
	}

	@Then("the number of latest service entries are not {int}")
	@Then("the number of latest service entries is not {int}")
	@When("the count of latest service entries is not {int}")
	public void assertNotCountLatest (int count) {
		assertNotCount(this.baseTestServiceListableService.getLatestListable(), count);
	}

	/*
	 * assert latest operation
	 */

	private void assertLatest (BaseTestService service) {
		assertTrue(service.latestExists());
	}

	@Given("the latest {string} service entry is present")
	public void assertLatest (String name) {
		assertLatest(this.baseTestServiceListableService.getListable(name));
	}

	@Given("the latest service entry is present")
	public void assertLatestLatest () {
		assertLatest(this.baseTestServiceListableService.getLatestListable());
	}

}



