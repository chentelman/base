package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.module.core.service.BasePartitionedUpdateService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.testservice.BaseTestPartitionedService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BasePartitionedUpdateService} operations
 */
public class BaseTestServicePartitionedUpdateStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServicePartitionedUpdateStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * update operation
	 */

	@Given("the updated {string} service entry with id {string} in partition {string}")
	@Given("the updated {string} service entry with id {string} in {string} partition")
	@When("updating the {string} service entry with id {string} in partition {string}")
	@When("updating the {string} service entry with id {string} in {string} partition")
	@Then("update the {string} service entry with id {string} in partition {string}")
	@Then("update the {string} service entry with id {string} in {string} partition")
	public void updateById (String name, String id, String partition, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class)
			.update(true, data, partition, id);
	}

	@Given("the updated service entry with id {string} in partition {string}")
	@Given("the updated service entry with id {string} in {string} partition")
	@When("updating the service entry with id {string} in partition {string}")
	@When("updating the service entry with id {string} in {string} partition")
	@Then("update the service entry with id {string} in partition {string}")
	@Then("update the service entry with id {string} in {string} partition")
	public void updateByIdLatest (String id, String partition, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class)
			.update(true, data, partition, id);
	}

	@Given("a failure to update {string} service entry with id {string} in partition {string}")
	@Given("a failure to update {string} service entry with id {string} in {string} partition")
	@When("failing to update the {string} service entry with id {string} in partition {string}")
	@When("failing to update the {string} service entry with id {string} in {string} partition")
	@Then("fail to update the {string} service entry with id {string} in partition {string}")
	@Then("fail to update the {string} service entry with id {string} in {string} partition")
	public void updateByIdFailure (String name, String id, String partition, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class)
			.update(false, data, partition, id);
	}

	@Given("a failure to update service entry with id {string} in partition {string}")
	@Given("a failure to update service entry with id {string} in {string} partition")
	@When("failing to update the service entry with id {string} in partition {string}")
	@When("failing to update the service entry with id {string} in {string} partition")
	@Then("fail to update the service entry with id {string} in partition {string}")
	@Then("fail to update the service entry with id {string} in {string} partition")
	public void updateByIdFailureLatest (String id, String partition, DataTable data) {
		this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class)
			.update(false, data, partition, id);
	}
}



