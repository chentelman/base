package org.chentelman.base.testing.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.chentelman.base.testing.conveter.BaseDecoder;
import org.junit.jupiter.api.Test;

class BaseTestingDecoderConfigurationTest {

	private BaseTestingDecoderConfiguration configuration = new BaseTestingDecoderConfiguration ();

	private void testDecoder (String value, Object object, BaseDecoder decoder) {
		Object decoded = decoder.decode(value);
		String encoded = decoded.toString();

		// verify that the decoder return the same type as its managed type
		assertTrue (decoder.getType().isInstance(decoded));

		// verify the decoder successfully performed the decoding
		assertEquals(object, decoded, "Decoder " + decoder.getName() + " fail");
		assertEquals(value,  encoded, "Decoder " + decoder.getName() + " fail");
	}

	@Test
	void testByteDecoder () {
		byte b = '@';
		testDecoder ("64", b, configuration.byteDecoder());
	}

	@Test
	void testShortDecoder () {
		testDecoder ("12", (short)12, configuration.shortDecoder());
	}

	@Test
	void testIntegerDecoder () {
		testDecoder ("3456789", 3456789, configuration.integerDecoder());
	}

	@Test
	void testLongDecoder () {
		testDecoder ("987654321321", 987654321321L, configuration.longDecoder());
	}

	@Test
	void testFloatDecoder () {
		testDecoder ("12.34", 12.34f, configuration.floatDecoder());
	}

	@Test
	void testDoubleDecoder () {
		testDecoder ("0.12345678912345678", 0.12345678912345678, configuration.doubleDecoder());
	}

	@Test
	void testBooleanTrueDecoder () {
		testDecoder ("true", Boolean.TRUE, configuration.booleanDecoder());
	}

	@Test
	void testBooleanFalseDecoder () {
		testDecoder ("false", Boolean.FALSE, configuration.booleanDecoder());
	}

	@Test
	void testCharacterDecoder () {
		testDecoder ("a", 'a', configuration.charDecoder());
	}

	@Test
	void testNumberDecoder () {
		String value = "12345654321.543212345";
		testDecoder (value, new BigDecimal(value), configuration.numberDecoder());
	}

	@Test
	void testBigIntDecoder () {
		String value = "123456789012345678901234567890";
		testDecoder (value, new BigInteger(value), configuration.bigintDecoder());
	}

	@Test
	void testStringDecoder () {
		String value = "this is a string";
		testDecoder (value, value, configuration.stringDecoder());
	}

	@Test
	void testNullDecoder () {
		assertNull(configuration.nullDecoder().decode(""));
	}

	@Test
	void testNullDecoderWithValue () {
		assertNull(configuration.nullDecoder().decode("null"));
	}

	@Test
	void testNullDecoderFail () {
		BaseDecoder decoder = configuration.nullDecoder();
		assertThrows (AssertionError.class, () -> decoder.decode("a"));
	}

}



