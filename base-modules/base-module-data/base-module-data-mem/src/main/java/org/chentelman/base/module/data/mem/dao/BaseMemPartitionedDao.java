package org.chentelman.base.module.data.mem.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.chentelman.base.module.core.data.BasePartitionedCreateDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.module.core.data.BasePartitionedUpdateDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * Base partitioned dao definition using the Map interface.
 *
 * Provide default implementation for all partition dao methods to utilize the map interface
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

	// update dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default E update (E entity) {
		I id = entity.getId();
		P pk = entity.getPartition();

		if (Objects.isNull(id) || Objects.isNull(pk)) {
			return null;
		}

		Map<I, E> partition = get(pk);

		if (Objects.isNull(partition)) {
			partition = new HashMap<>();
			put (pk, partition);
		}

		partition.put (id, entity);

		return entity;
	}

	// delete dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default void delete (E entity) {
		deleteById (entity.getId(), entity.getPartition());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default void deleteById (I id) {
		if (Objects.nonNull(id)) {
			values().forEach(p -> p.remove(id));
		}
	}

	// delete partitioned interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default void deleteById (I id, P partitionKey) {
		Map<I, E> partition = null;

		if (Objects.nonNull(partitionKey) && Objects.nonNull(id)) {
			partition = get(partitionKey);
		}

		if (Objects.nonNull(partition)) {
			partition.remove(id);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default  void deleteByPartitionKey (P partitionKey) {
		if (Objects.nonNull(partitionKey)) {
			remove (partitionKey);
		}
	}
}



