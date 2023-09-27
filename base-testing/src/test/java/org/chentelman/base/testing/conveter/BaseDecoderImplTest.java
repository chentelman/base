package org.chentelman.base.testing.conveter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

class BaseDecoderImplTest {

	private static final Function<String, Object> converter = s -> s;
	private static final Class<?> clazz = BaseDecoderImplTest.class;
	private static final String   alias = "test_decoder";

	private BaseDecoder decoder () {
		return new BaseDecoderImpl (converter, clazz, List.of(alias));
	}

	@Test
	void verifyDecoderFunction () {
		assertEquals(converter, decoder().getDecoder());
	}

	@Test
	void verifyDecoderClass () {
		assertEquals(clazz, decoder().getType());
	}

	@Test
	void verifyDecoderAlias () {
		List<String> aliases = decoder().getAliases();

		assertEquals(1,     aliases.size());
		assertEquals(alias, aliases.get(0));
	}
}



