package org.chentelman.base.testing.conveter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

class BaseEncoderImplTest {

	private static final Function<Object, String> converter = s -> s.toString();
	private static final Class<?> clazz = BaseEncoderImplTest.class;
	private static final String   alias = "test_encoder";

	private BaseEncoder encoder () {
		return new BaseEncoderImpl (converter, clazz, List.of(alias));
	}

	@Test
	void verifyEncoderFunction () {
		assertEquals(converter, encoder().getEncoder());
	}

	@Test
	void verifyEncoderClass () {
		assertEquals(clazz, encoder().getType());
	}

	@Test
	void verifyEncoderAlias () {
		List<String> aliases = encoder().getAliases();

		assertEquals(1,     aliases.size());
		assertEquals(alias, aliases.get(0));
	}
}



