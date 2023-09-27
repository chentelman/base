package org.chentelman.base.testing.data.stepdefs;

import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestPartitionedDao;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BasePartitionedDeleteDao} operations
 */
public class BaseTestDaoPartitionedDeleteStepDefinitions {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public BaseTestDaoPartitionedDeleteStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	/*
	 * delete by id operation
	 */

	private void deleteById (BaseTestPartitionedDao dao, String partitionKey, String id, boolean status) {
		dao.deleteById(partitionKey, id, status);
	}

	@When("deleting the {string} data entry with id {string} from {string} partition")
	@Then("delete the {string} data entry with id {string} from {string} partition")
	public void deleteById (String name, String id, String partitionKey) {
		deleteById(this.baseTestDaoListableService.getListable(name, BaseTestPartitionedDao.class), partitionKey, id, true);
	}

	@When("deleting the data entry with id {string} from {string} partition")
	@Then("delete the data entry with id {string} from {string} partition")
	public void deleteByIdLatest (String id, String partitionKey) {
		deleteById(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, id, true);
	}

	@When("failing to delete the {string} data entry with id {string} from {string} partition")
	@Then("fail to delete the {string} data entry with id {string} from {string} partition")
	public void deleteByIdFailure (String name, String id, String partitionKey) {
		deleteById(this.baseTestDaoListableService.getListable(name, BaseTestPartitionedDao.class), partitionKey, id, false);
	}

	@When("failing to delete the data entry with id {string} from {string} partition")
	@Then("fail to delete the data entry with id {string} from {string} partition")
	public void deleteByIdLatestFailure (String id, String partitionKey) {
		deleteById(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, id, false);
	}

	/*
	 * delete by partition key operation
	 */

	private void deleteByPartitionKey (BaseTestPartitionedDao dao, String partitionKey, boolean status) {
		dao.deleteByPartitionKey(partitionKey, status);
	}

	@When("deleting all {string} data entries in {string} partition")
	@Then("delete all {string} data entries in {string} partition")
	public void deleteLatest (String name, String partitionKey) {
		deleteByPartitionKey(this.baseTestDaoListableService.getListable(name, BaseTestPartitionedDao.class), partitionKey, true);
	}

	@When("deleting all data entries in {string} partition")
	@Then("delete all data entries in {string} partition")
	public void deleteLatestLatest (String partitionKey) {
		deleteByPartitionKey(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, true);
	}

	@When("failing to delete all {string} data entries in {string} partition")
	@Then("fail to delete all {string} data entries in {string} partition")
	public void deleteLatestFailure (String name, String partitionKey) {
		deleteByPartitionKey(this.baseTestDaoListableService.getListable(name, BaseTestPartitionedDao.class), partitionKey, false);
	}

	@When("failing to delete all data entries in {string} partition")
	@Then("fail to delete all data entries in {string} partition")
	public void deleteLatestLatestFailure (String partitionKey) {
		deleteByPartitionKey(this.baseTestDaoListableService.getLatestListable(BaseTestPartitionedDao.class), partitionKey, false);
	}
}



