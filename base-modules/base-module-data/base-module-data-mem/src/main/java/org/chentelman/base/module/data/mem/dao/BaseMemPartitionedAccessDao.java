package org.chentelman.base.module.data.mem.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Base partitioned access dao definition using the Map interface.
 *
 * Provide default implementation for all access dao methods to utilize the map interface
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
public interface BaseMemPartitionedAccessDao<E extends BasePartitionedEntity<I, P>, I, P> extends BasePartitionedAccessDao<E, I, P>, Map<P, Map<I, E>> {

	// access dao interface

	/**
	 * {@inheritDoc}
	 *
	 * In a partitioned dao entities are spread across different partitions.
	 * This function will have to combine the results of all of those partitions
	 * to get the list of all the entities.
	 */
	@Override
	public default Collection<E> findAll () {
		return values().stream()
			.map(Map::values)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 *
	 * In a partitioned dao entities are spread across different partitions.
	 * This function will have to combine the results of all of those partitions
	 * before the page of the results is constructed.
	 */
	@Override
	public default Page<E> findAll (Pageable pageable) {
		// retrieve the collection of values
		Collection<E> items = findAll ();

		// stream the values
		List<E> paged = items.stream()
			.skip(pageable.getOffset())
			.limit(pageable.getPageSize())
			.collect(Collectors.toList());

		// return the filtered items as a page
		return new PageImpl<>(paged, pageable, items.size());
	}

	/**
	 * {@inheritDoc}
	 *
	 * In a partitioned dao entities are spread across different partitions.
	 * This function will have to sum the results of all partitions to get
	 * the overall entity count.
	 */
	@Override
	public default long count () {
		return values().stream()
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
	public default Optional<E> findById (I id) {
		return values().stream()
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
	public default Collection<E> findAllById (I id) {
		return values().stream()
			.map(Map::values)
			.flatMap(Collection::stream)
			.filter(c -> c.getId().equals(id))
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default Iterable<E> findAllById (Pageable pageable, I id) {
		// retrieve the collection of values
		Collection<E> items = findAllById (id);

		// stream the values
		List<E> paged = items.stream()
			.skip(pageable.getOffset())
			.limit(pageable.getPageSize())
			.collect(Collectors.toList());

		// return the filtered items as a page
		return new PageImpl<>(paged, pageable, items.size());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default Collection<E> findAll (P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return List.of();
		}

		Map<I, E> partition = get(partitionKey);

		if (Objects.isNull(partition)) {
			return List.of();
		}

		return partition.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default Page<E> findAll (Pageable pageable, P partitionKey) {
		// retrieve the collection of values
		Collection<E> items = findAll (partitionKey);

		// stream the values
		List<E> paged = items.stream()
			.skip(pageable.getOffset())
			.limit(pageable.getPageSize())
			.collect(Collectors.toList());

		// return the filtered items as a page
		return new PageImpl<>(paged, pageable, items.size());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default long count (P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return 0;
		}

		Map<I, E> partition = get(partitionKey);

		if (Objects.isNull(partition)) {
			return 0;
		}

		return partition.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default Optional<E> findById (I id, P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return Optional.empty();
		}

		Map<I, E> partition = get(partitionKey);

		if (Objects.isNull(partition)) {
			return Optional.empty();
		}

		if (Objects.isNull(id)) {
			return Optional.empty();
		}

		return Optional.ofNullable(partition.get(id));
	}

	/**
	 * Verify if the item with the specified id is already in storage.
	 *
	 * @param id The internal identifier of the entity to verify.
	 * @return true if the entity exists, otherwise false
	 */
	public default boolean exists (I id) {
		if (Objects.isNull(id)) {
			return false;
		}

		return values().stream().anyMatch(p -> p.containsKey(id));
	}

	/**
	 * Verify if the item with the specified id and partition key is already in storage.
	 *
	 * @param id The internal identifier of the entity to verify.
	 * @param partitionKey The internal key of the partition to verify.
	 * @return true if the entity exists, otherwise false
	 */
	public default boolean exists (I id, P partitionKey) {
		if (Objects.isNull(id) || Objects.isNull(partitionKey)) {
			return false;
		}

		Map<I, E> partition = get(partitionKey);

		if (Objects.nonNull(partition)) {
			return partition.containsKey(id);
		}

		return false;
	}

	/**
	 * Verify if any item for a specific partition key is already in storage.
	 *
	 * @param partitionKey The internal key of the partition to verify.
	 * @return true if the partition exists, otherwise false
	 */
	public default boolean hasPartition (P partitionKey) {
		if (Objects.isNull(partitionKey)) {
			return false;
		}

		return containsKey(partitionKey);
	}
}



