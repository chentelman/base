package org.chentelman.base.module.data.mem.dao;

import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * Base dao definition using the Map interface.
 *
 * Provide default implementation for all dao methods to utilize the map interface
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
public interface BaseMemDao<E extends BaseEntity<I>, I> extends
	BaseCreateDao<E, I>,
	BaseUpdateDao<E, I>,
	BaseDeleteDao<E, I>,
	BaseMemAccessDao<E, I> {

	// create dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default E add (E entity) {
		return update (entity);
	}

	// delete dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default void delete(E entity) {
		deleteById(entity.getId());
	}
}



