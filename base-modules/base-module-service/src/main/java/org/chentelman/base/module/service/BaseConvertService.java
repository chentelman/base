package org.chentelman.base.module.service;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.service.BaseService;

public interface BaseConvertService<D extends BaseEntity<I>, E extends BaseEntity<I>, I> extends BaseService<D, I> {

	/*
	 * BaseCreateService interface
	 */

	/**
	 * retrieve a base create dao object to execute the creation actions.
	 *
	 * @return an BaseCreateDao<E, I> object
	 */
	public BaseCreateDao<E, I> getCreateDao ();

	/**
	 * verify if the entity can be created.
	 *
	 * Method may
	 *  1. return true, in case the creation is possible
	 *  2. return false, in case the creation must be aborted
	 *  3. throw an error
	 *
	 * @return whether the entity can be created.
	 */
	public boolean canCreate (D entity);

	/*
	 * BaseAccessService interface
	 */

	/**
	 * retrieve a base access dao object to execute the read operations.
	 *
	 * @return an BaseCreateDao<E, I> object
	 */
	public BaseAccessDao<E, I> getAccessDao ();

	/**
	 * handle not found case.
	 * This is used to decide if an exception is to be thrown.
	 * Otherwise null is retured by the service.
	 */
	public void handleNotFound (I id);

	/*
	 * BaseUpdateService interface
	 */

	/**
	 * retrieve a base update dao object to execute the update actions.
	 *
	 * @return an BaseUpdateDao<E, I> object
	 */
	public BaseUpdateDao<E, I> getUpdateDao ();

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
	public boolean canUpdate (I id, D entity);

	/*
	 * BaseDeleteService interface
	 */

	/**
	 * retrieve a base delete dao object to execute the delete actions.
	 *
	 * @return an BaseDeleteDao<E, I> object
	 */
	public BaseDeleteDao<E, I> getDeleteDao ();

	/**
	 * verify if the entity can be deleted.
	 *
	 * Method may
	 *  1. return true, in case the deletion is possible
	 *  2. return false, in case the delete must be aborted
	 *  3. throw an error
	 *
	 * @param entity the entity in question
	 * @return whether the entity can be deleted
	 */
	public boolean canDelete (D entity);

	/**
	 * verify if the entity can be deleted.
	 *
	 * Method may
	 *  1. return true, in case the deletion is possible
	 *  2. return false, in case the delete must be aborted
	 *  3. throw an error
	 *
	 * @param id the id in question
	 * @return whether the entity can be deleted
	 */
	public boolean canDelete (I id);
}



