package org.chentelman.base.testing.service.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.service.BasePartitionedAccessService;
import org.chentelman.base.module.core.service.BasePartitionedDeleteService;
import org.chentelman.base.module.core.service.BasePartitionedUpdateService;
import org.chentelman.base.module.core.service.BaseService;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.data.domain.Pageable;

import io.cucumber.datatable.DataTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTestPartitionedServiceImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseTestServiceImpl<E, I> implements BaseTestPartitionedService {
	public static final String ENTITY_PARTITION = "partition %s";
	public static final String ENTITY_PARTITION_AND_ID = "partition %s and id %s";

	public static final String LOAD_ALL_BY_ID_SUCCESS = String.format(GENERIC_SUCCESS, "Loading all", ENTITY_ID);
	public static final String LOAD_ALL_BY_ID_FAILURE = String.format(GENERIC_FAILURE, "load all",    ENTITY_ID);

	public static final String PARTITION_LOAD_SUCCESS = String.format(GENERIC_SUCCESS, "Loading", ENTITY_PARTITION);
	public static final String PARTITION_LOAD_FAILURE = String.format(GENERIC_FAILURE, "load",    ENTITY_PARTITION);

	public static final String PARTITION_ID_LOAD_SUCCESS = String.format(GENERIC_SUCCESS, "Loading", ENTITY_PARTITION_AND_ID);
	public static final String PARTITION_ID_LOAD_FAILURE = String.format(GENERIC_FAILURE, "load",    ENTITY_PARTITION_AND_ID);

	public static final String PARTITION_DELETE_SUCCESS = String.format(GENERIC_SUCCESS, "Deleting", ENTITY_PARTITION);
	public static final String PARTITION_DELETE_FAILURE = String.format(GENERIC_FAILURE, "delete"  , ENTITY_PARTITION);

	public static final String PARTITION_AND_ID_DELETE_SUCCESS = String.format(GENERIC_SUCCESS, "Deleting", ENTITY_PARTITION_AND_ID);
	public static final String PARTITION_AND_ID_DELETE_FAILURE = String.format(GENERIC_FAILURE, "delete"  , ENTITY_PARTITION_AND_ID);

	public static final String NO_PARTITIONED_ACCESS_SERVICE = "does not allow partitioned access operations";
	public static final String NO_PARTITIONED_UPDATE_SERVICE = "does not allow partitioned update operations";
	public static final String NO_PARTITIONED_DELETE_SERVICE = "does not allow partitioned delete operations";

	private final Class<P> partitionType;

	public BaseTestPartitionedServiceImpl (Class<I> keyType, Class<E> dataType, Class<P> partitionType, BaseService<E, I> service, BaseObjectService builderService) {
		super (keyType, dataType, service, builderService);

		this.partitionType = partitionType;
	}

	protected final BasePartitionedAccessService<E, I, P> getPartitionedAccessService() {return castTestable (NO_PARTITIONED_ACCESS_SERVICE);}
	protected final BasePartitionedUpdateService<E, I, P> getPartitionedUpdateService() {return castTestable (NO_PARTITIONED_UPDATE_SERVICE);}
	protected final BasePartitionedDeleteService<E, I, P> getPartitionedDeleteService() {return castTestable (NO_PARTITIONED_DELETE_SERVICE);}

	// partitioned access interface

	@Override
	public void readAllById(boolean status, String id) {
		try {
			I key = objectService.decode(id, keyType);
			List<E> objects = BaseUtilities.toList(getPartitionedAccessService().readAllById(key));
			register (null, objects, true);
			assertTrue  (status, error(LOAD_ALL_BY_ID_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(LOAD_ALL_BY_ID_FAILURE));
		}
	}

	@Override
	public void readAllById(boolean status, String id, Pageable page) {
		try {
			I key = objectService.decode(id, keyType);
			List<E> objects = BaseUtilities.toList(getPartitionedAccessService().readAllById(page, key));
			register (null, objects, true);
			assertTrue  (status, error(LOAD_ALL_BY_ID_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(LOAD_ALL_BY_ID_FAILURE));
		}
	}

	@Override
	public void readAll(boolean status, String partitionKey) {
		try {
			List<E> objects = BaseUtilities.toList(getPartitionedAccessService().readAll(
				objectService.decode(partitionKey, partitionType)));
			register (null, objects, true);
			assertTrue  (status, error(PARTITION_LOAD_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(PARTITION_LOAD_FAILURE));
		}
	}

	@Override
	public void readAll(boolean status, String partitionKey, Pageable pageable) {
		try {
			List<E> objects = BaseUtilities.toList(getPartitionedAccessService().readAll(
				pageable, objectService.decode(partitionKey, partitionType)));
			register (null, objects, true);
			assertTrue  (status, error(PARTITION_LOAD_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(PARTITION_LOAD_FAILURE));
		}
	}

	@Override
	public void count(String partitionKey, long expected) {
		long actual = getPartitionedAccessService().count(
			objectService.decode(partitionKey, partitionType));

		assertEquals (expected, actual);
	}

	@Override
	public void read(boolean status, String partitionKey, String id) {
		E found = null;

		try {
			found = getPartitionedAccessService().read (
				objectService.decode(id, keyType),
				objectService.decode(partitionKey, partitionType)
			);
		} catch (Exception e) {
			// ignore error
		}

		if (found != null) {
			register(found, null, true);
			assertTrue  (status, error(String.format(PARTITION_ID_LOAD_SUCCESS, partitionKey, id)));
		} else {
			register(null, null, false);
			assertFalse (status, error(String.format(PARTITION_ID_LOAD_FAILURE, partitionKey, id)));
		}
	}

	// partitioned update interface

	@Override
	public void update(boolean status, DataTable data, String partitionKey, String id) {
		try {
			getPartitionedUpdateService().update (
				objectService.decode(id, keyType),
				objectService.decode(partitionKey, partitionType),
				objectService.build(data, dataType)
			);
			assertTrue  (status, error(String.format(PARTITION_AND_ID_DELETE_SUCCESS, partitionKey, id)));
		} catch (Exception e) {
			assertFalse (status, error(String.format(PARTITION_AND_ID_DELETE_FAILURE, partitionKey, id)));
		}
	}

	// partitioned delete interface

	@Override
	public void delete(boolean status, String partitionKey, String id) {
		try {
			getPartitionedDeleteService().delete (
				objectService.decode(id, keyType),
				objectService.decode(partitionKey, partitionType)
			);
			assertTrue  (status, error(String.format(PARTITION_AND_ID_DELETE_SUCCESS, partitionKey, id)));
		} catch (Exception e) {
			assertFalse (status, error(String.format(PARTITION_AND_ID_DELETE_FAILURE, partitionKey, id)));
		}
	}

	@Override
	public void deleteAll(boolean status, String partitionKey) {
		try {
			getPartitionedDeleteService().deleteAll(objectService.decode(partitionKey, partitionType));
			assertTrue  (status, error(String.format(PARTITION_DELETE_SUCCESS, partitionKey)));
		} catch (Exception e) {
			assertFalse (status, error(String.format(PARTITION_DELETE_FAILURE, partitionKey)));
		}
	}

}



