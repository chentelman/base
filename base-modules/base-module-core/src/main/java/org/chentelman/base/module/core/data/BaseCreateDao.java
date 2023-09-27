package org.chentelman.base.module.core.data;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * BaseCreateDao
 *
 * This class defines the base operations for creating a {@link BaseEntity} to a storage
 *
 * @param <E> the type of the entity to persist
 * @param <I> the type of the key of the entity
 */
public interface BaseCreateDao<E extends BaseEntity<I>, I> extends BaseCommonDao<E, I> {

	/**
	 * Persist (i.e. creates) a new entity in the persistent store.
	 * <p>
	 * The persisted state is reflected in the passed entity, i.e. generated values are set.
	 *
	 * @param entity The entity to persist.
	 * @return E The persistent state of the saved object.
	 */
	public E add (E entity);
}



