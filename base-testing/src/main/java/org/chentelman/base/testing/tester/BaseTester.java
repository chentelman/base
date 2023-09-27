package org.chentelman.base.testing.tester;

import org.chentelman.base.testing.service.BaseListable;

import io.cucumber.datatable.DataTable;

public interface BaseTester extends BaseListable {

	// assertions
	public boolean latestExists  ();
	public boolean dataMatches   (DataTable data);
	public boolean arrayCount    (int count);
	public boolean arrayMatches  (int index, DataTable data);
	public boolean arrayContains (DataTable data);
}



