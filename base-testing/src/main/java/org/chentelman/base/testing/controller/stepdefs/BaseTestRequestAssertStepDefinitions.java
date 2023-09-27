package org.chentelman.base.testing.controller.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.testresults.BaseTestJsonRequest;
import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.chentelman.base.testing.json.BaseJsonService;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Assert Step definitions for {@link BaseTestRequest} operations
 */
public class BaseTestRequestAssertStepDefinitions extends BaseComponent {
	private final BaseTestRequestListableService baseTestRequestListableService;
	private final BaseJsonService baseJsonService;

	public BaseTestRequestAssertStepDefinitions (BaseTestRequestListableService baseTestRequestListableService, BaseJsonService baseJsonService) {
		this.baseTestRequestListableService = baseTestRequestListableService;
		this.baseJsonService = baseJsonService;
	}

	private JsonArray getJsonArray (BaseTestJsonRequest request) {
		JsonArray array = request.getLatestArr();

		assertNotNull (array, "latest json request does not contain an array");

		return array;
	}

	private JsonObject getJsonObject (BaseTestJsonRequest request) {
		JsonObject object = request.getLatestObj();

		assertNotNull (object, "latest json request does not contain an object");

		return object;
	}

	private JsonObject getJsonObject (BaseTestJsonRequest request, int index) {
		JsonArray array = getJsonArray (request);

		assertTrue(array.size() > index, "latest json request has only " + array.size() + " items in array");

		JsonElement element = array.get(index);

		assertNotNull(element);
		assertTrue(element.isJsonObject());

		return element.getAsJsonObject();
	}

	/*
	 * assert data operation
	 */

	private void assertData (BaseTestJsonRequest request, DataTable data, boolean status) {
		JsonObject object = getJsonObject (request);

		assertEquals(status, baseJsonService.jsonObjectMatches(object, data));
	}

	@When("the latest {string} json request entry matches")
	public void assertData (String name, DataTable data) {
		assertData(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), data, true);
	}

	@When("the latest json request entry matches")
	public void assertDataLatest (DataTable data) {
		assertData(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), data, true);
	}

	@When("the latest {string} json request entry does not match")
	public void assertNotData (String name, DataTable data) {
		assertData(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), data, false);
	}

	@When("the latest json request entry does not match")
	public void assertNotDataLatest (DataTable data) {
		assertData(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), data, false);
	}

	/*
	 * assert data in array operation
	 */

	private void assertArrayData (BaseTestJsonRequest request, int index, DataTable data, boolean status) {
		JsonObject object = getJsonObject (request, index);

		assertEquals(status, baseJsonService.jsonObjectMatches(object, data), object.toString());
	}

	@When("the latest {string} json request entry at index {int} matches")
	public void assertArrayData (String name, int index, DataTable data) {
		assertArrayData(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), index, data, true);
	}

	@When("the latest json request entry at index {int} matches")
	public void assertArrayDataLatest (int index, DataTable data) {
		assertArrayData(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), index, data, true);
	}

	@When("the latest {string} json request entry at index {int} does not match")
	public void assertArrayNotData (String name, int index, DataTable data) {
		assertArrayData(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), index, data, false);
	}

	@When("the latest json request entry at index {int} does not match")
	public void assertArrayNotDataLatest (int index, DataTable data) {
		assertArrayData(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), index, data, false);
	}

	/*
	 * assert array contains data operation
	 */

	private void assertArrayContains (BaseTestJsonRequest request, DataTable data, boolean status) {
		JsonArray array = getJsonArray (request);

		assertEquals(status, baseJsonService.jsonArrayContainsJsonObject(array, data));
	}

	@When("the latest {string} json request entries contain")
	public void assertArrayContains (String name, DataTable data) {
		assertArrayContains(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), data, true);
	}

	@When("the latest json request entries contain")
	public void assertArrayContainsLatest (DataTable data) {
		assertArrayContains(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), data, true);
	}

	@When("the latest {string} json request entries do not contain")
	public void assertArrayNotContains (String name, DataTable data) {
		assertArrayContains(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), data, false);
	}

	@When("the latest json request entries do not contain")
	public void assertArrayNotContainsLatest (DataTable data) {
		assertArrayContains(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), data, false);
	}

	/*
	 * assert count operation
	 */

	private void assertCount (BaseTestJsonRequest request, int count) {
		JsonArray array = getJsonArray (request);

		assertEquals(count, array.size());
	}

	@Then("the number of latest {string} json request entries are {int}")
	@Then("the number of latest {string} json request entries is {int}")
	@When("the count of latest {string} json request entries is {int}")
	public void assertCount (String name, int count) {
		assertCount(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), count);
	}

	@Then("the number of latest json request entries are {int}")
	@Then("the number of latest json request entries is {int}")
	@When("the count of latest json request entries is {int}")
	public void assertCountLatest (int count) {
		assertCount(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), count);
	}

	/*
	 * assert not count operation
	 */

	private void assertNotCount (BaseTestJsonRequest request, int count) {
		JsonArray array = getJsonArray (request);

		assertNotEquals(count, array.size());
	}

	@Then("the number of latest {string} json request entries are not {int}")
	@Then("the number of latest {string} json request entries is not {int}")
	@When("the count of latest {string} json request entries is not {int}")
	public void assertNotCount (String name, int count) {
		assertNotCount(this.baseTestRequestListableService.getListable(name, BaseTestJsonRequest.class), count);
	}

	@Then("the number of latest json request entries are not {int}")
	@Then("the number of latest json request entries is not {int}")
	@When("the count of latest json request entries is not {int}")
	public void assertNotCountLatest (int count) {
		assertNotCount(this.baseTestRequestListableService.getLatestListable(BaseTestJsonRequest.class), count);
	}
}



