package org.chentelman.base.module.data.mem.service;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.data.BasePartitionedCreateDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.module.core.data.BasePartitionedUpdateDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.exception.BaseInvalidOperationException;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.mem.dao.BaseMemPartitionedAccessDao;
import org.chentelman.base.module.service.BaseDefaultPartitionedServiceImpl;

public class BaseMemDefaultPartitionedAccessServiceImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseDefaultPartitionedServiceImpl<E, I, P> {

	private final Class<E> servicedType;
	private final BaseMemPartitionedAccessDao<E, I, P> inMemoryDao;

	public BaseMemDefaultPartitionedAccessServiceImpl (
		Class<E> servicedType,
		BaseMemPartitionedAccessDao<E, I, P> inMemoryDao
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasePartitionedCreateDao<E, I, P> getCreateDao() {
		throw BaseInvalidOperationException.of(
			String.format("Entity %s does not support create operations",
				servicedType.getCanonicalName()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasePartitionedAccessDao<E, I, P> getAccessDao() {
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasePartitionedUpdateDao<E, I, P> getUpdateDao() {
		throw BaseInvalidOperationException.of(
			String.format("Entity %s does not support update operations",
				servicedType.getCanonicalName()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasePartitionedDeleteDao<E, I, P> getDeleteDao() {
		throw BaseInvalidOperationException.of(
			String.format("Entity %s does not support delete operations",
				servicedType.getCanonicalName()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(I id, P partitionKey) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDeletePartition(P partitionKey) {
		return true;
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
	public boolean canUpdate(I id, E entity) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, P partitionKey, E entity) {
		return true;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleNotFound(I id, P partitionKey) {
		throw BaseNotFoundException.of(id, partitionKey, servicedType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleNotFound(I id) {
		throw BaseNotFoundException.of(id, servicedType);
	}
}



