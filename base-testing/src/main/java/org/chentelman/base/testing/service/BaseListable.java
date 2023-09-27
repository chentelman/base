package org.chentelman.base.testing.service;

/**
 * Base interface for listable objects.
 *
 * Beans implementing this interface will be eligible to be discovered
 * by the {@link BaseListableService} implementations
 *
 * Two methods are defined at the base level.
 * 1. a method to retrieve a name for the listable to be used for differentiating the objects
 * 2. a method to clear the listable, to allow all of the discovered objects to be cleared
 *    by the respective service that discovered them.
 */
public interface BaseListable {

	/**
	 * retrieve the name of the listable object
	 * must be unique among all discovered beans
	 *
	 * @return the name of the listable object
	 */
	public String getName ();

	/**
	 * reset the base listable to its original state (if applicable).
	 */
	public void clear();
}



