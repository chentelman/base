package org.chentelman.base.testing.service.stepdefs;

import org.chentelman.base.module.core.service.BasePartitionedAccessService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.testservice.BaseTestPartitionedService;
import org.springframework.data.domain.PageRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for {@link BasePartitionedAccessService} operations
 */
public class BaseTestServicePartitionedAccessStepDefinitions {

	private final BaseTestServiceListableService baseTestServiceListableService;

	public BaseTestServicePartitionedAccessStepDefinitions (BaseTestServiceListableService baseTestServiceListableService) {
		this.baseTestServiceListableService = baseTestServiceListableService;
	}

	/*
	 * read all by id operation
	 */

	private void readAllById (BaseTestPartitionedService service, String id) {
		service.readAllById(true, id);
	}

	@Given("all {string} service entries with id {string}")
	@When("querying for all {string} service entries with id {string}")
	@Then("query all {string} service entries with id {string}")
	public void readAll (String name, String id) {
		readAllById (this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class), id);
	}

	@Given("all service entries with id {string}")
	@When("querying for all service entries with id {string}")
	@Then("query all service entries with id {string}")
	public void readAllLatest (String id) {
		readAllById (this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), id);
	}

	/*
	 * read page by id operation
	 */

	private void readPageById (BaseTestPartitionedService service, String id, int page, int size) {
		service.readAllById(true, id, PageRequest.of(page, size));
	}

	@Given("page {int} of size {int} for {string} service entries with id {string}")
	@When("querying for page {int} of size {int} for {string} service entries with id {string}")
	@Then("query page {int} of size {int} for {string} service entries with id {string}")
	public void readPage (int page, int size, String name, String id) {
		readPageById (this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class), id, page, size);
	}

	@Given("page {int} of size {int} for service entries with id {string}")
	@When("querying for page {int} of size {int} for service entries with id {string}")
	@Then("query page {int} of size {int} for service entries with id {string}")
	public void readPageLatest (int page, int size, String id) {
		readPageById (this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), id, page, size);
	}

	/*
	 * read all by partition operation
	 */

	private void readAllByPartition (BaseTestPartitionedService service, String partition) {
		service.readAll(true, partition);
	}

	@Given("all {string} service entries in partition {string}")
	@Given("all {string} service entries in {string} partition")
	@When("querying for all {string} service entries in partition {string}")
	@When("querying for all {string} service entries in {string} partition")
	@Then("query all {string} service entries in partition {string}")
	@Then("query all {string} service entries in {string} partition")
	public void readAllByPartition (String name, String partition) {
		readAllByPartition (this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class), partition);
	}

	@Given("all service entries in partition {string}")
	@Given("all service entries in {string} partition")
	@When("querying for all service entries in partition {string}")
	@When("querying for all service entries in {string} partition")
	@Then("query all service entries in partition {string}")
	@Then("query all service entries in {string} partition")
	public void readAllByPartitionLatest (String partition) {
		readAllByPartition (this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partition);
	}

	/*
	 * read page by partition operation
	 */

	private void readPageByPartition (BaseTestPartitionedService service, String partition, int page, int size) {
		service.readAll(true, partition, PageRequest.of(page, size));
	}

	@Given("page {int} of size {int} for {string} service entries in partition {string}")
	@Given("page {int} of size {int} for {string} service entries in {string} partition")
	@When("querying for page {int} of size {int} for {string} service entries in partition {string}")
	@When("querying for page {int} of size {int} for {string} service entries in {string} partition")
	@Then("query page {int} of size {int} for {string} service entries in partition {string}")
	@Then("query page {int} of size {int} for {string} service entries in {string} partition")
	public void readPageByPartition (int page, int size, String name, String partition) {
		readPageByPartition (this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class), partition, page, size);
	}

	@Given("page {int} of size {int} for service entries in partition {string}")
	@Given("page {int} of size {int} for service entries in {string} partition")
	@When("querying for page {int} of size {int} for service entries in partition {string}")
	@When("querying for page {int} of size {int} for service entries in {string} partition")
	@Then("query page {int} of size {int} for service entries in partition {string}")
	@Then("query page {int} of size {int} for service entries in {string} partition")
	public void readPageByPartitionLatest (int page, int size, String partition) {
		readPageByPartition (this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partition, page, size);
	}

	/*
	 * count operation
	 */

	private void count (BaseTestPartitionedService service, String partitionKey, long expected) {
		service.count(partitionKey, expected);
	}

	@Given("{long} {string} service entries in partition {string}")
	@Given("{long} {string} service entries in {string} partition")
	public void count (long expected, String name, String partitionKey) {
		count(this.baseTestServiceListableService.getListable(name, BaseTestPartitionedService.class), partitionKey, expected);
	}

	@When("the number of {string} service entries in partition {string} is {long}")
	@When("the number of {string} service entries in {string} partition is {long}")
	@When("the number of {string} service entries in partition {string} are {long}")
	@When("the number of {string} service entries in {string} partition are {long}")
	public void count (String name, String partitionKey, long expected) {
		count(this.baseTestServiceListableService.getListable(name, BaseTestPartitionedService.class), partitionKey, expected);
	}

	@Given("{long} service entries in partition {string}")
	@Given("{long} service entries in {string} partition")
	public void countLatest (long expected, String partitionKey) {
		count(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, expected);
	}

	@When("the number of service entries in partition {string} is {long}")
	@When("the number of service entries in {string} partition is {long}")
	@When("the number of service entries in partition {string} are {long}")
	@When("the number of service entries in {string} partition are {long}")
	public void countLatest (String partitionKey, long expected) {
		count(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, expected);
	}

	/*
	 * read by id operation
	 */

	private void readById (BaseTestPartitionedService service, String partitionKey, String id, boolean status) {
		service.read(status, partitionKey, id);
	}

	@Given("the {string} service entry with id {string} in partition {string}")
	@Given("the {string} service entry with id {string} in {string} partition")
	@When("querying for the {string} service entry with id {string} in partition {string}")
	@When("querying for the {string} service entry with id {string} in {string} partition")
	@Then("query the {string} service entry with id {string} in partition {string}")
	@Then("query the {string} service entry with id {string} in {string} partition")
	public void readById (String name, String id, String partitionKey) {
		readById(this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class), partitionKey, id, true);
	}

	@Given("the service entry with id {string} in partition {string}")
	@Given("the service entry with id {string} in {string} partition")
	@When("querying for the service entry with id {string} in partition {string}")
	@When("querying for the service entry with id {string} in {string} partition")
	@Then("query the service entry with id {string} in partition {string}")
	@Then("query the service entry with id {string} in {string} partition")
	public void readByIdLatest (String id, String partitionKey) {
		readById(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, id, true);
	}

	@Given("a failure to load the {string} service entry with id {string} in partition {string}")
	@Given("a failure to load the {string} service entry with id {string} in {string} partition")
	@When("failing to query for the {string} service entry with id {string} in partition {string}")
	@When("failing to query for the {string} service entry with id {string} in {string} partition")
	@Then("fail to query the {string} service entry with id {string} in partition {string}")
	@Then("fail to query the {string} service entry with id {string} in {string} partition")
	@Then("the {string} service entry with id {string} in partition {string} cannot be found")
	@Then("the {string} service entry with id {string} in {string} partition cannot be found")
	public void readByIdFailure (String name, String id, String partitionKey) {
		readById(this.baseTestServiceListableService.getLatestListable(name, BaseTestPartitionedService.class), partitionKey, id, false);
	}

	@Given("a failure to load the service entry with id {string} in partition {string}")
	@Given("a failure to load the service entry with id {string} in {string} partition")
	@When("failing to query for the service entry with id {string} in partition {string}")
	@When("failing to query for the service entry with id {string} in {string} partition")
	@Then("fail to query the service entry with id {string} in partition {string}")
	@Then("fail to query the service entry with id {string} in {string} partition")
	@Then("the service entry with id {string} in partition {string} cannot be found")
	@Then("the service entry with id {string} in {string} partition cannot be found")
	public void readByIdLatestFailure (String id, String partitionKey) {
		readById(this.baseTestServiceListableService.getLatestListable(BaseTestPartitionedService.class), partitionKey, id, false);
	}
}



