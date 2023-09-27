package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.module.core.service.BaseAccessService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.testservice.BaseTestService;
import org.springframework.data.domain.PageRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseAccessService} operations
 */
public class BaseTestServiceAccessStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServiceAccessStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * find all operation
	 */

	private void readAll (BaseTestService service) {
		service.readAll(true);
	}

	@Given("all {string} service entries")
	@When("querying for all {string} service entries")
	@Then("query all {string} service entries")
	public void readAll (String name) {
		readAll(this.baseTestServiceListableService.getLatestListable(name));
	}

	@Given("all service entries")
	@When("querying for all service entries")
	@Then("query all service entries")
	public void readAllLatest () {
		readAll(this.baseTestServiceListableService.getLatestListable());
	}

	/*
	 * find page operation
	 */

	private void readAll (BaseTestService service, int page, int size) {
		service.readAll(true, PageRequest.of(page, size));
	}

	@Given("page {int} of size {int} for {string} service entries")
	@When("querying for page {int} of size {int} for {string} service entries")
	@Then("query page {int} of size {int} for {string} service entries")
	public void findPage (int page, int size, String name) {
		readAll (this.baseTestServiceListableService.getLatestListable(name), page, size);
	}

	@Given("page {int} of size {int} for service entries")
	@When("querying for page {int} of size {int} for service entries")
	@Then("query page {int} of size {int} for service entries")
	public void findPageLatest (int page, int size) {
		readAll (this.baseTestServiceListableService.getLatestListable(), page, size);
	}

	/*
	 * find by id operation
	 */

	private void read (BaseTestService service, String id, boolean status) {
		service.read(status, id);
	}

	@Given("the {string} service entry with id {string}")
	@When("querying for the {string} service entry with id {string}")
	@Then("query the {string} service entry with id {string}")
	public void readById (String name, String id) {
		read(this.baseTestServiceListableService.getLatestListable(name), id, true);
	}

	@Given("the service entry with id {string}")
	@When("querying for the service entry with id {string}")
	@Then("query the service entry with id {string}")
	public void readByIdLatest (String id) {
		read(this.baseTestServiceListableService.getLatestListable(), id, true);
	}

	@Given("a failure to load the {string} service entry with id {string}")
	@When("failing to query for the {string} service entry with id {string}")
	@Then("fail to query the {string} service entry with id {string}")
	@Then("the {string} service entry with id {string} cannot be found")
	public void readByIdFailure (String name, String id) {
		read(this.baseTestServiceListableService.getLatestListable(name), id, false);
	}

	@Given("a failure to load the service entry with id {string}")
	@When("failing to query for the service entry with id {string}")
	@Then("fail to query the service entry with id {string}")
	@Then("the service entry with id {string} cannot be found")
	public void readByIdLatestFailure (String id) {
		read(this.baseTestServiceListableService.getLatestListable(), id, false);
	}

	/*
	 * find latest operation
	 */

	private void readLatest (BaseTestService service, boolean status) {
		service.readLatest(status);
	}

	@Given("the latest {string} service entry")
	@When("querying for the latest {string} service entry")
	@Then("query the latest {string} service entry")
	public void readLatest (String name) {
		readLatest(this.baseTestServiceListableService.getLatestListable(name), true);
	}

	@Given("the latest service entry")
	@When("querying for the latest service entry")
	@Then("query the latest service entry")
	public void readLatestLatest () {
		readLatest(this.baseTestServiceListableService.getLatestListable(), true);
	}

	@Given("a failure to load the latest {string} service entry")
	@When("failing to query for the latest {string} service entry")
	@Then("fail to query the latest {string} service entry")
	@Then("the latest {string} service entry cannot be found")
	public void readLatestFailure (String name) {
		readLatest(this.baseTestServiceListableService.getLatestListable(name), false);
	}

	@Given("a failure to load the latest service entry")
	@When("failing to query for the latest service entry")
	@Then("fail to query the latest service entry")
	@Then("the latest service entry cannot be found")
	public void readLatestLatestFailure () {
		readLatest(this.baseTestServiceListableService.getLatestListable(), false);
	}

	/*
	 * count operation
	 */

	private void count (BaseTestService service, long expected) {
		service.count(true, expected);
	}

	@Given("{long} {string} service entries")
	public void count (long expected, String name) {
		count(this.baseTestServiceListableService.getLatestListable(name), expected);
	}

	@When("the number of {string} service entries is {long}")
	@When("the number of {string} service entries are {long}")
	public void count (String name, long expected) {
		count(this.baseTestServiceListableService.getLatestListable(name), expected);
	}

	@Given("{long} service entries")
	@When("the number of service entries is {long}")
	@When("the number of service entries are {long}")
	public void countLatest (long expected) {
		count(this.baseTestServiceListableService.getLatestListable(), expected);
	}
}



