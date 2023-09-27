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
public class BaseEncoderImpl extends BaseConverterImpl implements BaseEncoder {

	private final Function<Object, String> encoder;

	public BaseEncoderImpl (Function<Object, String> encoder, Class<?> type, List<String> aliases) {
		super(type, aliases);

		this.encoder = encoder;
	}
}



