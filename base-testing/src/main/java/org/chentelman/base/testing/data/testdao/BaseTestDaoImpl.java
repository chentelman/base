package org.chentelman.base.testing.data.testdao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCommonDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.tester.BaseTesterImpl;
import org.springframework.data.domain.Pageable;

import io.cucumber.datatable.DataTable;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseTestDaoImpl<E extends BaseEntity<I>, I> extends BaseTesterImpl<E, I, BaseCommonDao<E, I>> implements BaseTestDao {
	public static final String DATA_TYPE_IS_NULL  = "does not provide a data type";
	public static final String NO_TABLE_INFO      = "entity does not provide a table annotation";
	public static final String NO_TABLE_NAME      = "entity table annotation does not specify a name";

	public BaseTestDaoImpl (Class<I> keyType, Class<E> dataType, BaseCommonDao<E, I> dao, BaseObjectService builderService) {
		super (keyType, dataType, dao, builderService);
	}

	protected final BaseCreateDao<E, I> getCreateDao() {return castTestable (NO_CREATE_TESTABLE);}
	protected final BaseAccessDao<E, I> getAccessDao() {return castTestable (NO_ACCESS_TESTABLE);}
	protected final BaseUpdateDao<E, I> getUpdateDao() {return castTestable (NO_UPDATE_TESTABLE);}
	protected final BaseDeleteDao<E, I> getDeleteDao() {return castTestable (NO_DELETE_TESTABLE);}

	/**
	 * Default implementation of getName is utilising the {@link Table} annotation,
	 * of the entity retrieved via {@see BaseDaoTester#getName()}
	 *
	 * @return the table name for the base entity under test
	 */
	@Override
	public String getName () {
		assertNotNull (dataType, error(DATA_TYPE_IS_NULL));

		Table table = dataType.getAnnotation(Table.class);

		assertNotNull (table, error(NO_TABLE_INFO));

		String name = table.name();

		assertNotNull (name, error(NO_TABLE_INFO));

		return name;
	}

	// create interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add (DataTable data, boolean status) {
		String error;

		try {
			E toCreate = objectService.build(data, dataType);
			assertNotNull (toCreate, error(INSTANTIATE_FAILED));

			getCreateDao().add(toCreate);

			register(toCreate, null, true);
			error = "Creating the entity was successful";
		} catch (Exception | AssertionError  e) {
			register(null, null, false);
			error = e.getMessage();
		}

		assertEquals(status, success, error(error));
	}

	// access interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findAll (boolean status) {
		try {
			List<E> objects = BaseUtilities.toList(getAccessDao().findAll());
			register (null, objects, true);
			assertTrue  (status, error(LOAD_ALL_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(LOAD_ALL_FAILURE));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findAll (Pageable page, boolean status) {
		try {
			List<E> objects = BaseUtilities.toList(getAccessDao().findAll(page));
			register (null, objects, true);
			assertTrue  (status, error(LOAD_ALL_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(LOAD_ALL_FAILURE));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void count (long expected) {
		long actual = getAccessDao().count();

		assertEquals (expected, actual);
	}

	/**
	 * generic method to find a single item
	 *
	 * @param id the id of the entity to look for
	 * @param status whether the method is supposed to succeed or not
	 */
	private void find (I id, boolean status) {
		Optional<E> found = getAccessDao().findById(id);

		if (found.isPresent()) {
			register(found.get(), null, true);
			assertTrue  (status, error(String.format(LOAD_SUCCESS, id.toString())));
		} else {
			register(null, null, false);
			assertFalse (status, error(String.format(LOAD_FAILURE, id.toString())));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findById (String id, boolean status) {
		find (objectService.decode(id, keyType), status);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findLatest (boolean status) {
		assertNotNull (id, error(LATEST_NOT_FOUND));

		find (id, status);
	}

	// update interface

	/**
	 * generic method to update a single item
	 *
	 * @param id the id of the entity to look for
	 * @param data the data to update to the entity
	 * @param status whether the method is supposed to succeed or not
	 */
	private void update (E updatable, boolean status) {
		try {
			register(getUpdateDao().update(updatable), null, true);
			assertTrue  (status, error(String.format(UPDATE_SUCCESS, id)));
		} catch (Exception e) {
			register(null, null, false);
			assertFalse (status, error(String.format(UPDATE_FAILURE, id)));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update (DataTable data, boolean status) {
		try {
			update (objectService.build(data, dataType), status);
		} catch (AssertionError e) {
			assertFalse (status, error(String.format(UPDATE_FAILURE, "")));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLatest (DataTable data, boolean status) {
		assertNotNull (entity, error(LATEST_NOT_FOUND));

		try {
			objectService.populate(entity, data, dataType);
		} catch (AssertionError e) {
			assertFalse (status, error(String.format(UPDATE_FAILURE, id)));
		}

		update (entity, status);
	}

	// delete interface

	/**
	 * generic method to delete a single item
	 *
	 * @param id the id of the entity to look for
	 * @param status whether the method is supposed to succeed or not
	 */
	private void delete (I id, boolean status) {
		try {
			getDeleteDao().deleteById(id);
			assertTrue  (status, error(String.format(DELETE_SUCCESS, id.toString())));
		} catch (Exception e) {
			assertFalse (status, error(String.format(DELETE_FAILURE, id.toString())));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById (String id, boolean status) {
		delete (objectService.decode(id, keyType), status);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteLatest (boolean status) {
		assertNotNull (id, error(LATEST_NOT_FOUND));

		delete (id, status);
	}

	// Utilities

	protected final Map<I, E> getMap() {return castTestable ("not a map");}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAll() {
		// first try to insert the values through the delete and access dao interfaces
		try {
			getDeleteDao().delete (
				getAccessDao().findAll()
			);
			return true;
		} catch (Exception e) {
			// ignore error
		}

		// otherwise if the dao is a map, clear it
		try {
			getMap().clear();
			return true;
		} catch (Exception e) {
			// ignore error
		}

		return false;
	}

	/**
	 * build and persist an entity even if the dao does not support
	 * create operations.
	 */
	@Override
	public boolean initialize(DataTable data) {
		try {
			return initialize(objectService.build(data, dataType));
		} catch (AssertionError e) {
			logger.warn(e.getMessage());
			return false;
		}
	}

	/**
	 * build and persist an entity even if the dao does not support
	 * create operations.
	 */
	protected boolean initialize (E data) {

		// first try to insert the values through the create dao interface
		try {
			getCreateDao().add(data);
			return true;
		} catch (Exception e) {
			// ignore error
		}

		// otherwise if the dao is a map, clear it
		try {
			getMap().put(data.getId(), data);
			return true;
		} catch (Exception e) {
			// ignore error
		}

		return false;
	}
}



