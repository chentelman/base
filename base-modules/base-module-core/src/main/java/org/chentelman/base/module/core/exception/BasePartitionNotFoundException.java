package org.chentelman.base.module.core.exception;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.http.HttpStatus;

/**
 * Base partition not found exception
 */
public class BasePartitionNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Base not found exceptions cannot be instantiated directly
	 *
	 * @param message a description for the exception
	 */
	private BasePartitionNotFoundException (String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	/**
	 * Instantiate a new exception with generated description
	 *
	 * @param partition the partition of the entity that was not found
	 * @param clazz the entity class that is being looked up
	 * @return an instance of the exception
	 */
	public static <E extends BasePartitionedEntity<I, P>, I, P> BasePartitionNotFoundException of (P partition, Class<E> clazz) {
		return new BasePartitionNotFoundException (
			String.format ("Partition %s for entity %s not found", partition, clazz.getCanonicalName())
		);
	}
}



