package org.chentelman.base.testing.controller.stepdefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.testing.controller.service.BaseTestRequestClient;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.chentelman.base.testing.json.BaseJsonService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Update Step definitions for {@link BaseTestRequest} partitioned operations
 */
public class BaseTestRequestPartitionedUpdateStepDefinitions extends BaseTestRequestClient {
	private final BaseTestRequestListableService baseTestRequestListableService;
	private final BaseJsonService baseJsonService;

	public BaseTestRequestPartitionedUpdateStepDefinitions (WebClient webClient, BaseTestRequestListableService baseTestRequestListableService, BaseJsonService baseJsonService) {
		super (webClient, MediaType.APPLICATION_JSON);

		this.baseTestRequestListableService = baseTestRequestListableService;
		this.baseJsonService = baseJsonService;
	}

	/*
	 * update operation
	 */

	@Given("the updated {string} json request entry with id {string} in partition {string}")
	@Given("the updated {string} json request entry with id {string} in {string} partition")
	@When("updating the {string} json request entry with id {string} in partition {string}")
	@When("updating the {string} json request entry with id {string} in {string} partition")
	@Then("update the {string} json request entry with id {string} in partition {string}")
	@Then("update the {string} json request entry with id {string} in {string} partition")
	public void updateById (String name, String id, String partition, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id, partition);

		assertTrue (request.isSuccess());
	}

	@Given("the updated json request entry with id {string} in partition {string}")
	@Given("the updated json request entry with id {string} in {string} partition")
	@When("updating the json request entry with id {string} in partition {string}")
	@When("updating the json request entry with id {string} in {string} partition")
	@Then("update the json request entry with id {string} in partition {string}")
	@Then("update the json request entry with id {string} in {string} partition")
	public void updateByIdLatest (String id, String partition, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id, partition);

		assertTrue (request.isSuccess());
	}

	@Given("a failure to update {string} json request entry with id {string} in partition {string}")
	@Given("a failure to update {string} json request entry with id {string} in {string} partition")
	@When("failing to update the {string} json request entry with id {string} in partition {string}")
	@When("failing to update the {string} json request entry with id {string} in {string} partition")
	@Then("fail to update the {string} json request entry with id {string} in partition {string}")
	@Then("fail to update the {string} json request entry with id {string} in {string} partition")
	public void updateByIdFailure (String name, String id, String partition, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id, partition);

		assertFalse (request.isSuccess());
	}

	@Given("a failure to update json request entry with id {string} in partition {string}")
	@Given("a failure to update json request entry with id {string} in {string} partition")
	@When("failing to update the json request entry with id {string} in partition {string}")
	@When("failing to update the json request entry with id {string} in {string} partition")
	@Then("fail to update the json request entry with id {string} in partition {string}")
	@Then("fail to update the json request entry with id {string} in {string} partition")
	public void updateByIdFailureLatest (String id, String partition, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id, partition);

		assertFalse (request.isSuccess());
	}
}



