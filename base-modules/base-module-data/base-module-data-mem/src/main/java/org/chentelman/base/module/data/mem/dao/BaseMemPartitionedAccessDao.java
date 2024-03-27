package org.chentelman.base.module.data.mem.dao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Base partitioned access dao definition.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
public interface BaseMemPartitionedAccessDao<E extends BasePartitionedEntity<I, P>, I, P> extends BasePartitionedAccessDao<E, I, P> {

	// access dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<E> findAll ();

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

	// access partitioned dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<E> findAllById (I id);

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
	public Collection<E> findAll (P partitionKey);

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
	 * Verify if the item with the specified id is already in storage.
	 *
	 * @param id The internal identifier of the entity to verify.
	 * @return true if the entity exists, otherwise false
	 */
	public boolean exists (I id);

	/**
	 * Verify if the item with the specified id and partition key is already in storage.
	 *
	 * @param id The internal identifier of the entity to verify.
	 * @param partitionKey The internal key of the partition to verify.
	 * @return true if the entity exists, otherwise false
	 */
	public boolean exists (I id, P partitionKey);

	/**
	 * Verify if any item for a specific partition key is already in storage.
	 *
	 * @param partitionKey The internal key of the partition to verify.
	 * @return true if the partition exists, otherwise false
	 */
	public boolean hasPartition (P partitionKey);
}



