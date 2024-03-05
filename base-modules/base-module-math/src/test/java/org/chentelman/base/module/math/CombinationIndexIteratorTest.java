package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for CombinationIterator class
 */
class CombinationIndexIteratorTest {

	/**
	 * instantiate an iterator and verify all the returned results against
	 * the provided expected values
	 *
	 * @param k number of items to pick
	 * @param n number of items to pick from
	 * @param results the expected results
	 */
	private void combinations (int k, int n, int[][] results) {
		CombinationIndexIterator iterator = new CombinationIndexIterator(k, n);
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
		combinations (3, 3, new int[][] {
			{0, 1, 2}
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
		combinations (2, 3, new int[][] {
			{0, 1},
			{0, 2},
			{1, 2}
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
		combinations (1, 3, new int[][] {
			{0},
			{1},
			{2}
		});
	}
}



