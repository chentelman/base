package org.chentelman.base.module.core.exception;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.http.HttpStatus;

/**
 * Base not found exception
 */
public class BaseNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Base not found exceptions cannot be instantiated directly
	 *
	 * @param message a description for the exception
	 */
	private BaseNotFoundException (String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	/**
	 * Instantiate a new exception with generated description
	 *
	 * @param id the id of the entity that was not found
	 * @param clazz the entity class that is being looked up
	 * @return an instance of the exception
	 */
	public static <E extends BaseEntity<I>, I> BaseNotFoundException of (I id, Class<E> clazz) {
		return new BaseNotFoundException (
			String.format ("Entity %s with id %s not found", clazz.getCanonicalName(), id)
		);
	}

	/**
	 * Instantiate a new exception with generated description
	 *
	 * @param id the id of the entity that was not found
	 * @param partition the partition of the entity that was not found
	 * @param clazz the entity class that is being looked up
	 * @return an instance of the exception
	 */
	public static <E extends BasePartitionedEntity<I, P>, I, P> BaseNotFoundException of (I id, P partition, Class<E> clazz) {
		return new BaseNotFoundException (
			String.format ("Entity %s with id %s and partition %s not found", clazz.getCanonicalName(), id, partition)
		);
	}
}



