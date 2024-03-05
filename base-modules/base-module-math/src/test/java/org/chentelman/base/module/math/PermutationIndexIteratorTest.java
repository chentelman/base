package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for PermutationIterator class
 */
class PermutationIndexIteratorTest {

	/**
	 * instantiate an iterator and verify all the returned results against
	 * the provided expected values
	 *
	 * @param k number of items to pick
	 * @param n number of items to pick from
	 * @param results the expected results
	 */
	private void permutations (int k, int n, int[][] results) {
		PermutationIndexIterator iterator = new PermutationIndexIterator(k, n);
		int i = 0;

		while (iterator.hasNext()) {
			assertTrue(i < results.length, String.format("iterator returned more than %d results", results.length));
			assertArrayEquals(results[i], iterator.next());
			i += 1;
		}

		assertEquals(i, results.length);
	}

	/**
	 * Six possible permutations exist
	 *  - [0,1,2]
	 *  - [0,2,1]
	 *  - [1,0,2]
	 *  - [1,2,0]
	 *  - [2,0,1]
	 *  - [2,1,0]
	 */
	@Test
	void permutations_for_3_out_of_3 () {
		permutations (3, 3, new int[][] {
			{0, 1, 2},
			{0, 2, 1},
			{1, 0, 2},
			{1, 2, 0},
			{2, 0, 1},
			{2, 1, 0}
		});
	}

	/**
	 * Six possible permutations exist
	 *  - [0,1]
	 *  - [0,2]
	 *  - [1,0]
	 *  - [1,2]
	 *  - [2,0]
	 *  - [2,1]
	 */
	@Test
	void permutations_for_2_out_of_3 () {
		permutations (2, 3, new int[][] {
			{0, 1},
			{0, 2},
			{1, 0},
			{1, 2},
			{2, 0},
			{2, 1}
		});
	}

	/**
	 * Three possible permutations exist
	 *  - [0]
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void permutations_for_1_out_of_3 () {
		permutations (1, 3, new int[][] {
			{0},
			{1},
			{2}
		});
	}
}



