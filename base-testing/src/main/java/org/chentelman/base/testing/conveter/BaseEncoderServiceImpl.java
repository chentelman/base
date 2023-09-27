package org.chentelman.base.testing.conveter;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.chentelman.base.testing.service.BaseDiscoveryService;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * default base encoder service implementation
 */
public class BaseEncoderServiceImpl extends BaseDiscoveryService<BaseEncoder> implements BaseEncoderService {

	private Map<String, Function<Object, String>> encoders = new HashMap<> ();
	private Map<String, Function<Object, String>> aliases  = new HashMap<> ();

	public BaseEncoderServiceImpl (ListableBeanFactory listableBeanFactory) {
		super(listableBeanFactory, BaseEncoder.class);
	}

	/**
	 * Register a {@link BaseEncoder} to the class.
	 *
	 * In case a encoder for either the class or the alias is already registered
	 * an error will be generated
	 *
	 * @param encoder the encoder to register
	 */
	@Override
	public void register (BaseEncoder encoder) {
		var type = encoder.getType();

		if (Objects.nonNull(type)) {
			registerEncoder (type.getCanonicalName(), encoder);
		}

		for (String alias : encoder.getAliases()) {
			registerAlias (alias, encoder);
		}
	}

	/**
	 * register the encoder
	 *
	 * @param name the name of the class to register the encoder as
	 * @param encoder the encoder to register
	 */
	private void registerEncoder (String name, BaseEncoder encoder) {
		assertFalse (encoders.containsKey(name), () -> String.format("Encoder %s already registered", encoder));
		encoders.put(name, encoder.getEncoder());
	}

	/**
	 * register the dencoder alias
	 *
	 * @param name the name of the alias to register the encoder as
	 * @param encoder the encoder to register
	 */
	private void registerAlias (String alias, BaseEncoder encoder) {
		assertFalse (aliases.containsKey(alias), () -> String.format("Encoder %s alias %s already registered", encoder, alias));
		aliases.put(alias, encoder.getEncoder());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encode (Object object, Class<?> clazz) {
		return encoders.getOrDefault(clazz.getCanonicalName(), Object::toString).apply(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encode (Object object, String alias) {
		return aliases.getOrDefault(alias, Object::toString).apply(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		// nothing to clear
	}

}



