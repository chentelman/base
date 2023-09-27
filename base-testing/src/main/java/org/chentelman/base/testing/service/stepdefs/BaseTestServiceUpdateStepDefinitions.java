package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.module.core.service.BaseUpdateService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseUpdateService} operations
 */
public class BaseTestServiceUpdateStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServiceUpdateStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * update operation
	 */

	@Given("the updated {string} service entry with id {string}")
	@When("updating the {string} service entry with id {string}")
	@Then("update the {string} service entry with id {string}")
	public void updateById (String name, String id, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(name)
			.update(true, data, id);
	}

	@Given("the updated service entry with id {string}")
	@When("updating the service entry with id {string}")
	@Then("update the service entry with id {string}")
	public void updateByIdLatest (String id, DataTable data) {
		this.baseTestServiceListableService.getLatestListable()
			.update(true, data, id);
	}

	@Given("a failure to update {string} service entry with id {string}")
	@When("failing to update the {string} service entry with id {string}")
	@Then("fail to update the {string} service entry with id {string}")
	public void updateByIdFailure (String name, String id, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(name)
			.update(false, data, id);
	}

	@Given("a failure to update service entry with id {string}")
	@When("failing to update the service entry with id {string}")
	@Then("fail to update the service entry with id {string}")
	public void updateByIdFailureLatest (String id, DataTable data) {
		this.baseTestServiceListableService.getLatestListable()
			.update(false, data, id);
	}

	/*
	 * update operation
	 */

	@When("updating the latest {string} service entry")
	@Then("update the latest {string} service entry")
	public void updateLatest (String name, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(name)
			.updateLatest(true, data);
	}

	@When("updating the latest service entry")
	@Then("update the latest service entry")
	public void updateLatestLatest (DataTable data) {
		this.baseTestServiceListableService.getLatestListable()
			.updateLatest(true, data);
	}

	@When("failing to update the latest {string} service entry")
	@Then("fail to update the latest {string} service entry")
	public void updateLatestFailure (String name, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(name)
			.updateLatest(false, data);
	}

	@When("failing to update the latest service entry")
	@Then("fail to update the latest service entry")
	public void updateLatestLatestFailure (DataTable data) {
		this.baseTestServiceListableService.getLatestListable()
			.updateLatest(false, data);
	}
}



