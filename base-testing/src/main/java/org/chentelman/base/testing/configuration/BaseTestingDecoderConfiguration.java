package org.chentelman.base.testing.configuration;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.testing.conveter.BaseDecoder;
import org.chentelman.base.testing.conveter.BaseDecoderImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class BaseTestingDecoderConfiguration extends BaseComponent {

	public static final Function <String, Object> CHAR_DECODER = s -> s.toCharArray()[0];

	@Bean
	public BaseDecoder byteDecoder () {
		return new BaseDecoderImpl (Byte::valueOf, Byte.class, List.of ("byte"));
	}

	@Bean
	public BaseDecoder shortDecoder () {
		return new BaseDecoderImpl (Short::valueOf, Short.class, List.of ("short"));
	}

	@Bean
	public BaseDecoder integerDecoder () {
		return new BaseDecoderImpl (Integer::valueOf, Integer.class, List.of ("int", "integer"));
	}

	@Bean
	public BaseDecoder longDecoder () {
		return new BaseDecoderImpl (Long::valueOf, Long.class, List.of ("long"));
	}

	@Bean
	public BaseDecoder floatDecoder () {
		return new BaseDecoderImpl (Float::valueOf, Float.class, List.of ("float"));
	}

	@Bean
	public BaseDecoder doubleDecoder () {
		return new BaseDecoderImpl (Double::valueOf, Double.class, List.of ("double"));
	}

	@Bean
	public BaseDecoder booleanDecoder () {
		return new BaseDecoderImpl (Boolean::valueOf, Boolean.class, List.of ("bool", "boolean"));
	}

	@Bean
	public BaseDecoder charDecoder () {
		return new BaseDecoderImpl (CHAR_DECODER, Character.class, List.of ("char", "character"));
	}

	@Bean
	public BaseDecoder numberDecoder () {
		return new BaseDecoderImpl (BigDecimal::new, BigDecimal.class, List.of ("num", "number"));
	}

	@Bean
	public BaseDecoder bigintDecoder () {
		return new BaseDecoderImpl (s -> new BigInteger(s, 10), BigInteger.class, List.of ("bigint", "big int"));
	}

	@Bean
	public BaseDecoder stringDecoder () {
		return new BaseDecoderImpl (s -> s, String.class, List.of ("string"));
	}

	@Bean
	public BaseDecoder nullDecoder () {
		return new BaseDecoder () {

			@Override
			public String getName () {
				return "null";
			}

			@Override
			public Class<?> getType() {
				return null;
			}

			@Override
			public List<String> getAliases() {
				return List.of("null");
			}

			@Override
			public Function<String, Object> getDecoder() {
				return s -> Objects.isNull(s) || s.isEmpty() || s.equals("null")
					? null
					: fail (String.format("cannot convert to null for value: %s", s));
			}

		};
	}
}



