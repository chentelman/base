package org.chentelman.base.testing.service.testservice;

import org.chentelman.base.module.core.service.BasePartitionedAccessService;
import org.chentelman.base.module.core.service.BasePartitionedDeleteService;
import org.chentelman.base.module.core.service.BasePartitionedUpdateService;
import org.springframework.data.domain.Pageable;

import io.cucumber.datatable.DataTable;

/**
 * The test counterpart of {@link BasePartitionedAccessService}, {@link BasePartitionedUpdateService}, {@link BasePartitionedDeleteService}
 *
 * Performs the execution of the method calls and asserts the results
 */
public interface BaseTestPartitionedService extends BaseTestService {

	// partitioned access interface
	public void readAllById (boolean status, String id);
	public void readAllById (boolean status, String id, Pageable page);

	public void readAll (boolean status, String partitionKey);
	public void readAll (boolean status, String partitionKey, Pageable pageable);
	public void count   (String partitionKey, long expected);
	public void read    (boolean status, String partitionKey, String id);

	// partitioned update interface
	public void update (boolean status, DataTable data, String partitionKey, String id);

	// partitioned delete interface
	public void delete    (boolean status, String partitionKey, String id);
	public void deleteAll (boolean status, String partitionKey);
}



