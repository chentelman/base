package org.chentelman.base.testing.json.generators;

import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Generate a json element from a long value
 */
public class BaseJsonLongGenerator implements BaseJsonGenerator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		return new JsonPrimitive(Long.parseLong(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("long");
	}

}



