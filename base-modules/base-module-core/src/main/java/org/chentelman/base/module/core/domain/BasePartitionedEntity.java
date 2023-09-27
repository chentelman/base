package org.chentelman.base.module.core.domain;

/**
 * BasePartitionedEntity definition
 *
 * An extention of the {@link BaseEntity} that additionally provide a partition field.
 *
 * @param <I> the id type of the base partitioned entity
 * @param <P> the partition type of the base partitioned entity
 */
public interface BasePartitionedEntity<I, P> extends BaseEntity<I> {

	/**
	 * Return the partition key that corresponds to the entity
	 *
	 * @return P the partition of the entity
	 */
	public P getPartition ();
}



