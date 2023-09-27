package org.chentelman.base.testing.json.generators;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.testresults.BaseTestJsonRequest;
import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;

/**
 * Generate a json element from json request latest object
 */
public class BaseJsonLatestObjectGenerator implements BaseJsonGenerator {
	private BaseTestRequestListableService baseTestRequestListableService;

	public BaseJsonLatestObjectGenerator (BaseTestRequestListableService baseTestRequestListableService) {
		this.baseTestRequestListableService = baseTestRequestListableService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		BaseTestJsonRequest request = baseTestRequestListableService.getListable(value, BaseTestJsonRequest.class);
		assertNotNull(request, "request tester for " + value + " was not found");

		JsonElement element = request.getLatestObj();
		assertNotNull(element, "object for " + value + " was not found");

		return element;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("obj", "object");
	}
}



