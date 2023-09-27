package org.chentelman.base.testing.objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.chentelman.base.testing.conveter.BaseDecoderService;
import org.chentelman.base.testing.service.BaseStorageImpl;

import io.cucumber.datatable.DataTable;
import lombok.EqualsAndHashCode;

/**
 * Default implementation of the base object service.
 *
 * Service depends on {@link BaseObjectBuilder} to build each individual object.
 *
 * Those objects can either be set via the setBuilder method.
 * In case none is found the default implementation of the base object builder
 * will be used instead.
 */
@EqualsAndHashCode(callSuper = false)
public final class BaseObjectServiceImpl extends BaseStorageImpl<Object> implements BaseObjectService {
	private static final long serialVersionUID = 1L;

	private final BaseDecoderService decoderService;
	private final Map<String, BaseObjectBuilder> builders;

	public BaseObjectServiceImpl (BaseDecoderService decoderService) {
		this.decoderService = decoderService;
		this.builders = new HashMap<> ();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S> S build (Class<S> clazz) {
		return clazz.cast(getBuilder(clazz).build());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S> S build (DataTable data, Class<S> clazz) {
		return clazz.cast(getBuilder(clazz).build(data));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object build (String type) {
		return getBuilder(type).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object build (DataTable data, String type) {
		return getBuilder(type).build(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decode (String data, String type) {
		return decoderService.decode(data, type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <D> D decode (String data, Class<D> type) {
		return decoderService.instantiate(data, type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void populate (Object object, DataTable data, String type) {
		getBuilder(type).populate(object, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <D> void populate (D object, DataTable data, Class<D> clazz) {
		getBuilder(clazz).populate(object, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean check (Object object, DataTable data, String type) {
		return getBuilder(type).check(object, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S> boolean check (S object, DataTable data, Class<S> clazz) {
		return getBuilder(clazz).check(object, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseObjectBuilder setBuilder (Class<?> clazz, BaseObjectBuilder builder) {
		return setBuilder (clazz.getCanonicalName(), builder);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseObjectBuilder setBuilder (String key, BaseObjectBuilder builder) {
		assertNotNull(key, "cannot associate an object builder with a null key");
		assertNotNull(builder, "cannot associate a null object builder");
		return builders.put(key, builder);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E> BaseObjectBuilder getBuilder (Class<E> clazz) {
		return builders.computeIfAbsent (
			clazz.getCanonicalName(),
			k -> new BaseObjectBuilderImpl<E>(clazz, decoderService)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseObjectBuilder getBuilder (String key) {
		assertTrue (builders.containsKey(key), () -> String.format("builder for %s not found", key));
		return builders.get(key);
	}
}



