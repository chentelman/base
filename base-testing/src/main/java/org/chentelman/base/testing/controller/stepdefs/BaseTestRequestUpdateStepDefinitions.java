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
 * Update Step definitions for {@link BaseTestRequest} operations
 */
public class BaseTestRequestUpdateStepDefinitions extends BaseTestRequestClient {
	private final BaseTestRequestListableService baseTestRequestListableService;
	private final BaseJsonService baseJsonService;

	public BaseTestRequestUpdateStepDefinitions (WebClient webClient, BaseTestRequestListableService baseTestRequestListableService, BaseJsonService baseJsonService) {
		super (webClient, MediaType.APPLICATION_JSON);

		this.baseTestRequestListableService = baseTestRequestListableService;
		this.baseJsonService = baseJsonService;
	}

	/*
	 * update operation
	 */

	@Given("the updated {string} json request entry with id {string}")
	@When("updating the {string} json request entry with id {string}")
	@Then("update the {string} json request entry with id {string}")
	public void updateById (String name, String id, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id);

		assertTrue (request.isSuccess());
	}

	@Given("the updated json request entry with id {string}")
	@When("updating the json request entry with id {string}")
	@Then("update the json request entry with id {string}")
	public void updateByIdLatest (String id, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id);

		assertTrue (request.isSuccess());
	}

	@Given("a failure to update {string} json request entry with id {string}")
	@When("failing to update the {string} json request entry with id {string}")
	@Then("fail to update the {string} json request entry with id {string}")
	public void updateByIdFailure (String name, String id, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id);

		assertFalse (request.isSuccess());
	}

	@Given("a failure to update json request entry with id {string}")
	@When("failing to update the json request entry with id {string}")
	@Then("fail to update the json request entry with id {string}")
	public void updateByIdFailureLatest (String id, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, id);

		assertFalse (request.isSuccess());
	}

	/*
	 * update operation
	 */

	@When("updating the latest {string} json request entry")
	@Then("update the latest {string} json request entry")
	public void updateLatest (String name, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, request.getLatestId());

		assertTrue (request.isSuccess());
	}

	@When("updating the latest json request entry")
	@Then("update the latest json request entry")
	public void updateLatestLatest (DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, request.getLatestId());

		assertTrue (request.isSuccess());
	}

	@When("failing to update the latest {string} json request entry")
	@Then("fail to update the latest {string} json request entry")
	public void updateLatestFailure (String name, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, request.getLatestId());

		assertFalse (request.isSuccess());
	}

	@When("failing to update the latest json request entry")
	@Then("fail to update the latest json request entry")
	public void updateLatestLatestFailure (DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		update(request, baseJsonService.generateJsonObject(data).toString(), mediaType, request.getLatestId());

		assertFalse (request.isSuccess());
	}
}



