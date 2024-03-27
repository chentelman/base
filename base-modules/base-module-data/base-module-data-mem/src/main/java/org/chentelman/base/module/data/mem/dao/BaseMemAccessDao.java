package org.chentelman.base.module.data.mem.dao;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * Base mem access dao definition.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
public interface BaseMemAccessDao<E extends BaseEntity<I>, I> extends BaseAccessDao<E, I> {

	/**
	 * Verify if the item with the specified id is already in storage.
	 *
	 * @param id The internal identifier of the entity to verify.
	 * @return true if the entity exists, otherwise false
	 */
	public boolean exists (I id);
}



