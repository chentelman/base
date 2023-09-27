package org.chentelman.base.testing.service;

/**
 * The base listable service allows accessing of the discovered listable objects
 * The interface is parametrised to allow further specializations of the listable objects
 * to be managed by different services.
 *
 * @param <T> the type of the base listable objects to discover.
 */
public interface BaseListableService<T extends BaseListable> {

	/**
	 * retrieve the listable object with the provided name
	 *
	 * In case a listable with the provided name does not exist
	 * an assertion will be raised.
	 *
	 * @param name the name of the listable to retrieve
	 * @return the listable object
	 */
	public T getListable (String name);

	/**
	 * retrieve a listable object specialisation with the provided name
	 *
	 * In case a listable with the provided name does not exist
	 * an assertion will be raised.
	 *
	 * @param name the name of the listable to retrieve
	 * @param clazz the object specialisation
	 * @return the listable object
	 */
	public default <S extends T> S getListable (String name, Class<S> clazz) {
		return clazz.cast(getListable (name));
	}

	/**
	 * retrieve the listable object with the provided name
	 * and mark it as the latest listable object.
	 *
	 * In case a different listable has been marked as latest
	 * it will be overriden by this method call.
	 *
	 * @param name the name of the listable to retrieve
	 * @return the listable object
	 */
	public T getLatestListable (String name);

	/**
	 * retrieve a listable object specialisation with the provided name
	 * and mark it as the latest listable object.
	 *
	 * In case a different listable has been marked as latest
	 * it will be overriden by this method call.
	 *
	 * @param name the name of the listable to retrieve
	 * @param clazz the object specialisation
	 * @return the listable object
	 */
	public default <S extends T> S getLatestListable (String name, Class<S> clazz) {
		return clazz.cast(getLatestListable (name));
	}

	/**
	 * retrieve the listable object marked as the latest listable object,
	 * by the getLatestListable method.
	 *
	 * In case no listable object is marked as latest an assertion will be raised.
	 *
	 * @return the latest listable object
	 */
	public T getLatestListable ();

	/**
	 * retrieve a listable object specialisation marked as the latest listable object,
	 * by the getLatestListable method.
	 *
	 * In case no listable object is marked as latest an assertion will be raised.
	 *
	 * @param clazz the object specialisation
	 * @return the latest listable object
	 */
	public default <S extends T> S getLatestListable (Class<S> clazz) {
		return clazz.cast(getLatestListable());
	}

	/**
	 * clear method will cascade the call to all discovered listable objects.
	 * The service it self will not be reset.
	 */
	public void clear();
}



