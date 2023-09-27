package org.chentelman.base.module.core.domain;

import java.io.Serializable;

/**
 * BaseEntity definition
 *
 * As far as the base entity is concerned the only limitation is to provide a sort of a key field.
 *
 * @param <I> the id type of the base entity
 */
public interface BaseEntity<I> extends Serializable {

	/**
	 * Return the key that corresponds to the entity
	 *
	 * @return I the key of the entity
	 */
	public I getId ();
}



