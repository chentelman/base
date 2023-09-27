package org.chentelman.base.module.data.mem.service;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.exception.BaseInvalidOperationException;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.mem.dao.BaseMemAccessDao;
import org.chentelman.base.module.service.BaseDefaultServiceImpl;

public class BaseMemDefaultAccessServiceImpl<E extends BaseEntity<I>, I> extends BaseDefaultServiceImpl<E, I> {

	private final Class<E> servicedType;
	private final BaseMemAccessDao<E, I> inMemoryDao;

	public BaseMemDefaultAccessServiceImpl (
		Class<E> servicedType,
		BaseMemAccessDao<E, I> inMemoryDao
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;
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
	public boolean canCreate(E entity) {
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
	public boolean canUpdate(I id, E entity) {
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
	public boolean canDelete(E entity) {
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



