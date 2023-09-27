package org.chentelman.base.module.service;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.data.BasePartitionedCreateDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.module.core.data.BasePartitionedUpdateDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.service.BasePartitionedService;

public interface BaseConvertPartitionedService<D extends BasePartitionedEntity<I, P>, E extends BasePartitionedEntity<I, P>, I, P> extends
	BasePartitionedService<D, I, P>,
	BaseConvertService<D, E, I> {

	/*
	 * BasePartitionedCreateDao interface
	 */

	/**
	 * retrieve a base partitioned create dao object to execute the create operations.
	 *
	 * @return an BaseCreateDao<E, I> object
	 */
	public BasePartitionedCreateDao<E, I, P> getCreateDao ();

	/*
	 * BasePartitionedAccessDao interface
	 */

	/**
	 * retrieve a base partitioned access dao object to execute the read operations.
	 *
	 * @return an BaseCreateDao<E, I> object
	 */
	public BasePartitionedAccessDao<E, I, P> getAccessDao ();

	/**
	 * handle not found case.
	 * This is used to decide if an exception is to be thrown.
	 * Otherwise null is retured by the service.
	 */
	public void handleNotFound (I id, P partitionKey);

	/*
	 * BasePartitionedUpdateDao interface
	 */

	/**
	 * retrieve a base partitioned update dao object to execute the update operations.
	 *
	 * @return an BasePartitionedUpdateDao<E, I, P> object
	 */
	public BasePartitionedUpdateDao<E, I, P> getUpdateDao ();

	/**
	 * verify if the entity can be updated.
	 *
	 * Method may
	 *  1. return true, in case the update is possible
	 *  2. return false, in case the update must be aborted
	 *  3. throw an error
	 *
	 * @param entity the entity in question
	 * @return whether the entity can be updated
	 */
	public boolean canUpdate (I id, P partitionKey, D entity);

	/*
	 * BasePartitionedDeleteDao interface
	 */

	/**
	 * retrieve a base partitioned delete dao object to execute the delete operations.
	 *
	 * @return an BasePartitionedDeleteDao<E, I> object
	 */
	public BasePartitionedDeleteDao<E, I, P> getDeleteDao ();

	/**
	 * verify if the entity can be deleted.
	 *
	 * Method may
	 *  1. return true, in case the deletion is possible
	 *  2. return false, in case the delete must be aborted
	 *  3. throw an error
	 *
	 * @param id the id in question
	 * @param partitionKey the key of the partition
	 * @return whether the entity can be deleted
	 */
	public boolean canDelete (I id, P partitionKey);

	/**
	 * verify if the partition can be deleted.
	 *
	 * Method may
	 *  1. return true, in case the deletion is possible
	 *  2. return false, in case the delete must be aborted
	 *  3. throw an error
	 *
	 * @param partitionKey the key of the partition
	 * @return whether the entity can be deleted
	 */
	public boolean canDeletePartition (P partitionKey);
}



