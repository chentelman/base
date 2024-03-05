package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for Combinations class
 */
class CombinationsTest {

	/**
	 * verify the initialization of the combination state
	 * initial values provided differ from the expected values
	 * in order to verify all values will be overridden to
	 * the correct ones
	 */
	@Test
	void initialization () {
		int[] state  = {2, 3, 4, 5};
		int[] expect = {0, 1, 2, 3};

		Combinations.init(state);

		assertArrayEquals(expect, state);
	}

	/**
	 * verify the array is created and initialized
	 * using the correct dimensions (k value)
	 */
	@Test
	void instantiation () {
		int[] state  = Combinations.init(3, 6);
		int[] expect = {0, 1, 2};

		Combinations.init(state);

		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: picking 0 items of a list
	 * a single case for this combination exist
	 * the empty array.
	 */
	@Test
	void instantiation_zero () {
		int[] state  = Combinations.init(0, 6);
		int[] expect = {};

		Combinations.init(state);

		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: all items in the array are
	 * to be selected
	 * a single case for this combination exist
	 * an array with all elements.
	 */
	@Test
	void initialization_n_equals_k () {
		int[] combi = Combinations.init(3, 3);

		assertNotNull(combi);
		assertFalse(Combinations.next(3, combi));
	}

	/**
	 * edge case: invalid setup n < k
	 * no possible combination can be made
	 * null is expected as a result
	 */
	@Test
	void initialization_n_lessthan_k () {
		int[] combi = Combinations.init(3, 2);

		assertNull(combi);
	}

	/**
	 * simple case for incrementing the next combination
	 * only the last value is updated and incremented by one
	 */
	@Test
	void nextnoloop () {
		int[] state  = {0, 1, 2, 4};
		int[] expect = {0, 1, 2, 5};

		assertTrue(Combinations.next(6, state));

		assertArrayEquals(expect, state);
	}

	/**
	 * generic case for incrementing the next combination
	 * if the non last element is incremented by one
	 * the item on the right will be updated as well
	 */
	@Test
	void nextloop () {
		int[] state  = {0, 1, 4, 5};
		int[] expect = {0, 2, 3, 4};

		assertTrue(Combinations.next(6, state));

		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: last combination for the setup
	 * state is not updated and the next operation
	 * returns false
	 */
	@Test
	void nextend () {
		int[] state  = {2, 3, 4, 5};
		int[] expect = {2, 3, 4, 5};

		assertFalse(Combinations.next(4, 6, state));

		assertArrayEquals(expect, state);
	}

	/**
	 * Only one possible combination exist
	 *  - [1]
	 */
	@Test
	void count_1_of_1 () {
		assertEquals(1, Combinations.count(1, 1));
	}

	/**
	 * Two possible combination exist
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void count_1_of_2 () {
		assertEquals(2, Combinations.count(1, 2));
	}

	/**
	 * Only one possible combination exist
	 *  - [1,2]
	 */
	@Test
	void count_2_of_2 () {
		assertEquals(1, Combinations.count(2, 2));
	}

	/**
	 * Three possible combination exist
	 *  - [1]
	 *  - [2]
	 *  - [3]
	 */
	@Test
	void count_1_of_3 () {
		assertEquals(3, Combinations.count(1, 3));
	}

	/**
	 * Three possible combination exist
	 *  - [1,2]
	 *  - [1,3]
	 *  - [2,3]
	 */
	@Test
	void count_2_of_3 () {
		assertEquals(3, Combinations.count(2, 3));
	}

	/**
	 * Only one possible combination exist
	 *  - [1,2,3]
	 */
	@Test
	void count_3_of_3 () {
		assertEquals(1, Combinations.count(3, 3));
	}

	/**
	 * Four possible combination exist
	 *  - [1]
	 *  - [2]
	 *  - [3]
	 *  - [4]
	 */
	@Test
	void count_1_of_4 () {
		assertEquals(4, Combinations.count(1, 4));
	}

	/**
	 * Six possible combination exist
	 *  - [1,2]
	 *  - [1,3]
	 *  - [1,4]
	 *  - [2,3]
	 *  - [2,4]
	 *  - [3,4]
	 */
	@Test
	void count_2_of_4 () {
		assertEquals(6, Combinations.count(2, 4));
	}

	/**
	 * Four possible combination exist
	 *  - [1,2,3]
	 *  - [1,2,4]
	 *  - [1,3,4]
	 *  - [2,3,4]
	 */
	@Test
	void count_3_of_4 () {
		assertEquals(4, Combinations.count(3, 4));
	}

	/**
	 * Only one possible combination exist
	 *  - [1,2,3,4]
	 */
	@Test
	void count_4_of_4 () {
		assertEquals(1, Combinations.count(4, 4));
	}

	/**
	 * Too many combinations exists to list here
	 * just verify a large calculation
	 */
	@Test
	void count_16_of_32 () {
		assertEquals(601080390, Combinations.count(16, 32));
	}

	/**
	 * Too many combinations exists to list here
	 * just verify a large calculation
	 */
	@Test
	void count_25_of_50 () {
		assertEquals(126410606437752L, Combinations.count(25, 50));
	}

	/**
	 * generate combinations for no picks
	 * a single empty combination exists
	 */
	@Test
	void of_empty () {
		int[][] combinations = Combinations.of(0, 2);
		int[][] expect = {{}};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for no values
	 * no combination exist
	 */
	@Test
	void of_nothing () {
		int[][] combinations = Combinations.of(2, 0);
		int[][] expect = {};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for indexes
	 */
	@Test
	void of () {
		int[][] combinations = Combinations.of(2, 3);
		int[][] expect = {
			{0, 1},
			{0, 2},
			{1, 2}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for strings
	 */
	@Test
	void of_string () {
		String[][] combinations = Combinations.of(2, new String[] {"A", "B", "C"}, String.class);
		String[][] expect = {
			{"A", "B"},
			{"A", "C"},
			{"B", "C"}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for integers
	 */
	@Test
	void of_int () {
		int[][] combinations = Combinations.of(2, new int[] {1, 2, 3});
		int[][] expect = {
			{1, 2},
			{1, 3},
			{2, 3}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for long integers
	 */
	@Test
	void of_long () {
		long[][] combinations = Combinations.of(2, new long[] {100000000001L, 100000000002L, 100000000003L});
		long[][] expect = {
			{100000000001L, 100000000002L},
			{100000000001L, 100000000003L},
			{100000000002L, 100000000003L}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for characters
	 */
	@Test
	void of_char () {
		char[][] combinations = Combinations.of(2, new char[] {'A', 'B', 'C'});
		char[][] expect = {
			{'A', 'B'},
			{'A', 'C'},
			{'B', 'C'}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for bytes
	 */
	@Test
	void of_byte () {
		int[][] combinations = Combinations.of(2, new int[] {4, 5, 6});
		int[][] expect = {
			{4, 5},
			{4, 6},
			{5, 6}
		};

		assertArrayEquals(expect, combinations);
	}

}



