package org.chentelman.base.module.core.exception;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.http.HttpStatus;

/**
 * Base exception for invalid update operations
 */
public class BaseCannotUpdateException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Base invalid update exceptions cannot be instantiated directly
	 *
	 * @param message a description for the exception
	 */
	private BaseCannotUpdateException (String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

	/**
	 * Instantiate a new exception with generated description
	 *
	 * @param from original id value
	 * @param to the new id value
	 * @param clazz the entity class that is being updated
	 * @return an instance of the exception
	 */
	public static <E extends BaseEntity<I>, I> BaseCannotUpdateException ofId (I from, I to, Class<E> clazz) {
		return new BaseCannotUpdateException (
			String.format ("Cannot update %s entity's id from %s to %s", clazz.getCanonicalName(), from, to)
		);
	}

	/**
	 * Instantiate a new exception with generated description
	 *
	 * @param from original partition value
	 * @param to the new partition value
	 * @param clazz the entity class that is being updated
	 * @return an instance of the exception
	 */
	public static <E extends BasePartitionedEntity<I, P>, I, P> BaseCannotUpdateException ofPartition (P from, P to, Class<E> clazz) {
		return new BaseCannotUpdateException (
			String.format ("Cannot update %s entity's partition from %s to %s", clazz.getCanonicalName(), from, to)
		);
	}
}



