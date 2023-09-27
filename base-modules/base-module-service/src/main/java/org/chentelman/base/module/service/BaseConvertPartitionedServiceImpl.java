package org.chentelman.base.module.service;

import java.util.List;
import java.util.Optional;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * A implementation for the base service partitioned interfaces, converting the dao managed type
 * to a different serviced one.
 */
public abstract class BaseConvertPartitionedServiceImpl<D extends BasePartitionedEntity<I, P>, E extends BasePartitionedEntity<I, P>, I, P> extends BaseConvertServiceImpl<D, E, I> implements BaseConvertPartitionedService<D, E, I, P> {

	/*
	 * BasePartitionedAccessDao interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<D> readAllById (I id) {
		return BaseUtilities.toList(getAccessDao().findAllById(id), this::toDomain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<D> readAllById (Pageable page, I id) {
		return BaseUtilities.toList(getAccessDao().findAllById(page, id), this::toDomain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<D> readAll (P partitionKey) {
		return BaseUtilities.toList(getAccessDao().findAll(partitionKey), this::toDomain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<D> readAll (Pageable pageable, P partitionKey) {
		return BaseUtilities.toList(getAccessDao().findAll(pageable, partitionKey), this::toDomain);
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
	public D read (I id, P partitionKey) {
		Optional<E> entity = getAccessDao().findById(id, partitionKey);

		if (entity.isEmpty()) {
			handleNotFound(id, partitionKey);
			return null;
		}

		return toDomain(entity.get());
	}

	/*
	 * BasePartitionedUpdateDao interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void update (I id, P partitionKey, D domain) {
		if (canUpdate(id, partitionKey, domain)) {
			getUpdateDao().update(toEntity(domain));
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



