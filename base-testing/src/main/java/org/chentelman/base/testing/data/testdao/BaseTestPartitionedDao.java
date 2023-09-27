package org.chentelman.base.testing.data.testdao;

import org.chentelman.base.module.core.data.BasePartitionedAccessDao;
import org.chentelman.base.module.core.data.BasePartitionedDeleteDao;
import org.springframework.data.domain.Pageable;

/**
 * The test counterpart of {@link BasePartitionedAccessDao}, {@link BasePartitionedDeleteDao}
 *
 * Performs the execution of the method calls and asserts the results
 */
public interface BaseTestPartitionedDao extends BaseTestDao {

	// partitioned access interface
	public void findAllById (String id, boolean status);
	public void findAllById (Pageable page, String id, boolean status);

	public void findAll     (String partitionKey, boolean status);
	public void findAll     (String partitionKey, Pageable pageable, boolean status);
	public void count       (String partitionKey, long expected);
	public void findById    (String partitionKey, String id, boolean status);

	// partitioned delete interface
	public void deleteById           (String partitionKey, String id, boolean status);
	public void deleteByPartitionKey (String partitionKey, boolean status);
}



