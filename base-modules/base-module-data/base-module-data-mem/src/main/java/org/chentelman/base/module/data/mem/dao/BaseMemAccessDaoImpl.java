package org.chentelman.base.module.data.mem.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Default implementation for the base access dao.
 * Inherits {@link ConcurrentHashMap} to provide the implementation
 * for the missing methods.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
public class BaseMemAccessDaoImpl<E extends BaseEntity<I>, I> extends BaseComponent implements BaseMemAccessDao<E, I> {

	/**
	 * Internal memory storage
	 */
	protected Map<I, E> data;

	/**
	 * Generate the dao with a custom Map implementation for the internal storage
	 *
	 * @param data map used for the internal storage
	 */
	public BaseMemAccessDaoImpl (Map<I, E> data) {
		this.data = data;
	}

	// access dao interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<E> findAll () {
		return data.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<E> findAll (Pageable pageable) {
		// retrieve the collection of values
		Collection<E> items = data.values ();

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
	public long count () {
		return data.size ();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<E> findById (I id) {
		if (Objects.isNull(id)) {
			return Optional.empty();
		}
		return Optional.ofNullable(data.get (id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists (I id) {
		if (Objects.isNull(id)) {
			return false;
		}
		return data.containsKey(id);
	}

}



