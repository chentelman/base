package org.chentelman.base.testing.objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import io.cucumber.datatable.DataTable;

/**
 * Base interface for object builders.
 *
 * The builder is able to instantiate the object via the build method
 * as well as populate its field using the {@link DataTable} class
 *
 * Allowed DataTable format
 *
 * Format 1: update object based on their field name
 *
 * | field | value  |
 * | name1 | value1 |
 * | name2 | value2 |
 *
 * Format 2: update object based on their field name for a specific type
 *
 * | field |   type | value  |
 * | name1 |    int | value1 |
 * | name2 |   long | value2 |
 * | name3 | string | value3 |
 */
public interface BaseObjectBuilder {
	public static final String FIELD_NAME = "field";
	public static final String VALUE_NAME = "value";
	public static final String FTYPE_NAME = "type";

	public static final String MISSING_FIELD_COLUMN = "Column " + FIELD_NAME + " is missing from data table";
	public static final String MISSING_VALUE_COLUMN = "Column " + FIELD_NAME + " is missing from data table";

	/**
	 * Build an empty instance of the managed object
	 *
	 * @return an instance of the managed object
	 */
	public Object build ();

	/**
	 * Build an instance of the managed object as described by the data table.
	 *
	 * @return an instance of the managed object
	 */
	public default Object build (DataTable data) {
		Object object = build();

		populate (object, data);

		return object;
	}

	/**
	 * Populate an instance of the managed object as described by the data table.
	 */
	public default void populate (Object object, DataTable data) {
		for (var row : data.asMaps()) {
			populate (object, row);
		}
	}

	/**
	 * Populate a field of the managed object as described by a single row of the data table.
	 */
	public default void populate (Object object, Map<String, String> data) {
		String field = data.get(FIELD_NAME);
		String ftype = data.get(FTYPE_NAME);
		String value = data.get(VALUE_NAME);

		assertNotNull (field, MISSING_FIELD_COLUMN);
		assertNotNull (value, MISSING_VALUE_COLUMN);

		if (ftype == null || ftype.length() == 0) {
			populate (object, field, value);
		} else {
			populate (object, field, value, ftype);
		}
	}

	/**
	 * Populate a field of the managed object as described by the field name and its value as string.
	 */
	public void populate (Object object, String field, String value);

	/**
	 * Populate a field of the managed object as described by the field name, its type and its value as string.
	 */
	public void populate (Object object, String field, String value, String type);

	/**
	 * Check an instance of the managed object against its description by the data table.
	 */
	public default boolean check (Object object, DataTable data) {
		for (var row : data.asMaps()) {
			if (!check (object, row)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Check an instance of the managed object against its description by a single row of the data table.
	 */
	public default boolean check (Object object, Map<String, String> data) {
		String field = data.get(FIELD_NAME);
		String ftype = data.get(FTYPE_NAME);
		String value = data.get(VALUE_NAME);

		assertNotNull (field, MISSING_FIELD_COLUMN);
		assertNotNull (value, MISSING_VALUE_COLUMN);

		if (ftype == null || ftype.length() == 0) {
			return check (object, field, value);
		} else {
			return check (object, field, value, ftype);
		}
	}

	/**
	 * Check a field of a managed object against its description by its field name and its value as string.
	 */
	public boolean check (Object object, String field, String value);

	/**
	 * Check a field of a managed object against its description by its field name, its type and its value as string.
	 */
	public boolean check (Object object, String field, String value, String type);
}



