package org.chentelman.base.testing.controller.testresults;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface BaseTestJsonRequest extends BaseTestRequest {
	public JsonElement getLatestKey();
	public JsonArray   getLatestArr();
	public JsonObject  getLatestObj();

	@Override
	public default String getLatestId () {
		JsonElement key = getLatestKey();

		assertNotNull (key, () -> String.format("Latest %s id does not exist", getName()));

		return key.getAsString();
	}
}



