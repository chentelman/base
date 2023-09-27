package org.chentelman.base.testing.utilities;

import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.DataTableTypeRegistry;
import io.cucumber.datatable.DataTableTypeRegistryTableConverter;

/**
 * Base test utilities to provide base test methods to test components
 */
public class BaseTestUtilities {

	private BaseTestUtilities () {
		// cannot instantiate
	}

	/**
	 * Base test method to build a data table.
	 *
	 * @param data in the for of a list of lists
	 * @return the build data table
	 */
	public static DataTable buildDataTable (List<List<String>> data) {
		return DataTable.create(data,
			new DataTableTypeRegistryTableConverter (
				new DataTableTypeRegistry (Locale.ENGLISH)
			)
		);
	}

	/**
	 * Base test method to convert an {@link HttpStatusCode} to {@link HttpStatus}
	 *
	 * @param data the status code
	 * @return the status as {@link HttpStatus}
	 */
	public static HttpStatus toHttpStatus(HttpStatusCode statusCode) {
		try {
			return HttpStatus.class.cast(statusCode);
		} catch (ClassCastException e) {
			return HttpStatus.valueOf(statusCode.value());
		}
	}

}



