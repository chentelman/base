package org.chentelman.base.testing.service;

import java.io.Serializable;
import java.util.Map;

/**
 * Base storage interface.
 *
 * Base storage is essentially map implementation with a string typed key.
 * This will be used to store a parameterised list of items using the default
 * map interface
 *
 * @param <T> the type of stored elements in the storage
 */
public interface BaseStorage<T> extends Map<String, T>, Serializable {

}



