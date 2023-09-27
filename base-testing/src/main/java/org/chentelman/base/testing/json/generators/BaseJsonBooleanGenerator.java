package org.chentelman.base.testing.json.generators;

import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Generate a json element from a boolean
 */
public class BaseJsonBooleanGenerator implements BaseJsonGenerator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		return new JsonPrimitive(Boolean.parseBoolean(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("bool", "boolean");
	}
}



