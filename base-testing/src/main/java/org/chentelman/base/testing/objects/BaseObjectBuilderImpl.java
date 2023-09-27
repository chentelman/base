package org.chentelman.base.testing.objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.testing.conveter.BaseDecoderService;
import org.chentelman.base.testing.exception.BaseTestException;

/**
 * Default implementation of the base object builder interface,
 * for a generic class.
 *
 * Implementation is heavily dependent on reflection to access and assign values
 * to the managed class.
 *
 * @param <E> the type of the class
 */
public class BaseObjectBuilderImpl<E> extends BaseComponent implements BaseObjectBuilder {

	private Class<E> objectType;
	private BaseDecoderService decoderService;
	private Map<String, Field> fields = new HashMap<> ();

	/**
	 * Upon instantiation base object builder implementation,
	 * will scan the object and all of its super classes to generate
	 * a list of all fields available for the class.
	 *
	 * This list is stored in a hashmap to allow them to be used to update
	 * specific instances of objects.
	 *
	 * @param objectType the type that is managed by the class
	 */
	public BaseObjectBuilderImpl (Class<E> objectType, BaseDecoderService decoderService) {
		this.objectType     = objectType;
		this.decoderService = decoderService;

		buildFields (objectType);
	}

	/**
	 * In order for the builder to be able to not only identify
	 * the public field but also any fields normally hidden,
	 * the scan will have to go through the declared fields method.
	 *
	 * As this only going to return the fields declared in the class
	 * and not all of its super classes, we have to iteratively go through
	 * all of the available super classes to identify the fields.
	 *
	 * Processing of the declared fields is delegated to a different method.
	 *
	 * @param objectType the type that is managed by the class
	 */
	private void buildFields (Class<?> objectType) {
		while (objectType != null) {
			buildFields (objectType.getDeclaredFields());
			objectType = objectType.getSuperclass();
		}
	}

	/**
	 * The class and each of its super classes will produce a different field list.
	 * To process those we will have to simply add them to the fields has map
	 * and set the fields to be accessible.
	 *
	 * The method will also attempt to work around duplicates.
	 * As the order of the processing is performed from the base class
	 * towards its immediate super class if a duplicate name is encountered
	 * the later will be generally hidden by the base class and therefore
	 * an override will have to be avoided
	 *
	 * @param list the list of fields to process
	 */
	private void buildFields (Field[] list) {
		for (Field field : list) {
			if(!fields.containsKey(field.getName()) && field.trySetAccessible()) {
				fields.put(field.getName(), field);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E build () {
		try {
			return objectType.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			fail("unable to build " + objectType.getCanonicalName());
		}
		throw new BaseTestException("should never happen");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void populate (Object object, String field, String value) {
		assertTrue(fields.containsKey(field), unknownField(field));

		try {
			Field setter = fields.get(field);
			Object toset = decoderService.decode(value, setter.getType());

			logger.trace("populate field {} with {}", field, toset);
			setter.set(object, toset);
		} catch (Exception e) {
			fail ("Failed to update field " + field + " : " + e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void populate (Object object, String field, String value, String type) {
		assertTrue(fields.containsKey(field), unknownField(field));

		try {
			Field setter = fields.get(field);
			Object toset = decoderService.decode(value, type);

			logger.trace("populate field {} with {}", field, toset);
			setter.set(object, toset);
		} catch (Exception e) {
			fail ("Failed to update field " + field + " : " + e.getMessage());
		}
	}

	@Override
	public boolean check (Object object, String field, String value) {
		assertTrue(fields.containsKey(field), unknownField(field));

		try {
			Field getter = fields.get(field);

			Object actual = getter.get(object);
			Object expected = decoderService.decode(value, getter.getType());

			logger.debug("checking field {} against {}", field, expected);
			return Objects.equals(expected, actual);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean check (Object object, String field, String value, String type) {
		assertTrue(fields.containsKey(field), unknownField(field));

		try {
			Field getter = fields.get(field);

			Object actual = getter.get(object);
			Object expected = decoderService.decode(value, type);

			logger.debug("checking field {} against {}", field, expected);
			return Objects.equals(expected, actual);
		} catch (Exception e) {
			return false;
		}
	}

	private Supplier<String> unknownField (String field) {
		return () -> "Unknown field " + field;
	}
}



