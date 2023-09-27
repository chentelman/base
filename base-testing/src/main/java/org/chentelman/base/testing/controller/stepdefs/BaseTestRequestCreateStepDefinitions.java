package org.chentelman.base.testing.controller.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * Create Step definitions for {@link BaseTestRequest} operations
 */
public class BaseTestRequestCreateStepDefinitions extends BaseTestRequestClient {
	private final BaseTestRequestListableService baseTestRequestListableService;
	private final BaseJsonService baseJsonService;

	public BaseTestRequestCreateStepDefinitions (WebClient webClient, BaseTestRequestListableService baseTestRequestListableService, BaseJsonService baseJsonService) {
		super (webClient, MediaType.APPLICATION_JSON);

		this.baseTestRequestListableService = baseTestRequestListableService;
		this.baseJsonService = baseJsonService;
	}

	/*
	 * add operation
	 */

	@Given("a {string} json request entry")
	@When("creating a {string} json request entry")
	@Then("create a {string} json request entry")
	public void add (String name, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		add(request, baseJsonService.generateJsonObject(data).toString(), mediaType);

		assertEquals (true, request.isSuccess());
	}

	@Given("a json request entry")
	@When("creating a json request entry")
	@Then("create a json request entry")
	public void addLatest (DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		add(request, baseJsonService.generateJsonObject(data).toString(), mediaType);

		assertEquals (true, request.isSuccess());
	}

	@Given("a failure to create a {string} json request entry")
	@When("failing to create a {string} json request entry")
	@Then("fail to create a {string} json request entry")
	public void addFail (String name, DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		add(request, baseJsonService.generateJsonObject(data).toString(), mediaType);

		assertEquals (false, request.isSuccess());
	}

	@Given("a failure to create a json request entry")
	@When("failing to create a json request entry")
	@Then("fail to create a json request entry")
	public void addFailLatest (DataTable data) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		add(request, baseJsonService.generateJsonObject(data).toString(), mediaType);

		assertEquals (false, request.isSuccess());
	}
}



