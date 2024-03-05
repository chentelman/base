package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for RepetitionCombinationIterator class
 */
class RepetitionCombinationIndexIteratorTest {

	/**
	 * instantiate an iterator and verify all the returned results against
	 * the provided expected values
	 *
	 * @param k number of items to pick
	 * @param n number of items to pick from
	 * @param results the expected results
	 */
	private void combinations (int k, int n, int[][] results) {
		RepetitionCombinationIndexIterator iterator = new RepetitionCombinationIndexIterator(k, n);
		int i = 0;

		while (iterator.hasNext()) {
			assertTrue(i < results.length, String.format("iterator returned more than %d results", results.length));
			assertArrayEquals(results[i], iterator.next());
			i += 1;
		}

		assertEquals(i, results.length);
	}

	/**
	 * 10 possible combinations exist
	 */
	@Test
	void combinations_for_3_out_of_3 () {
		combinations (3, 3, new int[][] {
			{0, 0, 0},
			{0, 0, 1},
			{0, 0, 2},
			{0, 1, 1},
			{0, 1, 2},
			{0, 2, 2},
			{1, 1, 1},
			{1, 1, 2},
			{1, 2, 2},
			{2, 2, 2}
		});
	}

	/**
	 * 6 possible combinations exist
	 */
	@Test
	void combinations_for_2_out_of_3 () {
		combinations (2, 3, new int[][] {
			{0, 0},
			{0, 1},
			{0, 2},
			{1, 1},
			{1, 2},
			{2, 2}
		});
	}

	/**
	 * 3 possible combinations exist
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



