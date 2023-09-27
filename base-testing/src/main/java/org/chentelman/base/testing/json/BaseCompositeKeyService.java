package org.chentelman.base.testing.json;

import org.chentelman.base.testing.service.BaseStorage;

import io.cucumber.datatable.DataTable;

public interface BaseCompositeKeyService extends BaseStorage<String> {

	/**
	 * Encode the data as a base 64 string
	 *
	 * Internal storage is not affected by this method
	 *
	 * @param data to encode
	 * @return the encoded data
	 */
	public String generateBase64 (String data);

	/**
	 * Encode the data as a base 64 string.
	 *
	 * The data are padded with just enough spaces,
	 * for the generated value to not contain any = characters
	 *
	 * Internal storage is not affected by this method
	 *
	 * @param data to encode
	 * @return the encoded data
	 */
	public String generatePaddedBase64 (String data);

	/**
	 * Convert the data table into a string value
	 *
	 * Internal storage is not affected by this method
	 *
	 * @param data the data table to encode into a string value
	 * @return the generated string value
	 */
	public String build (DataTable data);

	/**
	 * Convert and store a data table into a string value
	 *
	 * @param name the key the string is going to be stored under
	 * @param data the data table to encode into a string value
	 * @return the generated string value
	 */
	public default String put (String name, DataTable data) {
		return put(name, build(data));
	}
}



