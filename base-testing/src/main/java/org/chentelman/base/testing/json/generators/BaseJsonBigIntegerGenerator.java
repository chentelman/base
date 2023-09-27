package org.chentelman.base.testing.json.generators;

import java.math.BigInteger;
import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Generate a json element from a big integer
 */
public class BaseJsonBigIntegerGenerator implements BaseJsonGenerator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		return new JsonPrimitive(new BigInteger(value));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("num", "number");
	}
}



