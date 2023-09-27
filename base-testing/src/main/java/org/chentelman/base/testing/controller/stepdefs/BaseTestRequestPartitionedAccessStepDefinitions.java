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
 * Access Step definitions for {@link BaseTestRequest} partitioned operations
 */
public class BaseTestRequestPartitionedAccessStepDefinitions extends BaseTestRequestClient {
	private final BaseTestRequestListableService baseTestRequestListableService;

	public BaseTestRequestPartitionedAccessStepDefinitions (WebClient webClient, BaseTestRequestListableService baseTestRequestListableService) {
		super (webClient, MediaType.APPLICATION_JSON);

		this.baseTestRequestListableService = baseTestRequestListableService;
	}

	/*
	 * read all by id operation
	 */

	@Given("all {string} json request entries with id {string}")
	@When("querying for all {string} json request entries with id {string}")
	@Then("query all {string} json request entries with id {string}")
	public void readAll (String name, String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read(request, id);

		assertTrue (request.isSuccess());
	}

	@Given("all json request entries with id {string}")
	@When("querying for all json request entries with id {string}")
	@Then("query all json request entries with id {string}")
	public void readAllLatest (String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read(request, id);

		assertTrue (request.isSuccess());
	}

	/*
	 * read page by id operation
	 */

	@Given("page {int} of size {int} for {string} json request entries with id {string}")
	@When("querying for page {int} of size {int} for {string} json request entries with id {string}")
	@Then("query page {int} of size {int} for {string} json request entries with id {string}")
	public void readPage (int page, int size, String name, String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read(request, page, size, id);

		assertTrue (request.isSuccess());
	}

	@Given("page {int} of size {int} for json request entries with id {string}")
	@When("querying for page {int} of size {int} for json request entries with id {string}")
	@Then("query page {int} of size {int} for json request entries with id {string}")
	public void readPageLatest (int page, int size, String id) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read(request, page, size, id);

		assertTrue (request.isSuccess());
	}

	/*
	 * read all by partition operation
	 */

	@Given("all {string} json request entries in partition {string}")
	@Given("all {string} json request entries in {string} partition")
	@When("querying for all {string} json request entries in partition {string}")
	@When("querying for all {string} json request entries in {string} partition")
	@Then("query all {string} json request entries in partition {string}")
	@Then("query all {string} json request entries in {string} partition")
	public void readAllByPartition (String name, String partition) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		readAll(request, partition);

		assertTrue (request.isSuccess());
	}

	@Given("all json request entries in partition {string}")
	@Given("all json request entries in {string} partition")
	@When("querying for all json request entries in partition {string}")
	@When("querying for all json request entries in {string} partition")
	@Then("query all json request entries in partition {string}")
	@Then("query all json request entries in {string} partition")
	public void readAllByPartitionLatest (String partition) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		readAll(request, partition);

		assertTrue (request.isSuccess());
	}

	/*
	 * read page by partition operation
	 */

	@Given("page {int} of size {int} for {string} json request entries in partition {string}")
	@Given("page {int} of size {int} for {string} json request entries in {string} partition")
	@When("querying for page {int} of size {int} for {string} json request entries in partition {string}")
	@When("querying for page {int} of size {int} for {string} json request entries in {string} partition")
	@Then("query page {int} of size {int} for {string} json request entries in partition {string}")
	@Then("query page {int} of size {int} for {string} json request entries in {string} partition")
	public void readPageByPartition (int page, int size, String name, String partition) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		readAll(request, page, size, partition);

		assertTrue (request.isSuccess());
	}

	@Given("page {int} of size {int} for json request entries in partition {string}")
	@Given("page {int} of size {int} for json request entries in {string} partition")
	@When("querying for page {int} of size {int} for json request entries in partition {string}")
	@When("querying for page {int} of size {int} for json request entries in {string} partition")
	@Then("query page {int} of size {int} for json request entries in partition {string}")
	@Then("query page {int} of size {int} for json request entries in {string} partition")
	public void readPageByPartitionLatest (int page, int size, String partition) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		readAll(request, page, size, partition);

		assertTrue (request.isSuccess());
	}

	/*
	 * read by id operation
	 */

	@Given("the {string} json request entry with id {string} in partition {string}")
	@Given("the {string} json request entry with id {string} in {string} partition")
	@When("querying for the {string} json request entry with id {string} in partition {string}")
	@When("querying for the {string} json request entry with id {string} in {string} partition")
	@Then("query the {string} json request entry with id {string} in partition {string}")
	@Then("query the {string} json request entry with id {string} in {string} partition")
	public void readById (String name, String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read(request, id, partitionKey);

		assertTrue (request.isSuccess());
	}

	@Given("the json request entry with id {string} in partition {string}")
	@Given("the json request entry with id {string} in {string} partition")
	@When("querying for the json request entry with id {string} in partition {string}")
	@When("querying for the json request entry with id {string} in {string} partition")
	@Then("query the json request entry with id {string} in partition {string}")
	@Then("query the json request entry with id {string} in {string} partition")
	public void readByIdLatest (String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read(request, id, partitionKey);

		assertTrue (request.isSuccess());
	}

	@Given("a failure to load the {string} json request entry with id {string} in partition {string}")
	@Given("a failure to load the {string} json request entry with id {string} in {string} partition")
	@When("failing to query for the {string} json request entry with id {string} in partition {string}")
	@When("failing to query for the {string} json request entry with id {string} in {string} partition")
	@Then("fail to query the {string} json request entry with id {string} in partition {string}")
	@Then("fail to query the {string} json request entry with id {string} in {string} partition")
	@Then("the {string} json request entry with id {string} in partition {string} cannot be found")
	@Then("the {string} json request entry with id {string} in {string} partition cannot be found")
	public void readByIdFailure (String name, String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable(name);

		read(request, id, partitionKey);

		assertFalse (request.isSuccess());
	}

	@Given("a failure to load the json request entry with id {string} in partition {string}")
	@Given("a failure to load the json request entry with id {string} in {string} partition")
	@When("failing to query for the json request entry with id {string} in partition {string}")
	@When("failing to query for the json request entry with id {string} in {string} partition")
	@Then("fail to query the json request entry with id {string} in partition {string}")
	@Then("fail to query the json request entry with id {string} in {string} partition")
	@Then("the json request entry with id {string} in partition {string} cannot be found")
	@Then("the json request entry with id {string} in {string} partition cannot be found")
	public void readByIdLatestFailure (String id, String partitionKey) {
		BaseTestRequest request = this.baseTestRequestListableService.getLatestListable();

		read(request, id, partitionKey);

		assertFalse (request.isSuccess());
	}
}



