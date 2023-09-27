package org.chentelman.base.testing.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.datatable.DataTable;

/**
 * Extended base storage interface.
 *
 * This will be used to store a parameterised list of items using the default
 * map interface as well as provide to more functions to build and store an element
 * using a {@link DataTable} to represent that element.
 *
 * @param <T> the type of stored elements in the storage
 */
public interface BaseStorageBuilder<T> extends BaseStorage<T> {

	/**
	 * Generate an empty instance of the requested type
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param clazz the class of the entry to create. must be a subclass of the managed storage class.
	 * @return the entry created
	 */
	public <S extends T> S build (Class<S> clazz);

	/**
	 * Generate a storage entry from cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param data the data table describing the entry to create
	 * @param clazz the class of the entry to create. must be a subclass of the managed storage class.
	 * @return the entry created
	 */
	public <S extends T> S build (DataTable data, Class<S> clazz);

	/**
	 * Populate a storage entry from cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param element the data entry to populate
	 * @param data the data table describing the entry to create
	 * @param clazz the class of the entry to create. must be a subclass of the managed storage class.
	 */
	public <S extends T> void populate (S element, DataTable data, Class<S> clazz);

	/**
	 * Generate and store a storage entry from cucumbers Data table spec
	 *
	 * The generated entry will be stored under the key value.
	 * In case a previous entry already exists under this key
	 * the entry will be overridden and the previous entry will
	 * be returned.
	 *
	 * @param key the name the entry will be stored under
	 * @param data the data table describing the entry to create
	 * @param clazz the class of the entry to create. must be a subclass of the managed storage class.
	 * @return the entry created
	 */
	public default <S extends T> T store (String key, DataTable data, Class<S> clazz) {
		return put (key, build (data, clazz));
	}

	/**
	 * Update and store a storage entry from cucumbers Data table spec
	 *
	 * In case the entry does not exist a new one will be created instead.
	 * If an entry is found, it will be populated with the new data and stored again.
	 *
	 * @param key the name the entry is stored under
	 * @param data the data table describing the entry to populate
	 * @param clazz the class of the entry to create. must be a subclass of the managed storage class.
	 * @return the entry created
	 */
	public default <S extends T> T update (String key, DataTable data, Class<S> clazz) {
		try {
			T entry = clazz.cast(get(key));

			populate (clazz.cast(entry), data, clazz);

			return put (key, entry);
		} catch (RuntimeException e) {
			return store (key, data, clazz);
		}
	}

	/**
	 * Check an element against a cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param element the element to check
	 * @param data the data table describing the entry to check against
	 * @param clazz the class of the entry to check. must be a subclass of the managed storage class.
	 */
	public <S extends T> boolean check (S element, DataTable data, Class<S> clazz);

	/**
	 * Check a storage entry against a cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param key the name the entry will be stored under
	 * @param data the data table describing the entry to check against
	 * @param clazz the class of the entry to check. must be a subclass of the managed storage class.
	 */
	public default <S extends T> boolean check (String key, DataTable data, Class<S> clazz) {
		Object element = get (key);

		assertNotNull (element, "element with key " + key + "not found");
		assertTrue (clazz.isInstance(element), "element not an instance of " + clazz.getCanonicalName());

		return check (clazz.cast(element), data, clazz);
	}
}



