package org.chentelman.base.testing.json.generators;

import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Generate a json element from an integer
 */
public class BaseJsonIntegerGenerator implements BaseJsonGenerator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		return new JsonPrimitive(Integer.parseInt(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("int", "integer");
	}

}



