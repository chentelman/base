package org.chentelman.base.testing.controller.stepdefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.testing.controller.service.BaseTestRequestClient;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Delete Step definitions for {@link BaseTestRequest} operations
 */
public class BaseTestRequestDeleteStepDefinitions extends BaseTestRequestClient {
	private final BaseTestRequestListableService baseTestRequestListableService;

	public BaseTestRequestDeleteStepDefinitions (WebClient webClient, BaseTestRequestListableService baseTestRequestListableService) {
		super (webClient, MediaType.APPLICATION_JSON);

		this.baseTestRequestListableService = baseTestRequestListableService;
	}

	/*
	 * delete by id operation
	 */

	@When("deleting the {string} json request entry with id {string}")
	@Then("delete the {string} json request entry with id {string}")
	public void deleteById (String name, String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		delete(request, id);

		assertTrue (request.isSuccess());
	}

	@When("deleting the json request entry with id {string}")
	@Then("delete the json request entry with id {string}")
	public void deleteByIdLatest (String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		delete(request, id);

		assertTrue (request.isSuccess());
	}

	@When("failing to delete the {string} json request entry with id {string}")
	@Then("fail to delete the {string} json request entry with id {string}")
	public void deleteByIdFailure (String name, String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		delete(request, id);

		assertFalse (request.isSuccess());
	}

	@When("failing to delete the json request entry with id {string}")
	@Then("fail to delete the json request entry with id {string}")
	public void deleteByIdFailureLatest (String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		delete(request, id);

		assertFalse (request.isSuccess());
	}

	/*
	 * delete latest operation
	 */

	@When("deleting the latest {string} json request entry")
	@Then("delete the latest {string} json request entry")
	public void deleteLatest (String name) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		delete (request, request.getLatestId());

		assertTrue (request.isSuccess());
	}

	@When("deleting the latest json request entry")
	@Then("delete the latest json request entry")
	public void deleteLatestLatest () {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		delete (request, request.getLatestId());

		assertTrue (request.isSuccess());
	}

	@When("failing to delete the latest {string} json request entry")
	@Then("fail to delete the latest {string} json request entry")
	public void deleteLatestFailure (String name) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		delete (request, request.getLatestId());

		assertFalse (request.isSuccess());
	}

	@When("failing to delete the latest json request entry")
	@Then("fail to delete the latest json request entry")
	public void deleteLatestLatestFailure () {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		delete (request, request.getLatestId());

		assertFalse (request.isSuccess());
	}
}



