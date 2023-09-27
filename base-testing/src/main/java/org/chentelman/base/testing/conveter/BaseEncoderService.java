package org.chentelman.base.testing.conveter;

/**
 * base encoder service, contains a list of all available encoders
 * and provides the methods to directly encode the object to a string value.
 */
public interface BaseEncoderService extends BaseConverterService<BaseEncoder> {

	/**
	 * Encode an object to a string
	 *
	 * @param object the encoded string value to decode
	 * @param clazz the class of the object to convert the string value to
	 * @return the encoded object
	 */
	public String encode (Object object, Class<?> clazz);

	/**
	 * Encode an object to a string
	 *
	 * @param object the encoded string value to decode
	 * @param alias the class alias to use
	 * @return the encoded object
	 */
	public String encode (Object object, String alias);
}



