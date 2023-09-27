package org.chentelman.base.testing.controller.stepdefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.testing.controller.service.BaseTestRequestClient;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.chentelman.base.testing.json.BaseJsonService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Delete Step definitions for {@link BaseTestRequest} partitioned operations
 */
public class BaseTestRequestPartitionedDeleteStepDefinitions extends BaseTestRequestClient {
	private final BaseTestRequestListableService baseTestRequestListableService;

	public BaseTestRequestPartitionedDeleteStepDefinitions (WebClient webClient, BaseTestRequestListableService baseTestRequestListableService, BaseJsonService baseJsonService) {
		super (webClient, MediaType.APPLICATION_JSON);

		this.baseTestRequestListableService = baseTestRequestListableService;
	}

	/*
	 * delete by id operation
	 */

	@When("deleting the {string} json request entry with id {string} from {string} partition")
	@Then("delete the {string} json request entry with id {string} from {string} partition")
	public void deleteById (String name, String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		delete(request, id, partitionKey);

		assertTrue (request.isSuccess());
	}

	@When("deleting the json request entry with id {string} from {string} partition")
	@Then("delete the json request entry with id {string} from {string} partition")
	public void deleteByIdLatest (String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		delete(request, id, partitionKey);

		assertTrue (request.isSuccess());
	}

	@When("failing to delete the {string} json request entry with id {string} from {string} partition")
	@Then("fail to delete the {string} json request entry with id {string} from {string} partition")
	public void deleteByIdFailure (String name, String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		delete(request, id, partitionKey);

		assertFalse (request.isSuccess());
	}

	@When("failing to delete the json request entry with id {string} from {string} partition")
	@Then("fail to delete the json request entry with id {string} from {string} partition")
	public void deleteByIdLatestFailure (String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		delete(request, id, partitionKey);

		assertFalse (request.isSuccess());
	}

	/*
	 * delete by partition key operation
	 */

	@When("deleting all {string} json request entries in {string} partition")
	@Then("delete all {string} json request entries in {string} partition")
	public void deleteLatest (String name, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		deletePartition(request, partitionKey);

		assertTrue (request.isSuccess());
	}

	@When("deleting all json request entries in {string} partition")
	@Then("delete all json request entries in {string} partition")
	public void deleteLatestLatest (String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		deletePartition(request, partitionKey);

		assertTrue (request.isSuccess());
	}

	@When("failing to delete all {string} json request entries in {string} partition")
	@Then("fail to delete all {string} json request entries in {string} partition")
	public void deleteLatestFailure (String name, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		deletePartition(request, partitionKey);

		assertFalse (request.isSuccess());
	}

	@When("failing to delete all json request entries in {string} partition")
	@Then("fail to delete all json request entries in {string} partition")
	public void deleteLatestLatestFailure (String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		deletePartition(request, partitionKey);

		assertFalse (request.isSuccess());
	}
}



