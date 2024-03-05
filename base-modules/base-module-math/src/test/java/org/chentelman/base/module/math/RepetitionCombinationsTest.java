package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for RepetitionCombinations class
 */
class RepetitionCombinationsTest {

	/**
	 * verify the initialization of the combination state
	 * initial values provided differ from the expected values
	 * in order to verify all values will be overridden to
	 * the correct ones
	 */
	@Test
	void initialization () {
		int[] state  = {2, 3, 4, 5};
		int[] expect = {0, 0, 0, 0};

		RepetitionCombinations.init(state);

		assertArrayEquals(expect, state);
	}

	/**
	 * verify the array is created and initialized
	 * using the correct dimensions (k value)
	 */
	@Test
	void instantiation () {
		int[] state  = RepetitionCombinations.init(3, 6);
		int[] expect = {0, 0, 0};

		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: picking 0 items of a list
	 * a single case for this combinations exist
	 * the empty array.
	 */
	@Test
	void instantiation_zero () {
		int[] state = RepetitionCombinations.init(0, 6);
		int[] expect = {};

		assertArrayEquals(expect, state);
		assertFalse(RepetitionCombinations.next(6, state));

		// when next returns with false
		// no changes to state happens
		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: all items in the array are
	 * to be selected
	 * a single case for this combinations exist
	 * an array with all elements.
	 */
	@Test
	void initialization_n_equals_k_equals_1 () {
		int[] combi = RepetitionCombinations.init(1, 1);

		assertNotNull(combi);
		assertFalse(RepetitionCombinations.next(1, combi));
	}

	/**
	 * edge case: this setup is not valid
	 */
	@Test
	void initialization_n_lessthan_k () {
		int[] combi = RepetitionCombinations.init(3, 2);

		assertNotNull(combi);
	}

	/**
	 * simple case for incrementing the next combination
	 * only the last value is updated and incremented by one
	 */
	@Test
	void nextnoloop () {
		int[] state  = {0, 1, 2, 4};
		int[] expect = {0, 1, 2, 5};

		assertTrue(RepetitionCombinations.next(6, state));
		assertArrayEquals(expect, state);
	}

	/**
	 * generic case for incrementing the next combination
	 * if the non last element is incremented by one
	 * the item on the right will be updated as well
	 */
	@Test
	void nextloop () {
		int[] state  = {0, 1, 5, 5};
		int[] expect = {0, 2, 2, 2};

		assertTrue(RepetitionCombinations.next(6, state));
		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: last combination for the setup
	 * state is not updated and the next operation
	 * returns false
	 */
	@Test
	void nextend () {
		int[] state  = {5, 5, 5, 5};
		int[] expect = {5, 5, 5, 5};

		assertFalse(RepetitionCombinations.next(4, 6, state));
		assertArrayEquals(expect, state);
	}

	/**
	 * Only one possible combinations exist
	 *  - [1]ο
	 */
	@Test
	void count_1_of_1 () {
		assertEquals(1, RepetitionCombinations.count(1, 1));
	}

	/**
	 * Two possible combinations exist
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void count_1_of_2 () {
		assertEquals(2, RepetitionCombinations.count(1, 2));
	}

	/**
	 * Three possible combinations exist
	 *  - [1,1]
	 *  - [1,2]
	 *  - [2,2]
	 */
	@Test
	void count_2_of_2 () {
		assertEquals(3, RepetitionCombinations.count(2, 2));
	}

	/**
	 * Three possible combinations exist
	 *  - [1]
	 *  - [2]
	 *  - [3]
	 */
	@Test
	void count_1_of_3 () {
		assertEquals(3, RepetitionCombinations.count(1, 3));
	}

	/**
	 * Six possible combinations exist
	 *  - [1,1]
	 *  - [1,2]
	 *  - [1,3]
	 *  - [2,2]
	 *  - [2,3]
	 *  - [3,3]
	 */
	@Test
	void count_2_of_3 () {
		assertEquals(6, RepetitionCombinations.count(2, 3));
	}

	/**
	 * Ten possible combinations exist
	 *  - [1,1,1]
	 *  - [1,1,2]
	 *  - [1,1,3]
	 *  - [1,2,2]
	 *  - [1,2,3]
	 *  - [1,3,3]
	 *  - [2,2,2]
	 *  - [2,2,3]
	 *  - [2,3,3]
	 *  - [3,3,3]
	 */
	@Test
	void count_3_of_3 () {
		assertEquals(10, RepetitionCombinations.count(3, 3));
	}

	/**
	 * Four possible combinations exist
	 *  - [1]
	 *  - [2]
	 *  - [3]
	 *  - [4]
	 */
	@Test
	void count_1_of_4 () {
		assertEquals(4, RepetitionCombinations.count(1, 4));
	}

	/**
	 * Ten possible combinations exist
	 *  - [1,1]
	 *  - [1,2]
	 *  - [1,3]
	 *  - [1,4]
	 *  - [2,2]
	 *  - [2,3]
	 *  - [2,4]
	 *  - [3,3]
	 *  - [3,4]
	 *  - [4,4]
	 */
	@Test
	void count_2_of_4 () {
		assertEquals(10, RepetitionCombinations.count(2, 4));
	}

	/**
	 * Twenty possible combinations exist
	 *  - [1,1,1]
	 *  - [1,1,2]
	 *  - [1,1,3]
	 *  - [1,1,4]
	 *  - [1,2,2]
	 *  - [1,2,3]
	 *  - [1,2,4]
	 *  - [1,3,3]
	 *  - [1,3,4]
	 *  - [1,4,4]
	 *  - [2,2,2]
	 *  - [2,2,3]
	 *  - [2,2,4]
	 *  - [2,3,3]
	 *  - [2,3,4]
	 *  - [2,4,4]
	 *  - [3,3,3]
	 *  - [3,3,4]
	 *  - [3,4,4]
	 *  - [4,4,4]
	 */
	@Test
	void count_3_of_4 () {
		assertEquals(20, RepetitionCombinations.count(3, 4));
	}

	/**
	 * Thirty five (35) possible combinations exist
	 *  - [1,1,1,1]
	 *  - [1,1,1,2]
	 *  - [1,1,1,3]
	 *  - [1,1,1,4]
	 *  - [1,1,2,2]
	 *  - [1,1,2,3]
	 *  - [1,1,2,4]
	 *  - [1,1,3,3]
	 *  - [1,1,3,4]
	 *  - [1,1,4,4]
	 *  - [1,2,2,2]
	 *  - [1,2,2,3]
	 *  - [1,2,2,4]
	 *  - [1,2,3,3]
	 *  - [1,2,3,4]
	 *  - [1,2,4,4]
	 *  - [1,3,3,3]
	 *  - [1,3,3,4]
	 *  - [1,3,4,4]
	 *  - [1,4,4,4]
	 *  - [2,2,2,2]
	 *  - [2,2,2,3]
	 *  - [2,2,2,4]
	 *  - [2,2,3,3]
	 *  - [2,2,3,4]
	 *  - [2,2,4,4]
	 *  - [2,3,3,3]
	 *  - [2,3,3,4]
	 *  - [2,3,4,4]
	 *  - [2,4,4,4]
	 *  - [3,3,3,3]
	 *  - [3,3,3,4]
	 *  - [3,3,4,4]
	 *  - [3,4,4,4]
	 *  - [4,4,4,4]
	 */
	@Test
	void count_4_of_4 () {
		assertEquals(35, RepetitionCombinations.count(4, 4));
	}

	/**
	 * Too many combinations exists to list here
	 * just verify a large calculation
	 */
	@Test
	void count_15_of_15 () {
		assertEquals(77558760L, RepetitionCombinations.count(15, 15));
	}

	/**
	 * Too many combinations exists to list here
	 * just verify a large calculation
	 */
	@Test
	void count_20_of_20 () {
		assertEquals(68923264410L, RepetitionCombinations.count(20, 20));
	}

	/**
	 * Too many combinations exists to list here
	 * just verify a large calculation
	 */
	@Test
	void count_30_of_30 () {
		assertEquals(59132290782430712L, RepetitionCombinations.count(30, 30));
	}

	/**
	 * generate combinations for no picks
	 * a single empty combination exists
	 */
	@Test
	void of_empty () {
		int[][] combinations = RepetitionCombinations.of(0, 2);
		int[][] expect = {{}};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for no values
	 * no combination exist
	 */
	@Test
	void of_nothing () {
		int[][] combinations = RepetitionCombinations.of(2, 0);
		int[][] expect = {};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for indexes
	 */
	@Test
	void of () {
		int[][] combinations = RepetitionCombinations.of(2, 3);
		int[][] expect = {
			{0, 0},
			{0, 1},
			{0, 2},
			{1, 1},
			{1, 2},
			{2, 2}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for strings
	 */
	@Test
	void of_string () {
		String[][] combinations = RepetitionCombinations.of(2, new String[] {"A", "B", "C"}, String.class);
		String[][] expect = {
			{"A", "A"},
			{"A", "B"},
			{"A", "C"},
			{"B", "B"},
			{"B", "C"},
			{"C", "C"}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for integers
	 */
	@Test
	void of_int () {
		int[][] combinations = RepetitionCombinations.of(2, new int[] {1, 2, 3});
		int[][] expect = {
			{1, 1},
			{1, 2},
			{1, 3},
			{2, 2},
			{2, 3},
			{3, 3}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for long integers
	 */
	@Test
	void of_long () {
		long[][] combinations = RepetitionCombinations.of(2, new long[] {100000000001L, 100000000002L, 100000000003L});
		long[][] expect = {
			{100000000001L, 100000000001L},
			{100000000001L, 100000000002L},
			{100000000001L, 100000000003L},
			{100000000002L, 100000000002L},
			{100000000002L, 100000000003L},
			{100000000003L, 100000000003L}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for characters
	 */
	@Test
	void of_char () {
		char[][] combinations = RepetitionCombinations.of(2, new char[] {'A', 'B', 'C'});
		char[][] expect = {
			{'A', 'A'},
			{'A', 'B'},
			{'A', 'C'},
			{'B', 'B'},
			{'B', 'C'},
			{'C', 'C'}
		};

		assertArrayEquals(expect, combinations);
	}

	/**
	 * generate combinations for bytes
	 */
	@Test
	void of_byte () {
		int[][] combinations = RepetitionCombinations.of(2, new int[] {4, 5, 6});
		int[][] expect = {
			{4, 4},
			{4, 5},
			{4, 6},
			{5, 5},
			{5, 6},
			{6, 6}
		};

		assertArrayEquals(expect, combinations);
	}

}



