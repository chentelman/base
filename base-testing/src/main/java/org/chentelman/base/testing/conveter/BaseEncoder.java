package org.chentelman.base.testing.conveter;

import java.util.function.Function;

/**
 * The base encoder interface.
 *
 * It provides the functions to encode as well as get a encoder,
 * from a String to the managed object.
 */
public interface BaseEncoder extends BaseConverter {

	/**
	 * Return an encoder for this type
	 *
	 * @return a {@link Function} interface to convert the type to string
	 */
	public Function<Object, String> getEncoder ();

	/**
	 * encode type to a string
	 *
	 * @param type the class to encode
	 * @return the class encoded to string
	 */
	public default String encode (Object type) {
		return getEncoder().apply(type);
	}

}



