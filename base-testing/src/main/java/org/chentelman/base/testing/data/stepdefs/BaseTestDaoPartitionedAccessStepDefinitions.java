package org.chentelman.base.testing.data.stepdefs;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestPartitionedDao;
import org.springframework.data.domain.PageRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BasePartitionedAccessDao} operations
 */
public class BaseTestDaoPartitionedAccessStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoPartitionedAccessStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	/*
	 * find all by id operation
	 */

	private void findAllById (BaseTestPartitionedDao dao, String id) {
		dao.findAllById(id, true);
	}

	@Given("all {string} data entries with id {string}")
	@When("querying for all {string} data entries with id {string}")
	@Then("query all {string} data entries with id {string}")
	public void findAll (String name, String id) {
		findAllById (this.baseTestDaoListableService.getLatestListable(name, BaseTestPartitionedDao.class), id);
	}

	@Given("all data entries with id {string}")
	@When("querying for all data entries with id {string}")
	@Then("query all data entries with id {string}")
	public void findAllLatest (String id) {
		findAllById (this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), id);
	}

	/*
	 * find page by id operation
	 */

	private void findPageById (BaseTestPartitionedDao dao, String id, int page, int size) {
		dao.findAllById(PageRequest.of(page, size), id, true);
	}

	@Given("page {int} of size {int} for {string} data entries with id {string}")
	@When("querying for page {int} of size {int} for {string} data entries with id {string}")
	@Then("query page {int} of size {int} for {string} data entries with id {string}")
	public void findPage (int page, int size, String name, String id) {
		findPageById (this.baseTestDaoListableService.getLatestListable(name, BaseTestPartitionedDao.class), id, page, size);
	}

	@Given("page {int} of size {int} for data entries with id {string}")
	@When("querying for page {int} of size {int} for data entries with id {string}")
	@Then("query page {int} of size {int} for data entries with id {string}")
	public void findPageLatest (int page, int size, String id) {
		findPageById (this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), id, page, size);
	}

	/*
	 * find all by partition operation
	 */

	private void findAllByPartition (BaseTestPartitionedDao dao, String partition) {
		dao.findAll(partition, true);
	}

	@Given("all {string} data entries in partition {string}")
	@Given("all {string} data entries in {string} partition")
	@When("querying for all {string} data entries in partition {string}")
	@When("querying for all {string} data entries in {string} partition")
	@Then("query all {string} data entries in partition {string}")
	@Then("query all {string} data entries in {string} partition")
	public void findAllByPartition (String name, String partition) {
		findAllByPartition (this.baseTestDaoListableService.getLatestListable(name, BaseTestPartitionedDao.class), partition);
	}

	@Given("all data entries in partition {string}")
	@Given("all data entries in {string} partition")
	@When("querying for all data entries in partition {string}")
	@When("querying for all data entries in {string} partition")
	@Then("query all data entries in partition {string}")
	@Then("query all data entries in {string} partition")
	public void findAllByPartitionLatest (String partition) {
		findAllByPartition (this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partition);
	}

	/*
	 * find page by partition operation
	 */

	private void findPageByPartition (BaseTestPartitionedDao dao, String partition, int page, int size) {
		dao.findAll(partition, PageRequest.of(page, size), true);
	}

	@Given("page {int} of size {int} for {string} data entries in partition {string}")
	@Given("page {int} of size {int} for {string} data entries in {string} partition")
	@When("querying for page {int} of size {int} for {string} data entries in partition {string}")
	@When("querying for page {int} of size {int} for {string} data entries in {string} partition")
	@Then("query page {int} of size {int} for {string} data entries in partition {string}")
	@Then("query page {int} of size {int} for {string} data entries in {string} partition")
	public void findPageByPartition (int page, int size, String name, String partition) {
		findPageByPartition (this.baseTestDaoListableService.getLatestListable(name, BaseTestPartitionedDao.class), partition, page, size);
	}

	@Given("page {int} of size {int} for data entries in partition {string}")
	@Given("page {int} of size {int} for data entries in {string} partition")
	@When("querying for page {int} of size {int} for data entries in partition {string}")
	@When("querying for page {int} of size {int} for data entries in {string} partition")
	@Then("query page {int} of size {int} for data entries in partition {string}")
	@Then("query page {int} of size {int} for data entries in {string} partition")
	public void findPageByPartitionLatest (int page, int size, String partition) {
		findPageByPartition (this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partition, page, size);
	}

	/*
	 * count operation
	 */

	private void count (BaseTestPartitionedDao dao, String partitionKey, long expected) {
		dao.count(partitionKey, expected);
	}

	@Given("{long} {string} data entries in partition {string}")
	@Given("{long} {string} data entries in {string} partition")
	public void count (long expected, String name, String partitionKey) {
		count(this.baseTestDaoListableService.getListable(name, BaseTestPartitionedDao.class), partitionKey, expected);
	}

	@When("the number of {string} data entries in partition {string} is {long}")
	@When("the number of {string} data entries in {string} partition is {long}")
	@When("the number of {string} data entries in partition {string} are {long}")
	@When("the number of {string} data entries in {string} partition are {long}")
	public void count (String name, String partitionKey, long expected) {
		count(this.baseTestDaoListableService.getListable(name, BaseTestPartitionedDao.class), partitionKey, expected);
	}

	@Given("{long} data entries in partition {string}")
	@Given("{long} data entries in {string} partition")
	public void countLatest (long expected, String partitionKey) {
		count(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, expected);
	}

	@When("the number of data entries in partition {string} is {long}")
	@When("the number of data entries in {string} partition is {long}")
	@When("the number of data entries in partition {string} are {long}")
	@When("the number of data entries in {string} partition are {long}")
	public void countLatest (String partitionKey, long expected) {
		count(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, expected);
	}

	/*
	 * find by id operation
	 */

	private void findById (BaseTestPartitionedDao dao, String partitionKey, String id, boolean status) {
		dao.findById(partitionKey, id, status);
	}

	@Given("the {string} data entry with id {string} in partition {string}")
	@Given("the {string} data entry with id {string} in {string} partition")
	@When("querying for the {string} data entry with id {string} in partition {string}")
	@When("querying for the {string} data entry with id {string} in {string} partition")
	@Then("query the {string} data entry with id {string} in partition {string}")
	@Then("query the {string} data entry with id {string} in {string} partition")
	public void findById (String name, String id, String partitionKey) {
		findById(this.baseTestDaoListableService.getLatestListable(name, BaseTestPartitionedDao.class), partitionKey, id, true);
	}

	@Given("the data entry with id {string} in partition {string}")
	@Given("the data entry with id {string} in {string} partition")
	@When("querying for the data entry with id {string} in partition {string}")
	@When("querying for the data entry with id {string} in {string} partition")
	@Then("query the data entry with id {string} in partition {string}")
	@Then("query the data entry with id {string} in {string} partition")
	public void findByIdLatest (String id, String partitionKey) {
		findById(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, id, true);
	}

	@Given("a failure to load the {string} data entry with id {string} in partition {string}")
	@Given("a failure to load the {string} data entry with id {string} in {string} partition")
	@When("failing to query for the {string} data entry with id {string} in partition {string}")
	@When("failing to query for the {string} data entry with id {string} in {string} partition")
	@Then("fail to query the {string} data entry with id {string} in partition {string}")
	@Then("fail to query the {string} data entry with id {string} in {string} partition")
	@Then("the {string} data entry with id {string} in partition {string} cannot be found")
	@Then("the {string} data entry with id {string} in {string} partition cannot be found")
	public void findByIdFailure (String name, String id, String partitionKey) {
		findById(this.baseTestDaoListableService.getLatestListable(name, BaseTestPartitionedDao.class), partitionKey, id, false);
	}

	@Given("a failure to load the data entry with id {string} in partition {string}")
	@Given("a failure to load the data entry with id {string} in {string} partition")
	@When("failing to query for the data entry with id {string} in partition {string}")
	@When("failing to query for the data entry with id {string} in {string} partition")
	@Then("fail to query the data entry with id {string} in partition {string}")
	@Then("fail to query the data entry with id {string} in {string} partition")
	@Then("the data entry with id {string} in partition {string} cannot be found")
	@Then("the data entry with id {string} in {string} partition cannot be found")
	public void findByIdLatestFailure (String id, String partitionKey) {
		findById(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, id, false);
	}
}



