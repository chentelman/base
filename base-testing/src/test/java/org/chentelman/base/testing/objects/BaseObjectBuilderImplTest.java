package org.chentelman.base.testing.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.chentelman.base.testing.configuration.BaseTestingDecoderConfiguration;
import org.chentelman.base.testing.conveter.BaseDecoder;
import org.chentelman.base.testing.conveter.BaseDecoderService;
import org.chentelman.base.testing.conveter.BaseDecoderServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * Test for the builder functionality
 */
class BaseObjectBuilderImplTest {
	BaseTestingDecoderConfiguration decoderConfiguration = new BaseTestingDecoderConfiguration();

	/**
	 * a function to generate the builder (unit under test)
	 */
	private TestBuilder testBuilder (BaseDecoder decoder) {
		return new TestBuilder(baseDecoderService(decoder));
	}

	/**
	 * a function to generate a decoder service.
	 * A single decoder will be set to the service to allow
	 * tests to be run either having the required decoder in place or not.
	 */
	private BaseDecoderService baseDecoderService (BaseDecoder decoder) {
		BaseDecoderServiceImpl decoderService = new BaseDecoderServiceImpl (null);

		if (decoder != null)
			decoderService.register(decoder);

		return decoderService;
	}

	/**
	 * the core testing functionality, a new builder will be created with a provided decoder for the test
	 * the builder will be used to instantiate and populate the object which will be returned
	 * for assertions.
	 */
	private TestObject populate (String field, String value, BaseDecoder decoder) {
		// create a new builder and build an emtpy object
		TestBuilder builder = testBuilder (decoder);
		TestObject  object  = builder.build();

		// ensure the object is created
		assertNotNull(object, "object not created");

		// populate the field
		builder.populate(object, field, value);

		// return the populated object
		return object;
	}

	/**
	 * in case the field is not provided an error will be raised
	 */
	@Test
	void testAssignOfInvalidField () {
		// field not in class
		assertThrows(AssertionError.class, () -> populate ("notthere", "data value", null));
	}

	/**
	 * assignment of string fields
	 */
	@Test
	void testStringAssignment () {
		String field = "data";
		String value = "data value";

		// required decoder provided
		TestObject object = populate (field, value, decoderConfiguration.stringDecoder());

		// assert that the field is updated
		assertEquals(value, object.getData(), field + " field not updated");
	}

	/**
	 * assignment with no decoder provided will produce an error
	 */
	@Test
	void testStringAssignmentNoDecoder () {
		// required decoder not provided
		assertThrows(AssertionError.class, () -> populate ("data", "data value", null));
	}

	/**
	 * assignment of a primitive int type
	 */
	@Test
	void testIntAssignment () {
		String field = "id";

		// primitive types dont require decoders
		TestObject object = populate (field, "123", null);

		// assert that the field is updated
		assertEquals(123, object.getId(), field + " field not updated");
	}

	/**
	 * the core testing functionality, a new builder will be created with a provided decoder for the test
	 * the builder will be used to instantiate and populate the object which will be returned
	 * for assertions.
	 */
	private TestObject populate (String field, String value, String type, BaseDecoder decoder) {
		// create a new builder and build an emtpy object
		TestBuilder builder = testBuilder (decoder);
		TestObject  object  = builder.build();

		// ensure the object is created
		assertNotNull(object, "object not created");

		// populate the field
		builder.populate(object, field, value, type);

		// return the populated object
		return object;
	}

	/**
	 * assignment of string fields
	 */
	@Test
	void testStringTypedAssignment () {
		String field = "data";
		String value = "data value";

		// required decoder provided
		TestObject object = populate (field, value, "string", decoderConfiguration.stringDecoder());

		// assert that the field is updated
		assertEquals(value, object.getData(), field + " field not updated");
	}

	/**
	 * the core testing functionality, a new builder will be created with a provided decoder for the test
	 * the builder will be used to check the object.
	 */
	private void check (TestObject object, String field, String value, BaseDecoder decoder) {
		// create a new builder and build an emtpy object
		TestBuilder builder = testBuilder (decoder);

		// populate the field
		builder.check(object, field, value);
	}

	/**
	 * assignment of string fields
	 */
	@Test
	void testStringCheck () {
		TestObject object = new TestObject ();

		object.setData("key");

		// required decoder provided
		check (object, "data", "key", decoderConfiguration.stringDecoder());
	}

	/**
	 * assignment of string fields
	 */
	@Test
	void testPrimitiveCheck () {
		TestObject object = new TestObject ();

		object.setId(345);

		// required decoder provided
		check (object, "id", "345", decoderConfiguration.stringDecoder());
	}

	/**
	 * the core testing functionality, a new builder will be created with a provided decoder for the test
	 * the builder will be used to check the object.
	 */
	private void check (TestObject object, String field, String value, String type, BaseDecoder decoder) {
		// create a new builder and build an emtpy object
		TestBuilder builder = testBuilder (decoder);

		// populate the field
		builder.check(object, field, value, type);
	}

	/**
	 * assignment of string fields
	 */
	@Test
	void testStringTypedCheck () {
		TestObject object = new TestObject ();

		object.setData("zzz");

		// required decoder provided
		check (object, "data", "zzz", "string", decoderConfiguration.stringDecoder());
	}

	/**
	 * assignment of int fields
	 */
	@Test
	void testPrimitiveTypedCheck () {
		TestObject object = new TestObject ();

		object.setId(456);

		// required decoder provided
		check (object, "id", "456", "int", decoderConfiguration.integerDecoder());
	}
}



