package org.chentelman.base.module.core.exception;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.http.HttpStatus;

/**
 * Base exception for duplicate errors
 */
public class BaseDuplicateException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Base duplicate exceptions cannot be instantiated directly
	 *
	 * @param message a description for the exception
	 */
	private BaseDuplicateException (String message) {
		super(HttpStatus.CONFLICT, message);
	}

	/**
	 * Instantiate a new exception with generated description
	 *
	 * @param id the id value that caused the conflict
	 * @param clazz the entity class that is being updated
	 * @return an instance of the exception
	 */
	public static <E extends BaseEntity<I>, I> BaseDuplicateException of (I id, Class<E> clazz) {
		return new BaseDuplicateException (
			String.format ("Entity %s with id %s already exists", clazz.getCanonicalName(), id)
		);
	}

	/**
	 * Instantiate a new exception with generated description
	 *
	 * @param id the id value that caused the conflict
	 * @param partition the partition value that caused the conflict
	 * @param clazz the entity class that is being updated
	 * @return an instance of the exception
	 */
	public static <E extends BasePartitionedEntity<I, P>, I, P> BaseDuplicateException of (I id, P partition, Class<E> clazz) {
		return new BaseDuplicateException (
			String.format ("Entity %s with id %s and partition %s already exists", clazz.getCanonicalName(), id, partition)
		);
	}
}



