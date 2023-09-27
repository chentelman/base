package org.chentelman.base.testing.json;

import java.util.List;

import org.chentelman.base.testing.conveter.BaseConverterService;
import org.chentelman.base.testing.service.BaseListable;

import com.google.gson.JsonElement;

public interface BaseJsonGenerator extends BaseListable {

	/**
	 * This will be used by the {@link BaseConverterService} to register this type
	 *
	 * @return the list of aliases for this type
	 */
	public List<String> getAliases ();

	/**
	 * Generate a json element for the managed type
	 *
	 * @param value the value to convert to a json element
	 * @return the generated json element
	 */
	public JsonElement generate (String value);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default String getName () {
		return getClass().getCanonicalName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default void clear () {
		// do nothing
	}
}



