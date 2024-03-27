package org.chentelman.base.module.data.mem.dao;

import java.util.Map;
import java.util.Objects;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * Default implementation for the base dao.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
public class BaseMemDaoImpl<E extends BaseEntity<I>, I> extends BaseMemAccessDaoImpl<E, I> implements BaseMemDao<E, I> {

	/**
	 * Generate the dao with a custom Map implementation for the internal storage
	 *
	 * @param data map used for the internal storage
	 */
	public BaseMemDaoImpl(Map<I, E> data) {
		super(data);
	}

	// update dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E update (E entity) {
		I id = entity.getId();

		if (Objects.isNull(id)) {
			return null;
		}

		data.put (id, entity);
		return entity;
	}

	// delete dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(I id) {
		if (Objects.nonNull(id)) {
			data.remove(id);
		}
	}
}



