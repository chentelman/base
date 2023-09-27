package org.chentelman.base.testing.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.chentelman.base.testing.configuration.BaseTestingDecoderConfiguration;
import org.chentelman.base.testing.conveter.BaseDecoderService;
import org.chentelman.base.testing.conveter.BaseDecoderServiceImpl;
import org.chentelman.base.testing.utilities.BaseTestUtilities;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.cucumber.datatable.DataTable;

/**
 * Test for the object service functionality
 */
@TestInstance(Lifecycle.PER_CLASS)
class BaseObjectServiceImplTest {

	private BaseDecoderService decoder;
	private BaseObjectService service;

	@BeforeAll
	void initialize () {
		BaseTestingDecoderConfiguration configuration = new BaseTestingDecoderConfiguration();

		decoder = new BaseDecoderServiceImpl(null);
		decoder.register(configuration.integerDecoder());
		decoder.register(configuration.stringDecoder());
	}

	@BeforeEach
	void start () {
		service = new BaseObjectServiceImpl (decoder);
	}

	@AfterEach
	void end () {
		service = null;
	}

	@AfterAll
	void clear () {
		decoder = null;
	}

	@Test
	void testAutomaticallyCreateBuilder () {
		String key = TestObject.class.getCanonicalName();

		// check that the builder does not exist already
		assertThrows(AssertionError.class, () -> service.getBuilder(key));

		// check that the builder will be created automatically
		assertNotNull(service.getBuilder(TestObject.class));

		// check again, the builder should now be there
		assertNotNull(service.getBuilder(key));
	}

	@Test
	void testTypeBuilder () {
		service.setBuilder(TestObject.class, new TestBuilder(decoder));
		Object value = service.build("org.chentelman.base.testing.objects.TestObject");

		assertTrue (value instanceof TestObject, "object is not the correct class");
	}

	@Test
	void testClassBuilder () {
		TestObject object = service.build(TestObject.class);

		assertEquals(0, object.getId(), "test object does not have the default value for id");
		assertNull(object.getData(), "test object does not have the default value for data");
	}

	@Test
	void testDecodeWithType () {
		Object value = service.decode("12.3", "string");

		assertEquals ("12.3", value);
	}

	@Test
	void testDecodeWithClass () {
		double value = service.decode("12.3", double.class);

		assertEquals (12.3, value);
	}

	private void testClassBuilderWithData (DataTable table, int id, String data) {
		TestObject object = service.build(table, TestObject.class);

		assertEquals(  id, object.getId(),   "test object does not have the provided value for id");
		assertEquals(data, object.getData(), "test object does not have the provided value for data");
	}

	@Test
	void testClassBuilderWithData () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field", "value"),
			List.of(   "id",     "5"),
			List.of( "data",  "five")
		));

		testClassBuilderWithData (data, 5, "five");
	}

	@Test
	void testClassBuilderWithData_reversed () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("value", "field"),
			List.of(    "3",    "id"),
			List.of("three",  "data")
		));

		testClassBuilderWithData (data, 3, "three");
	}

	@Test
	void testClassBuilderWithDataTyped () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",   "type", "value"),
			List.of(   "id",    "int",     "7"),
			List.of( "data", "string", "seven")
		));

		testClassBuilderWithData (data, 7, "seven");
	}

	private void testClassPopulation (DataTable table, int id, String data) {
		TestObject object = new TestObject();

		service.populate(object, table, TestObject.class);

		assertEquals(  id, object.getId(),   "test object has not been populated with the correct value for id");
		assertEquals(data, object.getData(), "test object has not been populated with the correct value for data");
	}

	@Test
	void testClassPopulation () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field", "value"),
			List.of(   "id",     "8"),
			List.of( "data", "eight")
		));

		testClassPopulation (data, 8, "eight");
	}

	@Test
	void testClassPopulation_reversed () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("value", "field"),
			List.of(    "6",    "id"),
			List.of(  "six",  "data")
		));

		testClassPopulation (data, 6, "six");
	}

	@Test
	void testClassPopulationTyped () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",   "type", "value"),
			List.of(   "id",    "int",     "4"),
			List.of( "data", "string",  "four")
		));

		testClassPopulation (data, 4, "four");
	}

	private void store (DataTable table, int id, String data) {
		String key = "the key";

		// verify nothing exists already for the key
		assertNull (service.get(key));

		// store the new object
		service.store(key, table, TestObject.class);

		Object object = service.get(key);

		// verify the object is stored
		assertTrue (object instanceof TestObject, "stored object is not of the right class");
		assertEquals(  id, ((TestObject)object).getId(),   "test object has not been stored with the correct value for id");
		assertEquals(data, ((TestObject)object).getData(), "test object has not been stored with the correct value for data");
	}

	@Test
	void testStore () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",  "value"),
			List.of(   "id",     "11"),
			List.of( "data", "eleven")
		));

		store (data, 11, "eleven");
	}

	@Test
	void testStoreTyped () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",   "type",  "value"),
			List.of(   "id",    "int",     "12"),
			List.of( "data", "string", "twelve")
		));

		store (data, 12, "twelve");
	}

	private void update (DataTable table, int id, String data) {
		String key = "the key";

		// populate the storage with an empty object
		service.put(key, new TestObject());

		// update the object
		service.update(key, table, TestObject.class);

		Object object = service.get(key);

		// verify the object is stored
		assertTrue (object instanceof TestObject, "updated object is not of the right class");
		assertEquals(  id, ((TestObject)object).getId(),   "test object has not been updated with the correct value for id");
		assertEquals(data, ((TestObject)object).getData(), "test object has not been updated with the correct value for data");
	}

	@Test
	void testUpdate () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",  "value"),
			List.of(   "id",     "20"),
			List.of( "data", "twenty")
		));

		update (data, 20, "twenty");
	}

	@Test
	void testUpdateTyped () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",   "type",  "value"),
			List.of(   "id",    "int",     "90"),
			List.of( "data", "string", "ninety")
		));

		update (data, 90, "ninety");
	}

	private void check (DataTable table, int id, String data, boolean status) {
		String key = "the key";

		// populate the storage with the object
		TestObject object = new TestObject ();

		object.setId(id);
		object.setData(data);

		service.put(key, object);

		// update the object
		assertEquals(status, service.check(key, table, TestObject.class));
	}

	@Test
	void testCheck () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",  "value"),
			List.of(   "id",     "30"),
			List.of( "data", "thirty")
		));

		check (data, 30, "thirty", true);
	}

	@Test
	void testCheckTyped () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",   "type", "value"),
			List.of(   "id",    "int",    "60"),
			List.of( "data", "string", "sixty")
		));

		check (data, 60, "sixty", true);
	}

	@Test
	void testCheckAgainst () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field", "value"),
			List.of(   "id",    "40"),
			List.of( "data", "forty")
		));

		check (data, 50, "fifty", false);
	}

	@Test
	void testCheckAgainstTyped () {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field",   "type", "value"),
			List.of(   "id",    "int",    "50"),
			List.of( "data", "string", "fifty")
		));

		check (data, 40, "forty", false);
	}
}



