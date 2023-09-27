package org.chentelman.base.module.data.mem.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Base access dao definition using the Map interface.
 *
 * Provide default implementation for all access dao methods to utilize the map interface
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
public interface BaseMemAccessDao<E extends BaseEntity<I>, I> extends BaseAccessDao<E, I>, Map<I, E> {

	// access dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default Iterable<E> findAll () {
		return values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default Page<E> findAll (Pageable pageable) {
		// retrieve the collection of values
		Collection<E> items = values ();

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
	public default long count () {
		return size ();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default Optional<E> findById (I id) {
		if (Objects.isNull(id)) {
			return Optional.empty();
		}
		return Optional.ofNullable(get (id));
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
		return containsKey(id);
	}
}



