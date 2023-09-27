package org.chentelman.base.testing.conveter;

import java.util.List;
import java.util.function.Function;

import lombok.Getter;

/**
 * Default implementation for the {@link BaseDecoder}.
 *
 * Extends the {@link BaseConverterImpl} to additionally provide the decoder.
 */
@Getter
public class BaseDecoderImpl extends BaseConverterImpl implements BaseDecoder {

	private final Function<String, Object> decoder;

	public BaseDecoderImpl(Function<String, Object> decoder, Class<?> type, List<String> aliases) {
		super(type, aliases);

		this.decoder = decoder;
	}
}



