package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.testing.service.service.BaseTestServiceListableService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * Generic Step definitions
 */
public class BaseTestServiceGenericStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServiceGenericStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	@Given("latest service {string}")
	@Then("set latest service to {string}")
	public void setLatest (String name) {
		this.baseTestServiceListableService.getLatestListable(name);
	}
}



