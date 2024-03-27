package org.chentelman.base.module.data.mem.dao;

import org.chentelman.base.module.core.data.BasePartitionedCreateDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.module.core.data.BasePartitionedUpdateDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * Base partitioned dao definition.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 * @param <P> the partitioned type for the map
 */
public interface BaseMemPartitionedDao<E extends BasePartitionedEntity<I, P>, I, P> extends
	BasePartitionedCreateDao<E, I, P>,
	BasePartitionedUpdateDao<E, I, P>,
	BasePartitionedDeleteDao<E, I, P>,
	BaseMemPartitionedAccessDao<E, I, P> {

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
	public default void delete (E entity) {
		deleteById (entity.getId(), entity.getPartition());
	}
}



