package org.chentelman.base.testing.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.chentelman.base.testing.service.BaseStorageImpl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.cucumber.datatable.DataTable;

/**
 * A default implementation for the base json service.
 *
 * Utilises the {@link BaseJsonGeratorService} to build the json elements
 */
public class BaseJsonServiceImpl extends BaseStorageImpl<JsonElement> implements BaseJsonService {
	private static final long serialVersionUID = 1L;

	private final BaseJsonGeneratorService baseJsonGeneratorService;

	public BaseJsonServiceImpl (BaseJsonGeneratorService baseJsonGeneratorService) {
		this.baseJsonGeneratorService = baseJsonGeneratorService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement generateJsonElement (String type, String value) {
		return baseJsonGeneratorService.generate(type, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean jsonElementMatches (JsonElement objElem, JsonElement datElem) {

		if (objElem.isJsonPrimitive()) {
			// elements must match
			return objElem.equals(datElem);
		}

		if (objElem.isJsonNull()) {
			// data element must be null too
			return datElem.isJsonNull();
		}

		if (objElem.isJsonObject()) {
			// data element must be object too
			if (!datElem.isJsonObject()) {
				return false;
			}

			// match objects recursively
			return jsonObjectMatches(objElem.getAsJsonObject(), datElem.getAsJsonObject());
		}

		if (objElem.isJsonArray()) {
			// data must be an array too
			if (!datElem.isJsonArray()) {
				return false;
			}

			// we need to match every item int he data list with the object list
			return jsonArrayMatches(objElem.getAsJsonArray(), datElem.getAsJsonArray());
		}

		// no further types are implemented stop with an error if that ever happens
		fail ("object element could not be handled (unimplemented type)");

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonObject generateJsonObject (DataTable data) {
		JsonObject obj = new JsonObject ();

		for (var row : data.asLists()) {
			assertEquals(3, row.size(), "Json row must have exactly three elements");
			obj.add(row.get(0), generateJsonElement(row.get(1), row.get(2)));
		}

		return obj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean jsonObjectMatches (JsonObject object, JsonObject data) {
		// verify the existence of all keys
		for (String key : data.keySet()) {
			if (!object.has(key)) {
				logger.info("object does not contain key {}", key);
				return false;
			}

			// get the json elements to proceed with the rest of the tests
			JsonElement datElem = data.get(key);
			JsonElement objElem = object.get(key);

			if (!jsonElementMatches(objElem, datElem)) {
				logger.info("object's {} ({}) does not match {}", key, objElem, datElem);
				return false;
			}
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonArray generateJsonArray (DataTable template, DataTable data) {
		JsonArray arr = new JsonArray ();

		var maps = template.asMaps();
		assertEquals (1, maps.size(), "object template must have one row");
		var templateObject = maps.get(0);

		for (var row : data.asMaps()) {
			assertEquals (templateObject.size(), row.size(), "object template and data table must be of the same size");
			JsonObject obj = new JsonObject();

			for (var entry : row.entrySet()) {
				String key = entry.getKey();

				assertTrue (templateObject.containsKey(key), "key " + key + " must be contained in template object");

				obj.add(key, generateJsonElement(templateObject.get(key), entry.getValue()));
			}

			arr.add(obj);
		}

		return arr;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonArray generateJsonArray (DataTable data) {
		JsonArray arr = new JsonArray ();

		for (var row : data.asLists()) {
			assertEquals(2, row.size(), "Json array row must have exactly two elements");
			arr.add(generateJsonElement(row.get(0), row.get(1)));
		}

		return arr;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean jsonArrayMatches (JsonArray array, JsonArray data) {
		// iterate the data array and match each item
		for (int i = 0; i < data.size(); i += 1) {
			JsonElement element = data.get(i);

			if (!jsonArrayContainsJsonElement (array, element)) {
				logger.info("array does not contain {}", element);
				return false;
			}
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean jsonArrayContainsJsonElement (JsonArray array, JsonElement element) {

		if (element.isJsonPrimitive() || element.isJsonNull()) {
			// elements must match
			return array.contains(element);
		}

		if (element.isJsonObject()) {
			// array must contain the object
			return jsonArrayContainsJsonObject (array, element.getAsJsonObject());
		}

		if (element.isJsonArray()) {
			// array must contain the array
			return jsonArrayContainsJsonArray (array, element.getAsJsonArray());
		}

		// no further types are implemented stop with an error if that ever happens
		fail ("array element could not be handled (unimplemented type)");

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean jsonArrayContainsJsonObject (JsonArray array, JsonObject data) {
		// iterate the data array and match each object
		for (int i = 0; i < array.size(); i += 1) {
			JsonElement element = array.get(i);

			if (element.isJsonObject() && jsonObjectMatches(element.getAsJsonObject(), data)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean jsonArrayContainsJsonArray (JsonArray array, JsonArray data) {
		// iterate the data array and match each object
		for (int i = 0; i < array.size(); i += 1) {
			JsonElement element = array.get(i);

			if (element.isJsonArray() && jsonArrayMatches(element.getAsJsonArray(), data)) {
				return true;
			}
		}

		return false;
	}
}



