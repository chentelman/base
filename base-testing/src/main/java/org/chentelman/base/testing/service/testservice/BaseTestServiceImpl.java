package org.chentelman.base.testing.service.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.service.BaseAccessService;
import org.chentelman.base.module.core.service.BaseCommonService;
import org.chentelman.base.module.core.service.BaseCreateService;
import org.chentelman.base.module.core.service.BaseDeleteService;
import org.chentelman.base.module.core.service.BaseUpdateService;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.tester.BaseTesterImpl;
import org.springframework.data.domain.Pageable;

import io.cucumber.datatable.DataTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseTestServiceImpl<E extends BaseEntity<I>, I> extends BaseTesterImpl<E, I, BaseCommonService<E, I>> implements BaseTestService {

	public BaseTestServiceImpl (Class<I> keyType, Class<E> dataType, BaseCommonService<E, I> service, BaseObjectService builderService) {
		super (keyType, dataType, service, builderService);
	}

	protected final BaseCreateService<E, I> getCreateService() {return castTestable (NO_CREATE_TESTABLE);}
	protected final BaseAccessService<E, I> getAccessService() {return castTestable (NO_ACCESS_TESTABLE);}
	protected final BaseUpdateService<E, I> getUpdateService() {return castTestable (NO_UPDATE_TESTABLE);}
	protected final BaseDeleteService<E, I> getDeleteService() {return castTestable (NO_DELETE_TESTABLE);}

	// create interface

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(boolean status, DataTable data) {
		String error;

		try {
			E toCreate = objectService.build(data, dataType);
			assertNotNull (toCreate, error(INSTANTIATE_FAILED));

			getCreateService().create(toCreate);

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
	public void readAll(boolean status) {
		try {
			List<E> objects = BaseUtilities.toList(getAccessService().readAll());
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
	public void readAll(boolean status, Pageable page) {
		try {
			List<E> objects = BaseUtilities.toList(getAccessService().readAll(page));
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
	public void count(boolean status, long expected) {
		long actual = getAccessService().count();

		assertEquals (expected, actual);
	}

	/**
	 * generic method to read a single item
	 *
	 * @param id the id of the entity to look for
	 * @param status whether the method is supposed to succeed or not
	 */
	private void read (I id, boolean status) {
		E found = null;
		try {
			found = getAccessService().read(id);
		} catch (Exception e) {
			// ignore error
		}

		if (found != null) {
			register(found, null, true);
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
	public void read(boolean status, String id) {
		read (objectService.decode(id, keyType), status);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readLatest(boolean status) {
		assertNotNull (id, error(LATEST_NOT_FOUND));

		read (id, status);
	}

	// update interface

	/**
	 * generic method to update a single item
	 *
	 * @param id the id of the entity to look for
	 * @param data the data to update to the entity
	 * @param status whether the method is supposed to succeed or not
	 */
	private void update (I id, E updatable, boolean status) {
		try {
			getUpdateService().update(id, updatable);
			assertTrue  (status, error(String.format(UPDATE_SUCCESS, id)));
		} catch (Exception e) {
			assertFalse (status, error(String.format(UPDATE_FAILURE, id)));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(boolean status, DataTable data, String id) {
		try {
			update (objectService.decode(id, keyType), objectService.build(data, dataType), status);
		} catch (AssertionError e) {
			assertFalse (status, error(String.format(UPDATE_FAILURE, e.getMessage())));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLatest(boolean status, DataTable data) {
		assertNotNull (entity, error(LATEST_NOT_FOUND));

		try {
			update (id, objectService.build(data, dataType), status);
		} catch (AssertionError e) {
			assertFalse (status, error(String.format(UPDATE_FAILURE, e.getMessage())));
		}
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
			getDeleteService().delete(id);
			assertTrue  (status, error(String.format(DELETE_SUCCESS, id.toString())));
		} catch (Exception e) {
			assertFalse (status, error(String.format(DELETE_FAILURE, id.toString())));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(boolean status, String id) {
		delete (objectService.decode(id, keyType), status);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteLatest(boolean status) {
		assertNotNull (id, error(LATEST_NOT_FOUND));

		delete (id, status);
	}
}



