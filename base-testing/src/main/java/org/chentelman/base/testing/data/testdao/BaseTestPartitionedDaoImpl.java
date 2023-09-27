package org.chentelman.base.testing.data.testdao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.chentelman.base.module.core.data.BaseCommonDao;
import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.springframework.data.domain.Pageable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTestPartitionedDaoImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseTestDaoImpl<E, I> implements BaseTestPartitionedDao {
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

	public static final String NO_PARTITIONED_ACCESS_DAO = "does not allow partitioned access operations";
	public static final String NO_PARTITIONED_DELETE_DAO = "does not allow partitioned delete operations";

	private final Class<P> partitionType;

	public BaseTestPartitionedDaoImpl (Class<I> keyType, Class<E> dataType, Class<P> partitionType, BaseCommonDao<E, I> dao, BaseObjectService builderService) {
		super (keyType, dataType, dao, builderService);

		this.partitionType = partitionType;
	}

	protected final BasePartitionedAccessDao<E, I, P> getPartitionedAccessDao() {return castTestable (NO_PARTITIONED_ACCESS_DAO);}
	protected final BasePartitionedDeleteDao<E, I, P> getPartitionedDeleteDao() {return castTestable (NO_PARTITIONED_DELETE_DAO);}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findAllById (String id, boolean status) {
		try {
			I key = objectService.decode(id, keyType);
			List<E> objects = BaseUtilities.toList(getPartitionedAccessDao().findAllById(key));
			register (null, objects, true);
			assertTrue  (status, error(LOAD_ALL_BY_ID_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(LOAD_ALL_BY_ID_FAILURE));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findAllById(Pageable page, String id, boolean status) {
		try {
			I key = objectService.decode(id, keyType);
			List<E> objects = BaseUtilities.toList(getPartitionedAccessDao().findAllById(page, key));
			register (null, objects, true);
			assertTrue  (status, error(LOAD_ALL_BY_ID_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(LOAD_ALL_BY_ID_FAILURE));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findAll (String partitionKey, boolean status) {
		try {
			List<E> objects = BaseUtilities.toList(getPartitionedAccessDao().findAll(
				objectService.decode(partitionKey, partitionType)));
			register (null, objects, true);
			assertTrue  (status, error(PARTITION_LOAD_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(PARTITION_LOAD_FAILURE));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findAll (String partitionKey, Pageable pageable, boolean status) {
		try {
			List<E> objects = BaseUtilities.toList(getPartitionedAccessDao().findAll(
				pageable, objectService.decode(partitionKey, partitionType)));
			register (null, objects, true);
			assertTrue  (status, error(PARTITION_LOAD_SUCCESS));
		} catch (Exception e) {
			register (null, null, false);
			assertFalse (status, error(PARTITION_LOAD_FAILURE));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void count (String partitionKey, long expected) {
		long actual = getPartitionedAccessDao().count(
			objectService.decode(partitionKey, partitionType));

		assertEquals (expected, actual);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findById (String partitionKey, String id, boolean status) {
		Optional<E> found = getPartitionedAccessDao().findById (
			objectService.decode(id, keyType),
			objectService.decode(partitionKey, partitionType)
		);

		if (found.isPresent()) {
			register(found.get(), null, true);
			assertTrue  (status, error(String.format(PARTITION_ID_LOAD_SUCCESS, partitionKey, id)));
		} else {
			register(null, null, false);
			assertFalse (status, error(String.format(PARTITION_ID_LOAD_FAILURE, partitionKey, id)));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById (String partitionKey, String id, boolean status) {
		try {
			getPartitionedDeleteDao().deleteById (
				objectService.decode(id, keyType),
				objectService.decode(partitionKey, partitionType)
			);
			assertTrue  (status, error(String.format(PARTITION_AND_ID_DELETE_SUCCESS, partitionKey, id)));
		} catch (Exception e) {
			assertFalse (status, error(String.format(PARTITION_AND_ID_DELETE_FAILURE, partitionKey, id)));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByPartitionKey (String partitionKey, boolean status) {
		try {
			getPartitionedDeleteDao().deleteByPartitionKey(objectService.decode(partitionKey, partitionType));
			assertTrue  (status, error(String.format(PARTITION_DELETE_SUCCESS, partitionKey)));
		} catch (Exception e) {
			assertFalse (status, error(String.format(PARTITION_DELETE_FAILURE, partitionKey)));
		}
	}

	// Utilities

	protected final Map<P, Map<I, E>> getPartitionedMap() {return castTestable ("not a map");}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAll() {
		if (!super.deleteAll()) {
			try {
				getPartitionedMap().clear();
				return true;
			} catch (Exception e) {
				// ignore error
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean initialize (E data) {
		try {
			Map<P, Map<I, E>> map = getPartitionedMap();

			I id = data.getId();
			P pk = data.getPartition();

			if (Objects.nonNull(id) && Objects.nonNull(pk)) {
				Map<I, E> partition = map.get(pk);

				if (Objects.isNull(partition)) {
					partition = new HashMap<>();
					map.put (pk, partition);
				}

				partition.put (id, data);
			}

			return true;
		} catch (Exception e) {
			// ignore error
		}

		return super.initialize(data);
	}
}



