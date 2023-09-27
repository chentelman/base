package org.chentelman.base.testing.conveter;

import java.util.List;

import org.chentelman.base.testing.service.BaseListable;

/**
 * Base converter, the base interface for {@link BaseEncoder} and {@link BaseDecoder}
 *
 * Contains the common interface between the two.
 */
public interface BaseConverter extends BaseListable {

	/**
	 * This will be used by the {@link BaseConverterService} to register this type
	 *
	 * @return the class of the type this converter manages
	 */
	public Class<?> getType ();

	/**
	 * This will be used by the {@link BaseConverterService} to register this type
	 *
	 * @return the list of aliases for this type
	 */
	public List<String> getAliases ();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default String getName () {
		return getType().getCanonicalName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default void clear () {
		// do nothing
	}
}



