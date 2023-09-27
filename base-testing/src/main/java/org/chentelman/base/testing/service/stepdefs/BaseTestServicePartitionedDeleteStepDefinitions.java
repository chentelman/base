package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.module.core.service.BasePartitionedDeleteService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.testservice.BaseTestPartitionedService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BasePartitionedDeleteService} operations
 */
public class BaseTestServicePartitionedDeleteStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServicePartitionedDeleteStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * delete by id operation
	 */

	private void deleteById (BaseTestPartitionedService service, String partitionKey, String id, boolean status) {
		service.delete(status, partitionKey, id);
	}

	@When("deleting the {string} service entry with id {string} from {string} partition")
	@Then("delete the {string} service entry with id {string} from {string} partition")
	public void deleteById (String name, String id, String partitionKey) {
		deleteById(this.baseTestServiceListableService.getListable(name, BaseTestPartitionedService.class), partitionKey, id, true);
	}

	@When("deleting the service entry with id {string} from {string} partition")
	@Then("delete the service entry with id {string} from {string} partition")
	public void deleteByIdLatest (String id, String partitionKey) {
		deleteById(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, id, true);
	}

	@When("failing to delete the {string} service entry with id {string} from {string} partition")
	@Then("fail to delete the {string} service entry with id {string} from {string} partition")
	public void deleteByIdFailure (String name, String id, String partitionKey) {
		deleteById(this.baseTestServiceListableService.getListable(name, BaseTestPartitionedService.class), partitionKey, id, false);
	}

	@When("failing to delete the service entry with id {string} from {string} partition")
	@Then("fail to delete the service entry with id {string} from {string} partition")
	public void deleteByIdLatestFailure (String id, String partitionKey) {
		deleteById(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, id, false);
	}

	/*
	 * delete by partition key operation
	 */

	private void deleteByPartitionKey (BaseTestPartitionedService service, String partitionKey, boolean status) {
		service.deleteAll(status, partitionKey);
	}

	@When("deleting all {string} service entries in {string} partition")
	@Then("delete all {string} service entries in {string} partition")
	public void deleteLatest (String name, String partitionKey) {
		deleteByPartitionKey(this.baseTestServiceListableService.getListable(name, BaseTestPartitionedService.class), partitionKey, true);
	}

	@When("deleting all service entries in {string} partition")
	@Then("delete all service entries in {string} partition")
	public void deleteLatestLatest (String partitionKey) {
		deleteByPartitionKey(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, true);
	}

	@When("failing to delete all {string} service entries in {string} partition")
	@Then("fail to delete all {string} service entries in {string} partition")
	public void deleteLatestFailure (String name, String partitionKey) {
		deleteByPartitionKey(this.baseTestServiceListableService.getListable(name, BaseTestPartitionedService.class), partitionKey, false);
	}

	@When("failing to delete all service entries in {string} partition")
	@Then("fail to delete all service entries in {string} partition")
	public void deleteLatestLatestFailure (String partitionKey) {
		deleteByPartitionKey(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, false);
	}
}



