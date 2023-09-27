package org.chentelman.base.testing.json.generators;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;
import org.chentelman.base.testing.json.BaseJsonService;

import com.google.gson.JsonElement;

/**
 * Generate a json element from a json service entry
 */
public class BaseJsonJsonGenerator implements BaseJsonGenerator {
	private BaseJsonService jsonService;

	public BaseJsonJsonGenerator (BaseJsonService jsonService) {
		this.jsonService = jsonService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		assertTrue(jsonService.containsKey(value), "json " + value + " not found");
		return jsonService.get(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("json");
	}
}



