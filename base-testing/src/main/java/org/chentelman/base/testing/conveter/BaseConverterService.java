package org.chentelman.base.testing.conveter;

/**
 * Base converter service interface, the base interface
 * for {@link BaseEncoderService} and {@link BaseDecoderService}
 *
 * Contains the common interface between the two.
 */
public interface BaseConverterService<C extends BaseConverter> {

	/**
	 * register a converter to the service
	 *
	 * @param converter the converter to register
	 */
	public void register (C converter);
}



