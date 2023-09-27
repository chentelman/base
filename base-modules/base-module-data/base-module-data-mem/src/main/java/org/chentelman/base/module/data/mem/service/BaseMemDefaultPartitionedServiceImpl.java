package org.chentelman.base.module.data.mem.service;

import java.util.Objects;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.data.BasePartitionedCreateDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.module.core.data.BasePartitionedUpdateDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.exception.BaseCannotUpdateException;
import org.chentelman.base.module.core.exception.BaseDuplicateException;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.core.exception.BasePartitionNotFoundException;
import org.chentelman.base.module.data.mem.dao.BaseMemPartitionedDao;
import org.chentelman.base.module.service.BaseDefaultPartitionedServiceImpl;

public class BaseMemDefaultPartitionedServiceImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseDefaultPartitionedServiceImpl<E, I, P> {

	private final Class<E> servicedType;
	private final BaseMemPartitionedDao<E, I, P> inMemoryDao;

	public BaseMemDefaultPartitionedServiceImpl (
		Class<E> servicedType,
		BaseMemPartitionedDao<E, I, P> inMemoryDao
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasePartitionedCreateDao<E, I, P> getCreateDao() {
		return inMemoryDao;
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
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasePartitionedDeleteDao<E, I, P> getDeleteDao() {
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(I id, P partitionKey) {
		if (!inMemoryDao.exists(id, partitionKey)) {
			throw BaseNotFoundException.of(id, partitionKey, servicedType);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDeletePartition(P partitionKey) {
		if (!inMemoryDao.hasPartition(partitionKey)) {
			throw BasePartitionNotFoundException.of(partitionKey, servicedType);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canCreate(E entity) {
		if (inMemoryDao.exists(entity.getId(), entity.getPartition())) {
			throw BaseDuplicateException.of(entity.getId(), entity.getPartition(), servicedType);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, E entity) {
		return canUpdate (id, entity.getPartition(), entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, P partitionKey, E entity) {
		if (!Objects.equals(id, entity.getId())) {
			throw BaseCannotUpdateException.ofId(id, entity.getId(), servicedType);
		}

		if (!Objects.equals(partitionKey, entity.getPartition())) {
			throw BaseCannotUpdateException.ofPartition(partitionKey, entity.getPartition(), servicedType);
		}

		if (!inMemoryDao.exists(entity.getId(), entity.getPartition())) {
			throw BaseNotFoundException.of(id, partitionKey, servicedType);
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(E entity) {
		return canDelete (entity.getId(), entity.getPartition());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(I id) {
		if (!inMemoryDao.exists(id)) {
			throw BaseNotFoundException.of(id, servicedType);
		}
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



