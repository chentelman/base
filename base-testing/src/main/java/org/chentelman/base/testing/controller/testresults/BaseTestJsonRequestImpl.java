package org.chentelman.base.testing.controller.testresults;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.testing.controller.webclient.BaseResponseResults;
import org.springframework.http.HttpStatus;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Getter;

@Getter
public abstract class BaseTestJsonRequestImpl extends BaseTestRequestImpl implements BaseTestJsonRequest {
	private JsonElement latestKey;
	private JsonObject  latestObj;
	private JsonArray   latestArr;

	/**
	 * Internal method to setup the results internally
	 */
	@Override
	protected void results (BaseResponseResults results, HttpStatus expectedStatus, boolean expectingBody, boolean multipleResults) {
		super.results (results, expectedStatus, expectingBody, multipleResults);

		latestObj = null;
		latestArr = null;

		JsonElement element;

		if (results.getBody() != null) {
			element = JsonParser.parseString(results.getBody());
		} else {
			element = JsonNull.INSTANCE;
		}

		if (success && expectingBody && multipleResults) {
			assertTrue (element.isJsonArray(), "expecting json array as result");
			latestArr = element.getAsJsonArray();
		}

		if (!success || (expectingBody && !multipleResults)) {
			assertTrue (element.isJsonObject(), "expecting json object as result");
			latestObj = element.getAsJsonObject();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createResults (BaseResponseResults results) {
		super.createResults (results);

		if (success && latestObj.has(getKeyName())) {
			latestKey = latestObj.get(getKeyName());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear () {
		super.clear();

		latestKey = null;
		latestObj = null;
		latestArr = null;
	}

}



