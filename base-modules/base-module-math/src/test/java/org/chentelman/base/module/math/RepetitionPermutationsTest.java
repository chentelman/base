package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Test cases for RepetitionPermutations class
 */
class RepetitionRepetitionPermutationsTest {

	/**
	 * verify the initialization of the permutation state
	 * initial values provided differ from the expected values
	 * in order to verify all values will be overridden to
	 * the correct ones
	 */
	@Test
	void initialization () {
		int[] state  = {2, 3, 4, 5};
		int[] expect = {0, 0, 0, 0};

		RepetitionPermutations.init(state);

		assertArrayEquals(expect, state);
	}

	/**
	 * verify the array is created and initialized
	 * using the correct dimensions (k value)
	 */
	@Test
	void instantiation () {
		int[] state  = RepetitionPermutations.init(3, 6);
		int[] expect = {0, 0, 0};

		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: picking 0 items of a list
	 * a single case for this permutation exist
	 * the empty array.
	 */
	@Test
	void instantiation_zero () {
		int[] state  = RepetitionPermutations.init(0, 6);
		int[] expect = {};

		assertArrayEquals(expect, state);
		assertFalse(RepetitionPermutations.next(6, state));

		// when next returns with false
		// no changes to state happens
		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: all items in the array are
	 * to be selected
	 * a single case for this permutation exist
	 * an array with all elements.
	 */
	@Test
	void initialization_n_equals_1 () {
		int[] perms = RepetitionPermutations.init(1, 1);

		assertNotNull(perms);
		assertFalse(RepetitionPermutations.next(1, perms));
	}

	/**
	 * simple case for incrementing the next permutation
	 * only the last value is updated and no recursion
	 */
	@Test
	void next_no_recursion () {
		int[] state  = {0, 1, 2, 5, 4, 3};
		int[] expect = {0, 1, 2, 5, 4, 4};

		RepetitionPermutations.next(6, state);

		assertArrayEquals(expect, state);
	}

	/**
	 * simple case for incrementing the next permutation
	 * where items are recursively updated
	 */
	@Test
	void next_recursion () {
		int[] state  = {0, 5, 5, 5, 5, 5};
		int[] expect = {1, 0, 0, 0, 0, 0};

		RepetitionPermutations.next(6, state);

		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: last permutation for the setup
	 * state is not updated and the next operation
	 * returns false
	 */
	@Test
	void nextend () {
		int[] state  = {5, 5, 5, 5, 5, 5};
		int[] expect = {5, 5, 5, 5, 5, 5};

		RepetitionPermutations.next(6, state);

		assertArrayEquals(expect, state);
	}

	/**
	 * Only one possible permutation exist
	 *  - [1]
	 */
	@Test
	void count_1_of_1 () {
		assertEquals(1, RepetitionPermutations.count(1, 1));
	}

	/**
	 * Two possible permutation exist
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void count_1_of_2 () {
		assertEquals(2, RepetitionPermutations.count(1, 2));
	}

	/**
	 * Four possible permutation exist
	 *  - [1,1]
	 *  - [1,2]
	 *  - [2,1]
	 *  - [2,2]
	 */
	@Test
	void count_2_of_2 () {
		assertEquals(4, RepetitionPermutations.count(2, 2));
	}

	/**
	 * Three possible permutation exist
	 *  - [1]
	 *  - [2]
	 *  - [3]
	 */
	@Test
	void count_1_of_3 () {
		assertEquals(3, RepetitionPermutations.count(1, 3));
	}

	/**
	 * Nine possible permutation exist
	 *  - [1,1]
	 *  - [1,2]
	 *  - [1,3]
	 *  - [2,1]
	 *  - [2,2]
	 *  - [2,3]
	 *  - [3,1]
	 *  - [3,2]
	 *  - [3,3]
	 */
	@Test
	void count_2_of_3 () {
		assertEquals(9, RepetitionPermutations.count(2, 3));
	}

	/**
	 * 27 possible permutation exist
	 *  - [1,1,1]
	 *  - [1,1,2]
	 *  - [1,1,3]
	 *  - [1,2,1]
	 *  - [1,2,2]
	 *  - [1,2,3]
	 *  - [1,3,1]
	 *  - [1,3,2]
	 *  - [1,3,3]
	 *  - [2,1,1]
	 *  - [2,1,2]
	 *  - [2,1,3]
	 *  - [2,2,1]
	 *  - [2,2,2]
	 *  - [2,2,3]
	 *  - [2,3,1]
	 *  - [2,3,2]
	 *  - [2,3,3]
	 *  - [3,1,1]
	 *  - [3,1,2]
	 *  - [3,1,3]
	 *  - [3,2,1]
	 *  - [3,2,2]
	 *  - [3,2,3]
	 *  - [3,3,1]
	 *  - [3,3,2]
	 *  - [3,3,3]
	 */
	@Test
	void count_3_of_3 () {
		assertEquals(27, RepetitionPermutations.count(3, 3));
	}

	/**
	 * Four possible permutation exist
	 *  - [1]
	 *  - [2]
	 *  - [3]
	 *  - [4]
	 */
	@Test
	void count_1_of_4 () {
		assertEquals(4, RepetitionPermutations.count(1, 4));
	}

	/**
	 * 16 possible permutation exist
	 *  - [1,1]
	 *  - [1,2]
	 *  - [1,3]
	 *  - [1,4]
	 *  - [2,1]
	 *  - [2,2]
	 *  - [2,3]
	 *  - [2,4]
	 *  - [3,1]
	 *  - [3,2]
	 *  - [3,3]
	 *  - [3,4]
	 *  - [4,1]
	 *  - [4,2]
	 *  - [4,3]
	 *  - [4,4]
	 */
	@Test
	void count_2_of_4 () {
		assertEquals(16, RepetitionPermutations.count(2, 4));
	}

	/**
	 * 64 possible permutation exist
	 * (not listed)
	 */
	@Test
	void count_3_of_4 () {
		assertEquals(64, RepetitionPermutations.count(3, 4));
	}

	/**
	 * 256 possible permutation exist
	 * (not listed)
	 */
	@Test
	void count_4_of_4 () {
		assertEquals(256, RepetitionPermutations.count(4, 4));
	}

	/**
	 * Too many RepetitionPermutations exists to list here
	 * just verify a large calculation
	 */
	@Test
	void count_10_of_10 () {
		assertEquals(10000000000L, RepetitionPermutations.count(10, 10));
	}

	/**
	 * generate permutations for no picks
	 * a single empty permutation exists
	 */
	@Test
	void of_empty () {
		int[][] permutations = RepetitionPermutations.of(0, 2);
		int[][] expect = {{}};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for no values
	 * no permutation exist
	 */
	@Test
	void of_nothing () {
		int[][] permutations = RepetitionPermutations.of(2, 0);
		int[][] expect = {};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for indexes
	 */
	@Test
	void of () {
		int[][] permutations = RepetitionPermutations.of(2, 3);
		int[][] expect = {
			{0, 0},
			{0, 1},
			{0, 2},
			{1, 0},
			{1, 1},
			{1, 2},
			{2, 0},
			{2, 1},
			{2, 2}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for strings
	 */
	@Test
	void of_string () {
		String[][] permutations = RepetitionPermutations.of(2, new String[] {"A", "B", "C"}, String.class);
		String[][] expect = {
			{"A", "A"},
			{"A", "B"},
			{"A", "C"},
			{"B", "A"},
			{"B", "B"},
			{"B", "C"},
			{"C", "A"},
			{"C", "B"},
			{"C", "C"}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for integers
	 */
	@Test
	void of_int () {
		int[][] permutations = RepetitionPermutations.of(2, new int[] {1, 2, 3});
		int[][] expect = {
			{1, 1},
			{1, 2},
			{1, 3},
			{2, 1},
			{2, 2},
			{2, 3},
			{3, 1},
			{3, 2},
			{3, 3}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for long integers
	 */
	@Test
	void of_long () {
		long[][] permutations = RepetitionPermutations.of(2, new long[] {100000000001L, 100000000002L, 100000000003L});
		long[][] expect = {
			{100000000001L, 100000000001L},
			{100000000001L, 100000000002L},
			{100000000001L, 100000000003L},
			{100000000002L, 100000000001L},
			{100000000002L, 100000000002L},
			{100000000002L, 100000000003L},
			{100000000003L, 100000000001L},
			{100000000003L, 100000000002L},
			{100000000003L, 100000000003L}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for characters
	 */
	@Test
	void of_char () {
		char[][] permutations = RepetitionPermutations.of(2, new char[] {'A', 'B', 'C'});
		char[][] expect = {
			{'A', 'A'},
			{'A', 'B'},
			{'A', 'C'},
			{'B', 'A'},
			{'B', 'B'},
			{'B', 'C'},
			{'C', 'A'},
			{'C', 'B'},
			{'C', 'C'}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for bytes
	 */
	@Test
	void of_byte () {
		int[][] permutations = RepetitionPermutations.of(2, new int[] {4, 5, 6});
		int[][] expect = {
			{4, 4},
			{4, 5},
			{4, 6},
			{5, 4},
			{5, 5},
			{5, 6},
			{6, 4},
			{6, 5},
			{6, 6}
		};

		assertArrayEquals(expect, permutations);
	}

}



