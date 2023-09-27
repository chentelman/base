package org.chentelman.base.testing.json.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.List;

import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Generate a json element from a fraction
 */
public class BaseJsonFractionGenerator implements BaseJsonGenerator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		String[] parts = value.split("/");

		assertEquals(2, parts.length, "fraction must have two parts");

		return generate (new BigInteger(parts[0].trim()), new BigInteger(parts[1].trim()));
	}

	/**
	 * Generate a fraction as an object of two elements,
	 * its numerator and denominator
	 *
	 * @param num the numerator
	 * @param den the denominator
	 *
	 * @return a Json Object representing the fraction
	 */
	public JsonObject generate (BigInteger num, BigInteger den) {
		JsonObject fraction = new JsonObject ();

		fraction.add("num", new JsonPrimitive(num));
		fraction.add("den", new JsonPrimitive(den));

		return fraction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("fraction");
	}

}



