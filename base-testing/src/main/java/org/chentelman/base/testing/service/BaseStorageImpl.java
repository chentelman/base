package org.chentelman.base.testing.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.chentelman.base.testing.component.BaseTestComponent;

import io.cucumber.datatable.DataTable;

/**
 * Base storage implementation.
 *
 * The base storage implementation only provides the map related interface by
 * extending the default hash map implementation.
 *
 * The implementation for building and storing object using the {@link DataTable} structure
 * is left to be implemented by sub classes.
 *
 * @param <T> the type of stored objects in the storage
 */
public abstract class BaseStorageImpl<T> extends BaseTestComponent implements BaseStorage<T> {
	private static final long serialVersionUID = 1L;

	private Map<String, T> storage = new HashMap<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return storage.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return storage.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsKey(Object key) {
		return storage.containsKey(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsValue(Object value) {
		return storage.containsValue(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(Object key) {
		return storage.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T put(String key, T value) {
		return storage.put(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T remove(Object key) {
		return storage.remove(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putAll(Map<? extends String, ? extends T> m) {
		storage.putAll(m);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		storage.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> keySet() {
		return storage.keySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<T> values() {
		return storage.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Entry<String, T>> entrySet() {
		return storage.entrySet();
	}
}



