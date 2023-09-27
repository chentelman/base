package org.chentelman.base.module.data.mem.service;

import java.util.function.Function;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.exception.BaseInvalidOperationException;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.mem.dao.BaseMemAccessDao;
import org.chentelman.base.module.service.BaseConvertServiceImpl;

public class BaseMemConvertAccessServiceImpl<D extends BaseEntity<I>, E extends BaseEntity<I>, I> extends BaseConvertServiceImpl<D, E, I> {

	private final Class<D> servicedType;
	private final BaseMemAccessDao<E, I> inMemoryDao;
	private final Function<E, D> domainConverter;

	public BaseMemConvertAccessServiceImpl (
		Class<D> servicedType,
		BaseMemAccessDao<E, I> inMemoryDao,
		Function<E, D> domainConverter
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;

		this.domainConverter = domainConverter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public D toDomain(E entity) {
		return domainConverter.apply(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E toEntity(D domain) {
		throw BaseInvalidOperationException.of(
			String.format("Entity %s does not support convertion to dao managed type",
				servicedType.getCanonicalName()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseCreateDao<E, I> getCreateDao() {
		throw BaseInvalidOperationException.of(
			String.format("Entity %s does not support create operations",
				servicedType.getCanonicalName()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canCreate(D domain) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseAccessDao<E, I> getAccessDao() {
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleNotFound(I id) {
		throw BaseNotFoundException.of(id, servicedType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseUpdateDao<E, I> getUpdateDao() {
		throw BaseInvalidOperationException.of(
			String.format("Entity %s does not support update operations",
				servicedType.getCanonicalName()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, D domain) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseDeleteDao<E, I> getDeleteDao() {
		throw BaseInvalidOperationException.of(
			String.format("Entity %s does not support delete operations",
				servicedType.getCanonicalName()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(D domain) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(I id) {
		return true;
	}
}



