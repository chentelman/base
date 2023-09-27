package org.chentelman.base.testing.json.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

import org.chentelman.base.testing.json.BaseJsonGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Generate a json element from a random string template
 */
public class BaseJsonRandomStringGenerator implements BaseJsonGenerator {
	private static final String STRING_REGEX = "\\{[^{}]*\\}";

	private Random random;
	private Pattern stringPattern = Pattern.compile(STRING_REGEX, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

	public BaseJsonRandomStringGenerator (Random random) {
		this.random = random;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generate(String value) {
		return new JsonPrimitive(randomString(value));
	}

	public String randomString (int length) {
		return random.ints(48, 123)
			.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			.limit(length)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
	}

	public String randomString (String template) {
		var matcher = stringPattern.matcher(template);

		// find and process all matches
		while (matcher.find()) {
			String block = matcher.group();
			String[] parts = block.substring(1, block.length()-1).split(":");

			if (Objects.equals("string", parts[0])) {
				assertEquals(2, parts.length, "string template block must have two parts");
				int length = Integer.parseInt(parts[1]);
				assertTrue(length > 0, "string template block must define a positive number as the second part");
				template = template.replace(block, randomString(length));
			} else {
				fail("unknown template block for random string: " + parts[0]);
			}
		}

		return template;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAliases() {
		return List.of("random string");
	}
}



