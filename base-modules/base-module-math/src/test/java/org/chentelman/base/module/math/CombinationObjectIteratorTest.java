package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for CombinationValueIterator class
 */
class CombinationObjectIteratorTest {
	private String[] items = new String[] {"Dog", "Cat", "Bat"};

	/**
	 * instantiate an iterator with a list of default items
	 *
	 * @param k number of items to pick
	 * @param results the expected results
	 */
	private void combinations (int k, String[][] results) {
		CombinationObjectIterator<String> iterator = new CombinationObjectIterator<>(k, items, String.class);
		int i = 0;

		while (iterator.hasNext()) {
			assertTrue(i < results.length, String.format("iterator returned more than %d results", results.length));
			assertArrayEquals(results[i], iterator.next());
			i += 1;
		}

		assertEquals(i, results.length);
	}

	/**
	 * Only one possible combinations exist
	 *  - [0,1,2]
	 */
	@Test
	void combinations_for_3_out_of_3 () {
		combinations (3, new String[][] {
			{"Dog", "Cat", "Bat"}
		});
	}
	/**
	 * Three possible combinations exist
	 *  - [0,1]
	 *  - [0,2]
	 *  - [1,2]
	 */
	@Test
	void combinations_for_2_out_of_3 () {
		combinations (2, new String[][] {
			{"Dog", "Cat"},
			{"Dog", "Bat"},
			{"Cat", "Bat"}
		});
	}

	/**
	 * Three possible combinations exist
	 *  - [0]
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void combinations_for_1_out_of_3 () {
		combinations (1, new String[][] {
			{"Dog"},
			{"Cat"},
			{"Bat"}
		});
	}
}



