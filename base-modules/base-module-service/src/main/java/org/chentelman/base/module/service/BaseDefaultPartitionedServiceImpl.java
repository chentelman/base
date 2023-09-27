package org.chentelman.base.module.service;

import java.util.List;
import java.util.Optional;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * A default implementation for the base service partitioned interfaces.
 *
 * Reuses the dao partitioned entity for the serviced entity.
 */
public abstract class BaseDefaultPartitionedServiceImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseDefaultServiceImpl<E, I> implements BaseDefaultPartitionedService<E, I, P> {

	/*
	 * BasePartitionedAccessDao interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<E> readAllById (I id) {
		return BaseUtilities.toList(getAccessDao().findAllById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<E> readAllById (Pageable page, I id) {
		return BaseUtilities.toList(getAccessDao().findAllById(page, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<E> readAll (P partitionKey) {
		return BaseUtilities.toList(getAccessDao().findAll(partitionKey));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<E> readAll (Pageable pageable, P partitionKey) {
		return BaseUtilities.toList(getAccessDao().findAll(pageable, partitionKey));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public long count (P partitionKey) {
		return getAccessDao().count(partitionKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public E read (I id, P partitionKey) {
		Optional<E> entity = getAccessDao().findById(id, partitionKey);

		if (entity.isEmpty()) {
			handleNotFound(id, partitionKey);
			return null;
		}

		return entity.get();
	}

	/*
	 * BasePartitionedUpdateDao interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void update (I id, P partitionKey, E entity) {
		if (canUpdate(id, partitionKey, entity)) {
			getUpdateDao().update(entity);
		}
	}

	/*
	 * BasePartitionedDeleteDao interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void delete (I id, P partitionKey) {
		if (canDelete(id, partitionKey)) {
			getDeleteDao().deleteById(id, partitionKey);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void deleteAll (P partitionKey) {
		if (canDeletePartition(partitionKey)) {
			getDeleteDao().deleteByPartitionKey(partitionKey);
		}
	}
}



