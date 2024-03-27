package org.chentelman.base.module.data.mem.dao;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * Default implementation for the base partitioned dao.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 * @param <P> the partition type for the map
 */
public class BaseMemPartitionedDaoImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseMemPartitionedAccessDaoImpl<E, I, P> implements BaseMemPartitionedDao<E, I, P> {

	/**
	 * Partition map generator.
	 *
	 * When a new empty partition is created this supplier will be used
	 * to instantiate a new map for that partition
	 */
	protected Function<P, Map<I, E>> pmap;

	/**
	 * Generate the dao with a custom Map implementation for the internal storage
	 *
	 * @param data map used for the internal storage
	 */
	public BaseMemPartitionedDaoImpl(Map<P, Map<I, E>> data, Function<P, Map<I, E>> pmap) {
		super(data);

		this.pmap = pmap;
	}

	// update dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E update (E entity) {
		I id = entity.getId();
		P pk = entity.getPartition();

		if (Objects.isNull(id) || Objects.isNull(pk)) {
			return null;
		}

		Map<I, E> partition = data.computeIfAbsent(pk, pmap);

		partition.put (id, entity);

		return entity;
	}

	// delete dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById (I id) {
		if (Objects.nonNull(id)) {
			data.values().forEach(p -> p.remove(id));
		}
	}

	// delete partitioned interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById (I id, P partitionKey) {
		Map<I, E> partition = null;

		if (Objects.nonNull(partitionKey) && Objects.nonNull(id)) {
			partition = data.get(partitionKey);
		}

		if (Objects.nonNull(partition)) {
			partition.remove(id);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByPartitionKey (P partitionKey) {
		if (Objects.nonNull(partitionKey)) {
			data.remove (partitionKey);
		}
	}
}



