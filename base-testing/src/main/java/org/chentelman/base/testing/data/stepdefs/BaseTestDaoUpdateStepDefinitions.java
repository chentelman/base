package org.chentelman.base.testing.data.stepdefs;

import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestDao;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseUpdateDao} operations
 */
public class BaseTestDaoUpdateStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoUpdateStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	/*
	 * update operation
	 */

	private void update (BaseTestDao dao, DataTable data, boolean status) {
		dao.update(data, status);
	}

	@Given("the updated {string} data entry")
	@When("updating the {string} data entry")
	@Then("update the {string} data entry")
	public void update (String name, DataTable data) {
		update(this.baseTestDaoListableService.getLatestListable(name), data, true);
	}

	@Given("the updated data entry")
	@When("updating the data entry")
	@Then("update the data entry")
	public void updateLatest (DataTable data) {
		update(this.baseTestDaoListableService.getLatestListable(), data, true);
	}

	@Given("a failure to update {string} data entry")
	@When("failing to update the {string} data entry")
	@Then("fail to update the {string} data entry with")
	public void updateFailure (String name, DataTable data) {
		update(this.baseTestDaoListableService.getLatestListable(name), data, false);
	}

	@Given("a failure to update data entry")
	@When("failing to update the data entry")
	@Then("fail to update the data entry")
	public void updateFailureLatest (DataTable data) {
		update(this.baseTestDaoListableService.getLatestListable(), data, false);
	}

	/*
	 * update latest operation
	 */

	private void updateLatest (BaseTestDao dao, DataTable data, boolean status) {
		dao.updateLatest(data, status);
	}

	@When("updating the latest {string} data entry")
	@Then("update the latest {string} data entry")
	public void updateLatest (String name, DataTable data) {
		updateLatest(this.baseTestDaoListableService.getLatestListable(name), data, true);
	}

	@When("updating the latest data entry")
	@Then("update the latest data entry")
	public void updateLatestLatest (DataTable data) {
		updateLatest(this.baseTestDaoListableService.getLatestListable(), data, true);
	}

	@When("failing to update the latest {string} data entry")
	@Then("fail to update the latest {string} data entry")
	public void updateLatestFailure (String name, DataTable data) {
		updateLatest(this.baseTestDaoListableService.getLatestListable(name), data, false);
	}

	@When("failing to update the latest data entry")
	@Then("fail to update the latest data entry")
	public void updateLatestLatestFailure (DataTable data) {
		updateLatest(this.baseTestDaoListableService.getLatestListable(), data, false);
	}
}



