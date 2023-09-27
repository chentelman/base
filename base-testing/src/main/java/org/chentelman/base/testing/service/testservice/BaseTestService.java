package org.chentelman.base.testing.service.testservice;

import org.chentelman.base.testing.tester.BaseTester;
import org.springframework.data.domain.Pageable;

import io.cucumber.datatable.DataTable;

public interface BaseTestService extends BaseTester {
	// create interface
	public void create (boolean status, DataTable data);

	// access interface
	public void readAll    (boolean status);
	public void readAll    (boolean status, Pageable page);
	public void count      (boolean status, long expected);
	public void read       (boolean status, String id);
	public void readLatest (boolean status);

	// update interface
	public void update       (boolean status, DataTable data, String id);
	public void updateLatest (boolean status, DataTable data);

	// delete interface
	public void deleteById   (boolean status, String id);
	public void deleteLatest (boolean status);
}



