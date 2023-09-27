package org.chentelman.base.module.data.mem.service;

import java.util.Objects;
import java.util.function.Function;

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
import org.chentelman.base.module.service.BaseConvertPartitionedServiceImpl;

public class BaseMemConvertPartitionedServiceImpl<D extends BasePartitionedEntity<I, P>, E extends BasePartitionedEntity<I, P>, I, P> extends BaseConvertPartitionedServiceImpl<D, E, I, P> {

	private final Class<D> servicedType;
	private final BaseMemPartitionedDao<E, I, P> inMemoryDao;
	private final Function<E, D> domainConverter;
	private final Function<D, E> entityConverter;

	public BaseMemConvertPartitionedServiceImpl (
		Class<D> servicedType,
		BaseMemPartitionedDao<E, I, P> inMemoryDao,
		Function<E, D> domainConverter,
		Function<D, E> entityConverter
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;

		this.domainConverter = domainConverter;
		this.entityConverter = entityConverter;
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
	public D toDomain(E entity) {
		return domainConverter.apply(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E toEntity(D domain) {
		return entityConverter.apply(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canCreate(D domain) {
		if (inMemoryDao.exists(domain.getId(), domain.getPartition())) {
			throw BaseDuplicateException.of(domain.getId(), domain.getPartition(), servicedType);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, D domain) {
		return canUpdate (id, domain.getPartition(), domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, P partitionKey, D domain) {
		if (!Objects.equals(id, domain.getId())) {
			throw BaseCannotUpdateException.ofId(id, domain.getId(), servicedType);
		}

		if (!Objects.equals(partitionKey, domain.getPartition())) {
			throw BaseCannotUpdateException.ofPartition(partitionKey, domain.getPartition(), servicedType);
		}

		if (!inMemoryDao.exists(domain.getId(), domain.getPartition())) {
			throw BaseNotFoundException.of(id, partitionKey, servicedType);
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(D domain) {
		return canDelete (domain.getId(), domain.getPartition());
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
	public void handleNotFound(I id) {
		throw BaseNotFoundException.of(id, servicedType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleNotFound(I id, P partitionKey) {
		throw BaseNotFoundException.of(id, partitionKey, servicedType);
	}
}



