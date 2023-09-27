package org.chentelman.base.testing.controller.stepdefs;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.testresults.BaseTestRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * Generic Step definitions for {@link BaseTestRequest} operations
 */
public class BaseTestRequestGenericStepDefinitions extends BaseComponent {
	private final BaseTestRequestListableService baseTestRequestListableService;

	public BaseTestRequestGenericStepDefinitions (BaseTestRequestListableService baseTestRequestListableService) {
		this.baseTestRequestListableService = baseTestRequestListableService;
	}

	@Given("latest json request {string}")
	@Then("set latest json request to {string}")
	public void setLatest (String name) {
		this.baseTestRequestListableService.getLatestListable(name);
	}
}



