package org.chentelman.base.module.data.mem.service;

import java.util.function.Function;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.data.BasePartitionedCreateDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.module.core.data.BasePartitionedUpdateDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.exception.BaseInvalidOperationException;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.mem.dao.BaseMemPartitionedAccessDao;
import org.chentelman.base.module.service.BaseConvertPartitionedServiceImpl;

public class BaseMemConvertPartitionedAccessServiceImpl<D extends BasePartitionedEntity<I, P>, E extends BasePartitionedEntity<I, P>, I, P> extends BaseConvertPartitionedServiceImpl<D, E, I, P> {

	private final Class<D> servicedType;
	private final BaseMemPartitionedAccessDao<E, I, P> inMemoryDao;
	private final Function<E, D> domainConverter;

	public BaseMemConvertPartitionedAccessServiceImpl (
		Class<D> servicedType,
		BaseMemPartitionedAccessDao<E, I, P> inMemoryDao,
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
	public boolean canCreate(D domain) {
		return true;
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
	public boolean canUpdate(I id, P partitionKey, D domain) {
		return true;
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



