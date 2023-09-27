package org.chentelman.base.testing.objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.chentelman.base.testing.service.BaseStorageBuilder;

import io.cucumber.datatable.DataTable;

/**
 * A base storage implementation for plain objects.
 *
 * Objects managed by this service are indented to be simple POJOs
 * with no other functionalities other than storing various information.
 */
public interface BaseObjectService extends BaseStorageBuilder<Object> {

	/**
	 * Set a custom object builder for the class.
	 *
	 * No checks are performed that the object builder is a valid
	 * builder for the defined class and will be assumed as correct
	 *
	 * The class canonical name will be used as key
	 *
	 * @param clazz the type of the class this object builder is designed for
	 * @param builder the builder able to build that class.
	 */
	public BaseObjectBuilder setBuilder (Class<?> clazz, BaseObjectBuilder builder);

	/**
	 * Set a custom object builder for the class.
	 *
	 * The class will be registered under a convenient name to be used
	 * instead of the class type in the build and store methods
	 *
	 * @param key the key this builder will be registered as
	 * @param builder the builder able to build that class.
	 */
	public BaseObjectBuilder setBuilder (String key, BaseObjectBuilder builder);

	/**
	 * Return an object builder capable of building this object
	 *
	 * If a builder is not registered already a new default implementation
	 * of {@link BaseObjectBuilderImpl} will be instantiated for this class
	 * to handle the build.
	 *
	 * In case a specialised builder is required, this can be provided via
	 * the setBuilder method.
	 *
	 * @param <E>
	 * @param clazz
	 * @return an {@link BaseObjectBuilder} capable of building class E
	 */
	public <E> BaseObjectBuilder getBuilder (Class<E> clazz);

	/**
	 * Return an object builder capable of building this object
	 *
	 * This method requires the builder to be registered already.
	 *
	 * @param key the key the builder is registered as
	 * @return an {@link BaseObjectBuilder} associated with this key
	 */
	public BaseObjectBuilder getBuilder (String key);

	/**
	 * Generate an empty object for the managed type
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param type the type of object to created, an object builder for this type must be registered before hand
	 * @return the entry created
	 */
	public Object build (String type);

	/**
	 * Generate an object from cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param data the data table describing the object to create
	 * @param type the type of object to created, an object builder for this type must be registered before hand
	 * @return the entry created
	 */
	public Object build (DataTable data, String type);

	/**
	 * Populate a storage entry from cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param object the data entry to populate
	 * @param data the data table describing the entry to create
	 * @param type the type of object to created, an object builder for this type must be registered before hand
	 */
	public void populate (Object object, DataTable data, String type);

	/**
	 * Generate and store a storage entry from cucumbers Data table spec
	 *
	 * The generated entry will be stored under the key value.
	 * In case a previous entry already exists under this key
	 * the entry will be overridden and the previous entry will
	 * be returned.
	 *
	 * @param key the name the entry will be stored under
	 * @param data the data table describing the object to create
	 * @param type the type of object to created, an object builder for this type must be registered before hand
	 * @return the entry created
	 */
	public default Object store (String key, DataTable data, String type) {
		return put (key, build (data, type));
	}

	/**
	 * Check an object against a cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param key the name the entry to check
	 * @param data the data table describing the entry to check against
	 * @param type the type of the entry to check, an object builder for this type must be registered before hand
	 */
	public boolean check (Object object, DataTable data, String type);

	/**
	 * Check a storage entry against a cucumbers Data table spec
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param key the name the entry to check
	 * @param data the data table describing the entry to check against
	 * @param type the type of the entry to check, an object builder for this type must be registered before hand
	 */
	public default boolean check (String key, DataTable data, String type) {
		Object object = get (key);

		assertNotNull (object, "object with key " + key + "not found");

		return check (object, data, type);
	}

	/**
	 * Decode a string value to an object
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param data the data table describing the object to create
	 * @param type the type of object to created
	 * @return the decoded object
	 */
	public Object decode (String data, String type);

	/**
	 * Decode a string value to an object
	 *
	 * This method does not alter the storage it self.
	 *
	 * @param data the data table describing the object to create
	 * @param type the type of object to created
	 * @return the decoded object
	 */
	public <D> D decode (String data, Class<D> type);
}



