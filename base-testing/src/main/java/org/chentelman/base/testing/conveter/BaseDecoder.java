package org.chentelman.base.testing.conveter;

import java.util.function.Function;

/**
 * The base decoder interface.
 *
 * It provides the functions to decode as well as get a decoder,
 * from a String to the managed object.
 */
public interface BaseDecoder extends BaseConverter {

	/**
	 * Return a decoder for this type
	 *
	 * @return a {@link Function} interface to convert a string to its type
	 */
	public Function<String, Object> getDecoder ();

	/**
	 * decode a string to its type
	 *
	 * @param code the encoded value of the class
	 * @return the class decoded from the string
	 */
	public default Object decode (String code) {
		return getDecoder().apply(code);
	}

}



