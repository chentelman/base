package org.chentelman.base.testing.json;

import org.chentelman.base.testing.service.BaseListableService;

import com.google.gson.JsonElement;

/**
 * A base listable service specialisation for {@link BaseJsonGenerator} objects
 */
public interface BaseJsonGeneratorService extends BaseListableService<BaseJsonGenerator> {

	/**
	 * Generate a json element using a stored generator
	 *
	 * Generator must be registered before hand.
	 *
	 * @param generator the generator name to use
	 * @param value the value to use for the generator
	 * @return the generated json element
	 */
	public default JsonElement generate (String generator, String value) {
		return getListable(generator).generate(value);
	}
}



