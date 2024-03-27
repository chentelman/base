package org.chentelman.base.module.data.mem.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * Default implementation for the base partitioned access dao.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 * @param <P> the partition type for the map
 */
public class BaseMemPartitionedAccessDaoImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseComponent implements BaseMemPartitionedAccessDao<E, I, P> {

	/**
	 * Internal memory storage
	 */
	protected Map<P, Map<I, E>> data;

	/**
	 * Generate the dao with a custom Map implementation for the internal storage
	 *
	 * @param data map used for the internal storage
	 */
	public BaseMemPartitionedAccessDaoImpl (Map<P, Map<I, E>> data) {
		this.data = data;
	}

	// access dao interface

	/**
	 * {@inheritDoc}
	 *
	 * In a partitioned dao entities are spread across different partitions.
	 * This function will have to combine the results of all of those partitions
	 * to get the list of all the entities.
	 */
	@Override
	public Collection<E> findAll () {
		return data.values().stream()
			.map(Map::values)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 *
	 * In a partitioned dao entities are spread across different partitions.
	 * This function will have to sum the results of all partitions to get
	 * the overall entity count.
	 */
	@Override
	public long count () {
		return data.values().stream()
			.map(Map::size)
			.reduce(0, (l, r) -> l + r);
	}

	/**
	 * {@inheritDoc}
	 *
	 * In a partitioned dao entities multiple entities with the same id
	 * can be found in different partitions. This function will return the
	 * first one found.
	 */
	@Override
	public Optional<E> findById (I id) {
		return data.values().stream()
			.map(Map::values)
			.flatMap(Collection::stream)
			.filter(c -> c.getId().equals(id))
			.findFirst();
	}

	// access partitioned dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<E> findAllById (I id) {
		return data.values().stream()
			.map(Map::values)
			.flatMap(Collection::stream)
			.filter(c -> c.getId().equals(id))
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<E> findAll (P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return List.of();
		}

		Map<I, E> partition = data.get(partitionKey);

		if (Objects.isNull(partition)) {
			return List.of();
		}

		return partition.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count (P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return 0;
		}

		Map<I, E> partition = data.get(partitionKey);

		if (Objects.isNull(partition)) {
			return 0;
		}

		return partition.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<E> findById (I id, P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return Optional.empty();
		}

		Map<I, E> partition = data.get(partitionKey);

		if (Objects.isNull(partition)) {
			return Optional.empty();
		}

		if (Objects.isNull(id)) {
			return Optional.empty();
		}

		return Optional.ofNullable(partition.get(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists (I id) {
		if (Objects.isNull(id)) {
			return false;
		}

		return data.values().stream().anyMatch(p -> p.containsKey(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists (I id, P partitionKey) {
		if (Objects.isNull(id) || Objects.isNull(partitionKey)) {
			return false;
		}

		Map<I, E> partition = data.get(partitionKey);

		if (Objects.nonNull(partition)) {
			return partition.containsKey(id);
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasPartition (P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return false;
		}

		return data.containsKey(partitionKey);
	}
}



