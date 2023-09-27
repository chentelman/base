package org.chentelman.base.testing.conveter;

/**
 * base decoder service, contains a list of all available decoders
 * and provides the methods to directly decode and instantiate the managed object.
 */
public interface BaseDecoderService extends BaseConverterService<BaseDecoder> {

	/**
	 * Decode a string value to an object for the provided class.
	 *
	 * @param value the encoded string value to decode
	 * @param clazz the class of the object to convert the string value to
	 * @return the decoded object
	 */
	public Object decode (String value, Class<?> clazz);

	/**
	 * Decode a string value to an object for a provided alias.
	 *
	 * The alias must be registered in advance.
	 *
	 * @param value the encoded string value to decode
	 * @param alias the class alias to use
	 * @return the decoded object
	 */
	public Object decode (String value, String alias);

	/**
	 * Decode a string value to an object for the provided alias.
	 *
	 * The alias must be registered in advance.
	 *
	 * @param value the encoded string value to decode
	 * @param alias the class alias to use
	 * @return the decoded object
	 */
	public <T> T instantiate (String value, Class<T> clazz);
}



