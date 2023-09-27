package org.chentelman.base.testing.data.stepdefs;

import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestDao;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseCreateDao} operations
 */
public class BaseTestDaoCreateStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoCreateStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	/*
	 * add operation
	 */

	private void add (BaseTestDao dao, DataTable data, boolean status) {
		dao.add(data, status);
	}

	@Given("a {string} data entry")
	@When("creating a {string} data entry")
	@Then("create a {string} data entry")
	public void add (String name, DataTable data) {
		add(this.baseTestDaoListableService.getLatestListable(name), data, true);
	}

	@Given("a data entry")
	@When("creating a data entry")
	@Then("create a data entry")
	public void addLatest (DataTable data) {
		add(this.baseTestDaoListableService.getLatestListable(), data, true);
	}

	@Given("a failure to create a {string} data entry")
	@When("failing to create a {string} data entry")
	@Then("fail to create a {string} data entry")
	public void addFail (String name, DataTable data) {
		add(this.baseTestDaoListableService.getLatestListable(name), data, false);
	}

	@Given("a failure to create a data entry")
	@When("failing to create a data entry")
	@Then("fail to create a data entry")
	public void addFailLatest (DataTable data) {
		add(this.baseTestDaoListableService.getLatestListable(), data, false);
	}

}



