package org.chentelman.base.testing.conveter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class BaseDecoderServiceImplTest {

	private BaseDecoderServiceImpl service () {
		return new BaseDecoderServiceImpl(null);
	}

	private BaseDecoder decoder () {
		return new BaseDecoderImpl(
			s -> {
				String[] split = s.split(":", 2);
				TestDecoderObject object = new TestDecoderObject();
				object.setId(Integer.parseInt(split[0]));
				object.setData(split[1]);
				return object;
			},
			TestDecoderObject.class,
			List.of("test")
		);
	}

	@Test
	void registerNewDecoderByClass () {
		BaseDecoderService service = service ();
		BaseDecoder decoder = decoder ();

		service.register(decoder);

		Object object = service.decode("1:value", TestDecoderObject.class);

		assertNotNull (object);
		assertTrue    (TestDecoderObject.class.isInstance(object));

		TestDecoderObject decoded = TestDecoderObject.class.cast(object);

		assertEquals (1, decoded.getId(), "decoder failed");
		assertEquals ("value", decoded.getData(), "decoder failed");
	}

	@Test
	void registerNewDecoderByAlias () {
		BaseDecoderService service = service ();
		BaseDecoder decoder = decoder ();

		service.register(decoder);

		Object object = service.decode("1:value", "test");

		assertNotNull (object);
		assertTrue    (TestDecoderObject.class.isInstance(object));

		TestDecoderObject decoded = TestDecoderObject.class.cast(object);

		assertEquals (1, decoded.getId(), "decoder failed");
		assertEquals ("value", decoded.getData(), "decoder failed");
	}

	@Test
	void registerNewDecoderInstantiate () {
		BaseDecoderService service = service ();
		BaseDecoder decoder = decoder ();

		service.register(decoder);

		TestDecoderObject object = service.instantiate("1:value", TestDecoderObject.class);

		assertNotNull (object);
		assertEquals (1, object.getId(), "decoder failed");
		assertEquals ("value", object.getData(), "decoder failed");
	}

	@Test
	void registerDuplicate () {
		BaseDecoderService service = service ();
		BaseDecoder decoder = decoder ();

		service.register(decoder);

		assertThrows (AssertionError.class, () -> service.register(decoder), "dublicate decoder registered");
	}

	@Test
	void decodeWithNoDecoder () {
		BaseDecoderService service = service ();

		assertThrows (AssertionError.class, () -> service.instantiate("1:value", TestDecoderObject.class));
	}

	@Test
	void decodePrimitiveByte () {
		BaseDecoderService service = service ();
		byte object = service.instantiate("64", byte.class);

		assertEquals  ('@', object);
	}

	@Test
	void decodePrimitiveShort () {
		BaseDecoderService service = service ();
		short object = service.instantiate("12", short.class);

		assertEquals  (12, object);
	}

	@Test
	void decodePrimitiveInt () {
		BaseDecoderService service = service ();
		int object = service.instantiate("1234567890", int.class);

		assertEquals  (1234567890, object);
	}

	@Test
	void decodePrimitiveLong () {
		BaseDecoderService service = service ();
		long object = service.instantiate("1234567890123456789", long.class);

		assertEquals  (1234567890123456789L, object);
	}

	@Test
	void decodePrimitiveFloat () {
		BaseDecoderService service = service ();
		float object = service.instantiate("123.45", float.class);

		assertEquals  (123.45F, object);
	}

	@Test
	void decodePrimitiveDouble () {
		BaseDecoderService service = service ();
		double object = service.instantiate("12345.67", double.class);

		assertEquals  (12345.67, object);
	}

	@Test
	void decodePrimitiveBoolean () {
		BaseDecoderService service = service ();
		boolean object = service.instantiate("true", boolean.class);

		assertEquals  (Boolean.TRUE.booleanValue(), object);
	}

	@Test
	void decodePrimitiveChar () {
		BaseDecoderService service = service ();
		char object = service.instantiate("a", char.class);

		assertEquals ('a', object);
	}
}



