package org.chentelman.base.testing.json.generators;

import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

/**
 * Generate a null json element
 */
public class BaseJsonNullGenerator implements BaseJsonGenerator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		return JsonNull.INSTANCE;
	}

	@Override
	public List<String> getAliases() {
		return List.of("null");
	}
}



