package org.chentelman.base.testing.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.cucumber.datatable.DataTable;

/**
 * Test for the test utilities functionality
 */
class BaseTestUtilitiesTest {

	@Test
	void testBuildDataTable () {
		DataTable dataTable = BaseTestUtilities.buildDataTable(List.of (
			List.of ("id1", "value1"),
			List.of ("id2", "value2"),
			List.of ("id3", "value3"),
			List.of ("id4", "value4")
		));

		assertNotNull (dataTable, "data table was not created");
		assertEquals ("id1", dataTable.cell(0, 0));
		assertEquals ("id2", dataTable.cell(1, 0));
		assertEquals ("id3", dataTable.cell(2, 0));
		assertEquals ("id4", dataTable.cell(3, 0));

		assertEquals ("value1", dataTable.cell(0, 1));
		assertEquals ("value2", dataTable.cell(1, 1));
		assertEquals ("value3", dataTable.cell(2, 1));
		assertEquals ("value4", dataTable.cell(3, 1));

		assertThrows (IndexOutOfBoundsException.class, () -> dataTable.cell(4, 0));
		assertThrows (IndexOutOfBoundsException.class, () -> dataTable.cell(4, 1));
		assertThrows (IndexOutOfBoundsException.class, () -> dataTable.cell(0, 2));
		assertThrows (IndexOutOfBoundsException.class, () -> dataTable.cell(1, 2));
		assertThrows (IndexOutOfBoundsException.class, () -> dataTable.cell(2, 2));
		assertThrows (IndexOutOfBoundsException.class, () -> dataTable.cell(3, 2));
		assertThrows (IndexOutOfBoundsException.class, () -> dataTable.cell(4, 2));
	}
}



