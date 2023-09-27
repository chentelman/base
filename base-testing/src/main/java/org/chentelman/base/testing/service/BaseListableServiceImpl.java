package org.chentelman.base.testing.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ListableBeanFactory;

/**
 * Default base listable service implementation
 *
 * @param <T> the base listable sub categorisation to be used
 *            during the discover process.
 */
public class BaseListableServiceImpl<T extends BaseListable> extends BaseDiscoveryService<T> implements BaseListableService<T> {
	// discovered listables
	protected Map<String, T> listables;

	// the latest listable
	protected T latestListable;

	/**
	 * object initialisation is set to protected to force a different class
	 * to be created for each different listable that needs to be discovered.
	 *
	 * @param listableBeanFactory autowired dependency to discover the required beans.
	 * @param listableClass the class to be used for the discovery.
	 */
	protected BaseListableServiceImpl (ListableBeanFactory listableBeanFactory, Class<T> listableClass) {
		super (listableBeanFactory, listableClass);

		this.listables      = new HashMap<> ();
		this.latestListable = null;
	}

	/**
	 * Method to register the listable
	 *
	 * beans with a duplicate name are prohibited and will cause the application to fail.
	 */
	@Override
	protected void register (T discoverable) {
		if (listables.containsKey(discoverable.getName())) {
			logger.error("dublicate discoverable with name {} found", discoverable.getName());
			logger.error(" new -> {}", discoverable.getClass().getName());
			logger.error(" old -> {}", listables.get(discoverable.getName()).getClass().getName());
			System.exit(1);
		}

		listables.put(discoverable.getName(), discoverable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getListable(String name) {
		// cannot retrieve a listable that has not been discovered
		assertTrue(listables.containsKey(name), () -> String.format("listable for %s is not found", name));

		// get the listable from the map
		T listable = listables.get(name);

		// if for some reason the listable is null fail this
		assertNotNull(listable, () -> String.format("listable for %s is null", name));

		// return the listable
		return listable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getLatestListable(String name) {
		// retrieve the listable normally and store it in the latest listable variable
		// this is implemented this way to allow the caller to decide if the latest listable
		// should be update (using getLatestListable) or keep the previous one (using
		// getListable)
		latestListable = getListable(name);

		// return it
		return latestListable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getLatestListable() {
		assertNotNull(latestListable, () ->
			String.format("latest listable for %s is null",
				super.listableClass.getSimpleName()));

		return latestListable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		for (T listable : listables.values()) {
			listable.clear();
		}
		this.latestListable = null;
	}
}



