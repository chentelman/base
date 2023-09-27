package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.module.core.service.BaseCreateService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.testservice.BaseTestService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseCreateService} operations
 */
public class BaseTestServiceCreateStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServiceCreateStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * add operation
	 */

	private void add (BaseTestService service, DataTable data, boolean status) {
		service.create(status, data);
	}

	@Given("a {string} service entry")
	@When("creating a {string} service entry")
	@Then("create a {string} service entry")
	public void add (String name, DataTable data) {
		add(this.baseTestServiceListableService.getLatestListable(name), data, true);
	}

	@Given("a service entry")
	@When("creating a service entry")
	@Then("create a service entry")
	public void addLatest (DataTable data) {
		add(this.baseTestServiceListableService.getLatestListable(), data, true);
	}

	@Given("a failure to create a {string} service entry")
	@When("failing to create a {string} service entry")
	@Then("fail to create a {string} service entry")
	public void addFail (String name, DataTable data) {
		add(this.baseTestServiceListableService.getLatestListable(name), data, false);
	}

	@Given("a failure to create a service entry")
	@When("failing to create a service entry")
	@Then("fail to create a service entry")
	public void addFailLatest (DataTable data) {
		add(this.baseTestServiceListableService.getLatestListable(), data, false);
	}
}



