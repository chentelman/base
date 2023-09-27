package org.chentelman.base.testing.data.stepdefs;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestDao;
import org.springframework.data.domain.PageRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BaseAccessDao} operations
 */
public class BaseTestDaoAccessStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoAccessStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	/*
	 * find all operation
	 */

	private void findAll (BaseTestDao dao) {
		dao.findAll(true);
	}

	@Given("all {string} data entries")
	@When("querying for all {string} data entries")
	@Then("query all {string} data entries")
	public void findAll (String name) {
		findAll(this.baseTestDaoListableService.getLatestListable(name));
	}

	@Given("all data entries")
	@When("querying for all data entries")
	@Then("query all data entries")
	public void findAllLatest () {
		findAll(this.baseTestDaoListableService.getLatestListable());
	}

	/*
	 * find page operation
	 */

	private void findAll (BaseTestDao dao, int page, int size) {
		dao.findAll(PageRequest.of(page, size), true);
	}

	@Given("page {int} of size {int} for {string} data entries")
	@When("querying for page {int} of size {int} for {string} data entries")
	@Then("query page {int} of size {int} for {string} data entries")
	public void findPage (int page, int size, String name) {
		findAll (this.baseTestDaoListableService.getLatestListable(name), page, size);
	}

	@Given("page {int} of size {int} for data entries")
	@When("querying for page {int} of size {int} for data entries")
	@Then("query page {int} of size {int} for data entries")
	public void findPageLatest (int page, int size) {
		findAll (this.baseTestDaoListableService.getLatestListable(), page, size);
	}

	/*
	 * find by id operation
	 */

	private void findById (BaseTestDao dao, String id, boolean status) {
		dao.findById(id, status);
	}

	@Given("the {string} data entry with id {string}")
	@When("querying for the {string} data entry with id {string}")
	@Then("query the {string} data entry with id {string}")
	public void findById (String name, String id) {
		findById(this.baseTestDaoListableService.getLatestListable(name), id, true);
	}

	@Given("the data entry with id {string}")
	@When("querying for the data entry with id {string}")
	@Then("query the data entry with id {string}")
	public void findByIdLatest (String id) {
		findById(this.baseTestDaoListableService.getLatestListable(), id, true);
	}

	@Given("a failure to load the {string} data entry with id {string}")
	@When("failing to query for the {string} data entry with id {string}")
	@Then("fail to query the {string} data entry with id {string}")
	@Then("the {string} data entry with id {string} cannot be found")
	public void findByIdFailure (String name, String id) {
		findById(this.baseTestDaoListableService.getLatestListable(name), id, false);
	}

	@Given("a failure to load the data entry with id {string}")
	@When("failing to query for the data entry with id {string}")
	@Then("fail to query the data entry with id {string}")
	@Then("the data entry with id {string} cannot be found")
	public void findByIdLatestFailure (String id) {
		findById(this.baseTestDaoListableService.getLatestListable(), id, false);
	}

	/*
	 * find latest operation
	 */

	private void findLatest (BaseTestDao dao, boolean status) {
		dao.findLatest(status);
	}

	@Given("the latest {string} data entry")
	@When("querying for the latest {string} data entry")
	@Then("query the latest {string} data entry")
	public void findLatest (String name) {
		findLatest(this.baseTestDaoListableService.getLatestListable(name), true);
	}

	@Given("the latest data entry")
	@When("querying for the latest data entry")
	@Then("query the latest data entry")
	public void findLatestLatest () {
		findLatest(this.baseTestDaoListableService.getLatestListable(), true);
	}

	@Given("a failure to load the latest {string} data entry")
	@When("failing to query for the latest {string} data entry")
	@Then("fail to query the latest {string} data entry")
	@Then("the latest {string} data entry cannot be found")
	public void findLatestFailure (String name) {
		findLatest(this.baseTestDaoListableService.getLatestListable(name), false);
	}

	@Given("a failure to load the latest data entry")
	@When("failing to query for the latest data entry")
	@Then("fail to query the latest data entry")
	@Then("the latest data entry cannot be found")
	public void findLatestLatestFailure () {
		findLatest(this.baseTestDaoListableService.getLatestListable(), false);
	}

	/*
	 * count operation
	 */

	private void count (BaseTestDao dao, long expected) {
		dao.count(expected);
	}

	@Given("{long} {string} data entries")
	public void count (long expected, String name) {
		count(this.baseTestDaoListableService.getLatestListable(name), expected);
	}

	@When("the number of {string} data entries is {long}")
	@When("the number of {string} data entries are {long}")
	public void count (String name, long expected) {
		count(this.baseTestDaoListableService.getLatestListable(name), expected);
	}

	@Given("{long} data entries")
	@When("the number of data entries is {long}")
	@When("the number of data entries are {long}")
	public void countLatest (long expected) {
		count(this.baseTestDaoListableService.getLatestListable(), expected);
	}
}



