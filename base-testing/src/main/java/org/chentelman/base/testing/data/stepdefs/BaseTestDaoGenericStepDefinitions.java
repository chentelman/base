package org.chentelman.base.testing.data.stepdefs;

import org.chentelman.base.testing.data.service.BaseTestDaoListableService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * Generic Step definitions
 */
public class BaseTestDaoGenericStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoGenericStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	@Given("latest data {string}")
	@Then("set latest data to {string}")
	public void setLatest (String name) {
		this.baseTestDaoListableService.getLatestListable(name);
	}
}



