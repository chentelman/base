package org.chentelman.base.testing.conveter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.chentelman.base.testing.configuration.BaseTestingDecoderConfiguration;
import org.chentelman.base.testing.service.BaseDiscoveryService;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * default base decoder service implementation
 */
public class BaseDecoderServiceImpl extends BaseDiscoveryService<BaseDecoder> implements BaseDecoderService {

	private Map<String, Function<String, Object>> decoders = new HashMap<> ();
	private Map<String, Function<String, Object>> aliases  = new HashMap<> ();

	public BaseDecoderServiceImpl (ListableBeanFactory listableBeanFactory) {
		super(listableBeanFactory, BaseDecoder.class);

		// register primitive type decoders
		decoders.put("byte",    Byte    :: parseByte);
		decoders.put("short",   Short   :: parseShort);
		decoders.put("int",     Integer :: parseInt);
		decoders.put("long",    Long    :: parseLong);
		decoders.put("float",   Float   :: parseFloat);
		decoders.put("double",  Double  :: parseDouble);
		decoders.put("boolean", Boolean :: parseBoolean);
		decoders.put("char",    BaseTestingDecoderConfiguration.CHAR_DECODER);
	}

	/**
	 * Register a {@link BaseDecoder} to the class.
	 *
	 * In case a decoder for either the class or the alias is already registered
	 * an error will be generated
	 *
	 * @param decoder the decoder to register
	 */
	@Override
	public void register (BaseDecoder decoder) {
		var type = decoder.getType();

		if (Objects.nonNull(type)) {
			registerDecoder(type.getCanonicalName(), decoder);
		}

		for (String alias : decoder.getAliases()) {
			registerAlias (alias, decoder);
		}
	}

	/**
	 * register the decoder
	 *
	 * @param name the name of the class to register the decoder as
	 * @param decoder the decoder to register
	 */
	private void registerDecoder (String name, BaseDecoder decoder) {
		assertFalse (decoders.containsKey(name), () -> String.format("Decoder %s already registered", decoder));
		decoders.put(name, decoder.getDecoder());
	}

	/**
	 * register the decoder alias
	 *
	 * @param name the name of the alias to register the decoder as
	 * @param decoder the decoder to register
	 */
	private void registerAlias (String alias, BaseDecoder decoder) {
		assertFalse (aliases.containsKey(alias), () -> String.format("Decoder %s alias %s already registered", decoder, alias));
		aliases.put(alias, decoder.getDecoder());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decode (String value, Class<?> clazz) {
		String key = clazz.getCanonicalName();

		assertTrue (decoders.containsKey(key), () -> String.format("Decoder for %s not registered", key));

		return decoders.get(key).apply(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decode (String value, String alias) {
		assertTrue (aliases.containsKey(alias), () -> String.format("Decoder for alias %s not registered", alias));

		return aliases.get(alias).apply(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T instantiate (String value, Class<T> clazz) {
		if (clazz.isPrimitive()) {
			return (T)(decode(value, clazz));
		}
		return clazz.cast(decode(value, clazz));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		// nothing to clear
	}

}



