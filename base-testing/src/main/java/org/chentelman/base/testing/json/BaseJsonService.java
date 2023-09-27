package org.chentelman.base.testing.json;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.chentelman.base.testing.service.BaseStorage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.cucumber.datatable.DataTable;

/**
 * A base storage implementation for json objects.
 *
 * Additionally provide operations to create different types
 * of json elements and verify the contents of the storage.
 */
public interface BaseJsonService extends BaseStorage<JsonElement> {

	/**
	 * Retrieve a json element casted to one of its subtypes.
	 *
	 * @param <T> the subtype of Json Element to retrieve the item as
	 * @param name the name the json Element is registered under
	 * @param type the type of the json element to retrieve as
	 * @return the Json element
	 */
	public default <T extends JsonElement> T get (String name, Class<T> type) {
		assertTrue (containsKey(name), () -> String.format("Json with name %s could not be found", name));

		JsonElement element = get(name);

		assertTrue (type.isInstance(element), () -> String.format("Json with name %s not of type %s", name, type.getCanonicalName()));

		return type.cast(element);
	}

	/* ============ *
	 * json element *
	 * ============ */

	/**
	 * Generate a Json Element given a type and a string value.
	 *
	 * A custom generator for that type will be used to generate the json element.
	 *
	 * @param type the type of the json element, used as a reference to the related generator to be used
	 * @param value the value to be passed ot the generator to create the json element
	 * @return the generated json element
	 */
	public JsonElement generateJsonElement (String type, String value);

	/**
	 * TODO
	 * @param objElem
	 * @param datElem
	 * @return
	 */
	public boolean jsonElementMatches (JsonElement objElem, JsonElement datElem);

	/* =========== *
	 * json object *
	 * =========== */

	/**
	 * Generate a Json Object given a data table
	 * An example of the table format can be found below
	 *
	 *      ┌──────────────────── The name of the object field
	 *      │      ┌───────────── The type of the field
	 *      │      │        ┌──── The value to convert
	 *      │      │        │
	 *      *      *        *
	 *   | code |    int |   1 |
	 *   | name | string | one |
	 *
	 * @param data the data table with the informationt o build the object
	 * @return the generated json object
	 */
	public JsonObject generateJsonObject (DataTable data);

	/**
	 * Generate a json object given a data table and store it in the storage
	 *
	 * @param name the name to store the json object under
	 * @param data the table to build the json object
	 * @return the previously stored entry in the storage
	 */
	public default JsonElement putJsonObject (String name, DataTable data) {
		return put (name, generateJsonObject (data));
	}

	/**
	 * Check if the given json object matches the provided data table
	 *
	 * @param object the object to check
	 * @param data the data to check against
	 * @return true if the object matches the data table, or false otherwise
	 */
	public default boolean jsonObjectMatches (JsonObject object, DataTable data) {
		return jsonObjectMatches (object, generateJsonObject (data));
	}

	/**
	 * Check if the given json object matches the second json object.
	 *
	 * This operation is Noncommutative as the operation will only ensure
	 * that the second object is a subset of the first one. The following
	 * operation is true. However the opposite is false.
	 *
	 * Object1           Object2
	 * {
	 *   "id": 1,        {
	 *   "code": "1",      "code": "1",
	 *   "name": "one"     "name": "one"
	 * }                 }
	 *
	 * @param object the object to check
	 * @param data the object to check against
	 * @return true if the object matches the data table, or false otherwise
	 */
	public boolean jsonObjectMatches (JsonObject object, JsonObject data);

	/* ========== *
	 * json array *
	 * ========== */

	/**
	 * Generate a json array, two separate tables are required to build the array.
	 * One to define the template of each object and the second to provide
	 * the data for each object in the array
	 *
	 * Template format
	 *
	 *   |   code |    name | *───── The name of the object field
	 *   | string | integer | *───── The type of the field
	 *
	 * Data format
	 *
	 *   | code | name | *───── The name of the object field
	 *   |    1 |  one | *───── The data for the first object
	 *   |    2 |  two | *───── The data for the second object
	 *   |    4 | four | *───── The data for the third object
	 *
	 * @param template the data table defining the object structure
	 * @param data the data table defining the data of the objects
	 * @return the generated json array
	 */
	public JsonArray generateJsonArray (DataTable template, DataTable data);

	/**
	 * Generate a simple list of items base on the provided table data
	 * An example of the table format can be found below
	 *
	 *      ┌─────────────── The type of the field
	 *      │        ┌────── The value to convert
	 *      │        │
	 *      *        *
	 *   |    int |   1 |
	 *   | string | one |
	 *
	 * @param data the data table to build the array on
	 * @return the generated json array
	 */
	public JsonArray generateJsonArray (DataTable data);

	/**
	 * Generate a json array given a data table and a template and store it in the storage
	 *
	 * @param name the name to store the json array under
	 * @param template the table describing the structure of the object
	 * @param data the table to build the json objects for the array
	 * @return the previously stored entry in the storage
	 */
	public default JsonElement putJsonArray (String name, DataTable template, DataTable data) {
		return put (name, generateJsonArray (template, data));
	}

	/**
	 * Generate a simple list of items base on the provided table data and store it in the storage
	 *
	 * @param name the name to store the json array under
	 * @param data the data table to build the array on
	 * @return the generated json array
	 */
	public default JsonElement putJsonArray (String name, DataTable data) {
		return put (name, generateJsonArray (data));
	}

	/**
	 * Check if the given json array matches the second json array.
	 *
	 * This operation is Noncommutative as the operation will only ensure
	 * that the second array is a subset of the first one. The following
	 * operation is true. However the opposite is false.
	 *
	 * Array1     Array2
	 * [1, 2, 3]  [1, 3]
	 *
	 * @param array the array to check
	 * @param data the data to check against
	 * @return true if the array matches the data table, or false otherwise
	 */
	public boolean jsonArrayMatches (JsonArray array, JsonArray data);

	/**
	 * TODO
	 * @param array
	 * @param element
	 * @return
	 */
	public boolean jsonArrayContainsJsonElement (JsonArray array, JsonElement element);

	/**
	 * TODO
	 */
	public default boolean jsonArrayContainsJsonObject (JsonArray array, String key, String type, String value) {
		JsonObject obj = new JsonObject ();

		obj.add(key, generateJsonElement(type, value));

		return jsonArrayContainsJsonObject (array, obj);
	}

	/**
	 * TODO
	 */
	public default boolean jsonArrayContainsJsonObject (JsonArray array, DataTable data) {
		return jsonArrayContainsJsonObject (array, generateJsonObject(data));
	}

	/**
	 * Check if the provided json array contains the json object
	 *
	 * @param array the json array to check
	 * @param data the json object to check against
	 * @return return true if any of the elements in the json array match the json object.
	 */
	public boolean jsonArrayContainsJsonObject (JsonArray array, JsonObject data);

	/**
	 * Check if the provided json array contains the json array
	 *
	 * @param array the json array to check
	 * @param data the json array to check against
	 * @return return true if any of the elements in the json array match the json array.
	 */
	public boolean jsonArrayContainsJsonArray (JsonArray array, JsonArray data);

	/**
	 * TODO
	 */
	public default boolean jsonArrayContainsJsonObjectWithElement (JsonArray array, String key, JsonElement element) {
		JsonObject obj = new JsonObject();

		obj.add(key, element);

		return jsonArrayContainsJsonObject(array, obj);
	}
}



