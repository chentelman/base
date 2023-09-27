package org.chentelman.base.testing.tester;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.testing.component.BaseTestComponent;
import org.chentelman.base.testing.exception.BaseTestException;
import org.chentelman.base.testing.objects.BaseObjectService;

import io.cucumber.datatable.DataTable;

public class BaseTesterImpl<E extends BaseEntity<I>, I, T> extends BaseTestComponent implements BaseTester {
	public static final String GENERIC_SUCCESS = "%s entity with %s was successful";
	public static final String GENERIC_FAILURE = "Unable to %s entity with %s";

	public static final String ENTITY_ID = "id %s";

	public static final String CREATE_SUCCESS     = String.format(GENERIC_SUCCESS, "Creating",  ENTITY_ID);
	public static final String CREATE_FAILURE     = String.format(GENERIC_FAILURE, "create",    ENTITY_ID);
	public static final String LOAD_SUCCESS       = String.format(GENERIC_SUCCESS, "Loading",   ENTITY_ID);
	public static final String LOAD_FAILURE       = String.format(GENERIC_FAILURE, "load",      ENTITY_ID);
	public static final String UPDATE_SUCCESS     = String.format(GENERIC_SUCCESS, "Updating",  ENTITY_ID);
	public static final String UPDATE_FAILURE     = String.format(GENERIC_FAILURE, "update",    ENTITY_ID);
	public static final String DELETE_SUCCESS     = String.format(GENERIC_SUCCESS, "Deleting",  ENTITY_ID);
	public static final String DELETE_FAILURE     = String.format(GENERIC_FAILURE, "delete",    ENTITY_ID);

	public static final String ERROR_MESSAGE      = "Tester %s %s";
	public static final String TESTABLE_IS_NULL   = "does not provide a testable";
	public static final String NO_CREATE_TESTABLE = "does not allow create operations";
	public static final String NO_ACCESS_TESTABLE = "does not allow access operations";
	public static final String NO_UPDATE_TESTABLE = "does not allow update operations";
	public static final String NO_DELETE_TESTABLE = "does not allow delete operations";
	public static final String INSTANTIATE_FAILED = "Failed to instantiate entity";
	public static final String LATEST_NOT_FOUND   = "Latest not found";
	public static final String LIST_NOT_FOUND     = "Entities not loaded";
	public static final String LOAD_ALL_SUCCESS   = "Loading all entities was sucessful";
	public static final String LOAD_ALL_FAILURE   = "Unable to load all entities";

	protected final T testable;
	protected final Class<I> keyType;
	protected final Class<E> dataType;

	protected I id;
	protected E entity;
	protected List<E> list;
	protected boolean success;

	protected final BaseObjectService objectService;

	public BaseTesterImpl (Class<I> keyType, Class<E> dataType, T testable, BaseObjectService builderService) {
		this.testable = testable;
		this.keyType  = keyType;
		this.dataType = dataType;

		clear ();

		this.objectService = builderService;
	}

	/**
	 * Internal method to generate a supplier for an error message
	 *
	 * @param message the error message
	 * @return a supplier to generate the error message if needed
	 */
	protected final Supplier<String> error(String message) {
		return () -> String.format(ERROR_MESSAGE, getClass().getCanonicalName(), message);
	}

	/**
	 * Default implementation of getName is utilising the simple name of the entity
	 *
	 * @return the class name for the base entity under test
	 */
	@Override
	public String getName () {
		return dataType.getSimpleName();
	}

	/**
	 * Function to case testable to a certain type
	 * Failures will be raised in the following cases
	 *  1. testable is not provided in the first place
	 *  2. testable is not able to be cast to the desired type
	 *
	 * @return the testable casted to the requested type
	 */
	@SuppressWarnings("unchecked")
	protected final <S extends T> S castTestable (String failure) {
		assertNotNull(testable, error(TESTABLE_IS_NULL));
		try {
			return (S) testable;
		} catch (ClassCastException e) {
			// do nothing
		}

		fail(error(failure));
		throw new BaseTestException(failure);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void clear () {
		this.id      = null;
		this.entity  = null;
		this.list    = null;
		this.success = false;
	}

	/**
	 * register the results of the operations of this class
	 *
	 * Only the results of read and update operations are registered
	 *
	 * @param entity the entity loaded if available
	 * @param list the list of entities loaded if available
	 * @param status whether the operation was successful or not
	 */
	protected final void register (E entity, List<E> list, boolean status) {
		this.id      = Objects.nonNull(entity) ? entity.getId() : null;
		this.entity  = entity;
		this.list    = list;
		this.success = status;
	}

	// Assertions

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean latestExists() {
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean dataMatches (DataTable data) {
		assertNotNull (entity);

		return objectService.check(entity, data, dataType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean arrayMatches(int index, DataTable data) {
		assertNotNull (list);
		assertTrue (list.size() > index);
		assertNotNull (list.get(index));

		return objectService.check(list.get(index), data, dataType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean arrayCount (int count) {
		assertNotNull (list, LIST_NOT_FOUND);

		return list.size() == count;
	}

	/**
	 * check if the object described by the data table exists in the array results
	 */
	@Override
	public final boolean arrayContains(DataTable data) {
		assertNotNull (list, LIST_NOT_FOUND);

		for (E item : list) {
			if (objectService.check(item, data, dataType)) {
				return true;
			}
		}
		return false;
	}
}



