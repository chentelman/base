package org.chentelman.base.testing.json.generators;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;
import org.chentelman.base.testing.json.BaseCompositeKeyService;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;

/**
 * Generate a json element from a composite key service entry
 */
public class BaseJsonCompositeIdGenerator implements BaseJsonGenerator {

	private final BaseCompositeKeyService compositeKeyService;

	public BaseJsonCompositeIdGenerator (BaseCompositeKeyService compositeKeyService) {
		this.compositeKeyService = compositeKeyService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		assertTrue (compositeKeyService.containsKey(value));

		String element = compositeKeyService.get(value);

		if (element == null) {
			return JsonNull.INSTANCE;
		}

		return new JsonPrimitive(element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("compositeId");
	}
}



