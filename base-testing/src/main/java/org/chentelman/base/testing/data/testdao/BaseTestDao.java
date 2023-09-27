package org.chentelman.base.testing.data.testdao;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.testing.tester.BaseTester;
import org.springframework.data.domain.Pageable;

import io.cucumber.datatable.DataTable;

/**
 * The test counterpart of {@link BaseCreateDao}, {@link BaseAccessDao}, {@link BaseUpdateDao}, {@link BaseDeleteDao}
 *
 * Performs the execution of the method calls and asserts the results
 */
public interface BaseTestDao extends BaseTester {
	// create interface
	public void add (DataTable data, boolean status);

	// access interface
	public void findAll    (boolean status);
	public void findAll    (Pageable page, boolean status);
	public void count      (long expected);
	public void findById   (String id, boolean status);
	public void findLatest (boolean status);

	// update interface
	public void update       (DataTable data, boolean status);
	public void updateLatest (DataTable data, boolean status);

	// delete interface
	public void deleteById   (String id, boolean status);
	public void deleteLatest (boolean status);

	// utilities
	public boolean deleteAll  ();
	public boolean initialize (DataTable data);
}



