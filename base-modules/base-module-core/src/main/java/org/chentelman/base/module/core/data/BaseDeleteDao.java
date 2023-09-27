package org.chentelman.base.module.core.data;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * BaseDeleteDao
 *
 * This class defines the base operations for removing a {@link BaseEntity} from a storage
 *
 * @param <E> the type of the entity to persist
 * @param <I> the type of the key of the entity
 */
public interface BaseDeleteDao<E extends BaseEntity<I>, I> extends BaseCommonDao<E, I> {

	/**
	 * Removes an entity from the persistent store.
	 *
	 * @param entity The entity to remove.
	 */
	public void delete (E entity);

	/**
	 * Removes an entity from the persistent store.
	 *
	 * @param id The internal identifier of the entity to delete.
	 */
	public void deleteById (I id);

	/**
	 * Removes the given entities from the persistent store.
	 *
	 * @param entities The entities to remove.
	 */
	public default void delete (final Iterable<E> entities) {
		for (E entity : entities) {
			this.delete(entity);
		}
	}
}



