package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.module.core.service.BaseDeleteService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.testservice.BaseTestService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseDeleteService} operations
 */
public class BaseTestServiceDeleteStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServiceDeleteStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * delete by id operation
	 */

	private void deleteById (BaseTestService service, String id, boolean status) {
		service.deleteById(status, id);
	}

	@When("deleting the {string} service entry with id {string}")
	@Then("delete the {string} service entry with id {string}")
	public void deleteById (String name, String id) {
		deleteById(this.baseTestServiceListableService.getListable(name), id, true);
	}

	@When("deleting the service entry with id {string}")
	@Then("delete the service entry with id {string}")
	public void deleteByIdLatest (String id) {
		deleteById(this.baseTestServiceListableService.getLatestListable(), id, true);
	}

	@When("failing to delete the {string} service entry with id {string}")
	@Then("fail to delete the {string} service entry with id {string}")
	public void deleteByIdFailure (String name, String id) {
		deleteById(this.baseTestServiceListableService.getListable(name), id, false);
	}

	@When("failing to delete the service entry with id {string}")
	@Then("fail to delete the service entry with id {string}")
	public void deleteByIdFailureLatest (String id) {
		deleteById(this.baseTestServiceListableService.getLatestListable(), id, false);
	}

	/*
	 * delete latest operation
	 */

	private void deleteLatest (BaseTestService service, boolean status) {
		service.deleteLatest(status);
	}

	@When("deleting the latest {string} service entry")
	@Then("delete the latest {string} service entry")
	public void deleteLatest (String name) {
		deleteLatest(this.baseTestServiceListableService.getListable(name), true);
	}

	@When("deleting the latest service entry")
	@Then("delete the latest service entry")
	public void deleteLatestLatest () {
		deleteLatest(this.baseTestServiceListableService.getLatestListable(), true);
	}

	@When("failing to delete the latest {string} service entry")
	@Then("fail to delete the latest {string} service entry")
	public void deleteLatestFailure (String name) {
		deleteLatest(this.baseTestServiceListableService.getListable(name), false);
	}

	@When("failing to delete the latest service entry")
	@Then("fail to delete the latest service entry")
	public void deleteLatestLatestFailure () {
		deleteLatest(this.baseTestServiceListableService.getLatestListable(), false);
	}
}



