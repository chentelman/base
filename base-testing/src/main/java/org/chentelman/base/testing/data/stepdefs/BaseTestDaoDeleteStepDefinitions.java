package org.chentelman.base.testing.data.stepdefs;

import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestDao;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseDeleteDao} operations
 */
public class BaseTestDaoDeleteStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoDeleteStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	/*
	 * delete by id operation
	 */

	private void deleteById (BaseTestDao dao, String id, boolean status) {
		dao.deleteById(id, status);
	}

	@When("deleting the {string} data entry with id {string}")
	@Then("delete the {string} data entry with id {string}")
	public void deleteById (String name, String id) {
		deleteById(this.baseTestDaoListableService.getListable(name), id, true);
	}

	@When("deleting the data entry with id {string}")
	@Then("delete the data entry with id {string}")
	public void deleteByIdLatest (String id) {
		deleteById(this.baseTestDaoListableService.getLatestListable(), id, true);
	}

	@When("failing to delete the {string} data entry with id {string}")
	@Then("fail to delete the {string} data entry with id {string}")
	public void deleteByIdFailure (String name, String id) {
		deleteById(this.baseTestDaoListableService.getListable(name), id, false);
	}

	@When("failing to delete the data entry with id {string}")
	@Then("fail to delete the data entry with id {string}")
	public void deleteByIdFailureLatest (String id) {
		deleteById(this.baseTestDaoListableService.getLatestListable(), id, false);
	}

	/*
	 * delete latest operation
	 */

	private void deleteLatest (BaseTestDao dao, boolean status) {
		dao.deleteLatest(status);
	}

	@When("deleting the latest {string} data entry")
	@Then("delete the latest {string} data entry")
	public void deleteLatest (String name) {
		deleteLatest(this.baseTestDaoListableService.getListable(name), true);
	}

	@When("deleting the latest data entry")
	@Then("delete the latest data entry")
	public void deleteLatestLatest () {
		deleteLatest(this.baseTestDaoListableService.getLatestListable(), true);
	}

	@When("failing to delete the latest {string} data entry")
	@Then("fail to delete the latest {string} data entry")
	public void deleteLatestFailure (String name) {
		deleteLatest(this.baseTestDaoListableService.getListable(name), false);
	}

	@When("failing to delete the latest data entry")
	@Then("fail to delete the latest data entry")
	public void deleteLatestLatestFailure () {
		deleteLatest(this.baseTestDaoListableService.getLatestListable(), false);
	}

	/*
	 * delete all
	 */

	@Given("a clean {string} data repository")
	@Then("clean the {string} data repository")
	public void deleteAll (String name) {
		this.baseTestDaoListableService.getListable(name).deleteAll();
	}

	@Given("a clean repository")
	@Then("clean the repository")
	public void deleteAllLatest () {
		this.baseTestDaoListableService.getLatestListable().deleteAll();
	}
}



