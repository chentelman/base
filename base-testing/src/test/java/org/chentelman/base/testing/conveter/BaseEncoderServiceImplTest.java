package org.chentelman.base.testing.conveter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

class BaseEncoderServiceImplTest {

	private BaseEncoderServiceImpl service () {
		return new BaseEncoderServiceImpl(null);
	}

	private BaseEncoder encoder () {
		return new BaseEncoderImpl(
			o -> {
				TestEncoderObject object = TestEncoderObject.class.cast(o);
				return String.format("%d:%s", object.getId(), object.getData());
			},
			TestEncoderObject.class,
			List.of("test")
		);
	}

	@Test
	void registerNewEncoderByClass () {
		BaseEncoderService service = service ();
		BaseEncoder encoder = encoder ();

		service.register(encoder);

		String encoded = service.encode(new TestEncoderObject(1, "value1"), TestEncoderObject.class);

		assertNotNull (encoded);
		assertEquals ("1:value1", encoded, "encoder failed");
	}

	@Test
	void registerNewEncoderByAlias () {
		BaseEncoderService service = service ();
		BaseEncoder encoder = encoder ();

		service.register(encoder);

		String encoded = service.encode(new TestEncoderObject(2, "value2"), "test");

		assertNotNull (encoded);
		assertEquals ("2:value2", encoded, "encoder failed");
	}

	@Test
	void registerDuplicate () {
		BaseEncoderService service = service ();
		BaseEncoder encoder = encoder ();

		service.register(encoder);

		assertThrows (AssertionError.class, () -> service.register(encoder), "dublicate encoder registered");
	}

	@Test
	void decodeWithNoEncoder () {
		BaseEncoderService service = service ();

		// encodes using toString when no encoder is found
		String encoded = service.encode(new TestEncoderObject(3, "value3"), "test");

		assertNotNull (encoded);
		assertEquals ("TestEncoderObject(id=3, data=value3)", encoded, "encoder failed");
	}
}



