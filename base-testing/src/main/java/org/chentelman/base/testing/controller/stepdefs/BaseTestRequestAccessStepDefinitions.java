package org.chentelman.base.testing.controller.stepdefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.testing.controller.service.BaseTestRequestClient;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Access Step definitions for {@link BaseTestRequest} operations
 */
public class BaseTestRequestAccessStepDefinitions extends BaseTestRequestClient {
	private final BaseTestRequestListableService baseTestRequestListableService;

	public BaseTestRequestAccessStepDefinitions (WebClient webClient, BaseTestRequestListableService baseTestRequestListableService) {
		super (webClient, MediaType.APPLICATION_JSON);

		this.baseTestRequestListableService = baseTestRequestListableService;
	}

	/*
	 * find all operation
	 */

	@Given("all {string} json request entries")
	@When("querying for all {string} json request entries")
	@Then("query all {string} json request entries")
	public void readAll (String name) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		readAll(request);

		assertTrue (request.isSuccess());
	}

	@Given("all json request entries")
	@When("querying for all json request entries")
	@Then("query all json request entries")
	public void readAllLatest () {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		readAll(request);

		assertTrue (request.isSuccess());
	}

	/*
	 * find page operation
	 */

	@Given("page {int} of size {int} for {string} json request entries")
	@When("querying for page {int} of size {int} for {string} json request entries")
	@Then("query page {int} of size {int} for {string} json request entries")
	public void findPage (int page, int size, String name) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		readAll(request, page, size);

		assertTrue (request.isSuccess());
	}

	@Given("page {int} of size {int} for json request entries")
	@When("querying for page {int} of size {int} for json request entries")
	@Then("query page {int} of size {int} for json request entries")
	public void findPageLatest (int page, int size) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		readAll(request, page, size);

		assertTrue (request.isSuccess());
	}

	/*
	 * find by id operation
	 */

	@Given("the {string} json request entry with id {string}")
	@When("querying for the {string} json request entry with id {string}")
	@Then("query the {string} json request entry with id {string}")
	public void readById (String name, String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read(request, id);

		assertTrue (request.isSuccess());
	}

	@Given("the json request entry with id {string}")
	@When("querying for the json request entry with id {string}")
	@Then("query the json request entry with id {string}")
	public void readByIdLatest (String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read(request, id);

		assertTrue (request.isSuccess());
	}

	@Given("a failure to load the {string} json request entry with id {string}")
	@When("failing to query for the {string} json request entry with id {string}")
	@Then("fail to query the {string} json request entry with id {string}")
	@Then("the {string} json request entry with id {string} cannot be found")
	public void readByIdFailure (String name, String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read(request, id);

		assertFalse (request.isSuccess());
	}

	@Given("a failure to load the json request entry with id {string}")
	@When("failing to query for the json request entry with id {string}")
	@Then("fail to query the json request entry with id {string}")
	@Then("the json request entry with id {string} cannot be found")
	public void readByIdLatestFailure (String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read(request, id);

		assertFalse (request.isSuccess());
	}

	/*
	 * find latest operation
	 */

	@Given("the latest {string} json request entry")
	@When("querying for the latest {string} json request entry")
	@Then("query the latest {string} json request entry")
	public void readLatest (String name) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read (request, request.getLatestId());

		assertTrue (request.isSuccess());
	}

	@Given("the latest json request entry")
	@When("querying for the latest json request entry")
	@Then("query the latest json request entry")
	public void readLatestLatest () {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read (request, request.getLatestId());

		assertTrue (request.isSuccess());
	}

	@Given("a failure to load the latest {string} json request entry")
	@When("failing to query for the latest {string} json request entry")
	@Then("fail to query the latest {string} json request entry")
	@Then("the latest {string} json request entry cannot be found")
	public void readLatestFailure (String name) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read (request, request.getLatestId());

		assertFalse (request.isSuccess());
	}

	@Given("a failure to load the latest json request entry")
	@When("failing to query for the latest json request entry")
	@Then("fail to query the latest json request entry")
	@Then("the latest json request entry cannot be found")
	public void readLatestLatestFailure () {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read (request, request.getLatestId());

		assertFalse (request.isSuccess());
	}
}



