package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for Permutations class
 */
class PermutationsTest {

	/**
	 * verify the initialization of the permutation state
	 * initial values provided differ from the expected values
	 * in order to verify all values will be overridden to
	 * the correct ones
	 */
	@Test
	void initialization () {
		int[] state  = {2, 3, 4, 5};
		int[] expect = {0, 1, 3, 2};

		Permutations.init(2, state);

		assertArrayEquals(expect, state);
	}

	/**
	 * verify the array is created and initialized
	 * using the correct dimensions (k value)
	 */
	@Test
	void instantiation () {
		int[] state  = Permutations.init(3, 6);
		int[] expect = {0, 1, 2, 5, 4, 3};

		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: picking 0 items of a list
	 * a single case for this permutation exist
	 * the empty array.
	 *
	 * Quite surprisingly the state for the permutations
	 * is still 6 (the number of items to pick from)
	 * but initialized in a way that no next state exists.
	 */
	@Test
	void instantiation_zero () {
		int[] state  = Permutations.init(0, 6);
		int[] expect = {5, 4, 3, 2, 1, 0};

		assertArrayEquals(expect, state);
		assertFalse(Permutations.next(0, state));

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
		int[] perms = Permutations.init(1, 1);

		assertNotNull(perms);
		assertFalse(Permutations.next(1, perms));
	}

	/**
	 * edge case: invalid setup n < k
	 * no possible permutation can be made
	 * null is expected as a result
	 */
	@Test
	void initialization_n_lessthan_k () {
		int[] perms = Permutations.init(3, 2);

		assertNull(perms);
	}

	/**
	 * simple case for incrementing the next permutation
	 * only the last value is updated and no flip is required
	 */
	@Test
	void nextnofliplast () {
		int[] state  = {0, 1, 2, 5, 4, 3};
		int[] expect = {0, 1, 3, 5, 4, 2};

		assertTrue(Permutations.next(3, state));
		assertArrayEquals(expect, state);
	}

	/**
	 * simple case for incrementing the next permutation
	 * only the last value is updated and no flip is required
	 */
	@Test
	void nextnoflipnolast () {
		int[] state  = {0, 1, 3, 5, 4, 2};
		int[] expect = {0, 1, 4, 5, 3, 2};

		assertTrue(Permutations.next(3, state));
		assertArrayEquals(expect, state);
	}

	/**
	 * generic case for incrementing the next permutation
	 * if the non last element is incremented by one
	 * a flip will be required
	 */
	@Test
	void nextflip () {
		int[] state  = {0, 1, 5, 4, 3, 2};
		int[] expect = {0, 2, 1, 5, 4, 3};

		assertTrue(Permutations.next(3, state));
		assertArrayEquals(expect, state);
	}

	/**
	 * edge case: last permutation for the setup
	 * state is not updated and the next operation
	 * returns false
	 */
	@Test
	void nextend () {
		int[] state  = {5, 4, 3, 2, 1, 0};
		int[] expect = {5, 4, 3, 2, 1, 0};

		assertFalse(Permutations.next(3, state));

		// when next returns with false
		// no changes to state happens
		assertArrayEquals(expect, state);
	}

	/**
	 * Only one possible permutation exist
	 *  - [1]
	 */
	@Test
	void count_1_of_1 () {
		assertEquals(1, Permutations.count(1, 1));
	}

	/**
	 * Two possible permutation exist
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void count_1_of_2 () {
		assertEquals(2, Permutations.count(1, 2));
	}

	/**
	 * Two possible permutation exist
	 *  - [1,2]
	 *  - [2,1]
	 */
	@Test
	void count_2_of_2 () {
		assertEquals(2, Permutations.count(2, 2));
	}

	/**
	 * Three possible permutation exist
	 *  - [1]
	 *  - [2]
	 *  - [3]
	 */
	@Test
	void count_1_of_3 () {
		assertEquals(3, Permutations.count(1, 3));
	}

	/**
	 * Six possible permutation exist
	 *  - [1,2]
	 *  - [1,3]
	 *  - [2,1]
	 *  - [2,3]
	 *  - [3,1]
	 *  - [3,2]
	 */
	@Test
	void count_2_of_3 () {
		assertEquals(6, Permutations.count(2, 3));
	}

	/**
	 * Six possible permutation exist
	 *  - [1,2,3]
	 *  - [1,3,2]
	 *  - [2,1,3]
	 *  - [2,3,1]
	 *  - [3,1,2]
	 *  - [3,2,1]
	 */
	@Test
	void count_3_of_3 () {
		assertEquals(6, Permutations.count(3, 3));
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
		assertEquals(4, Permutations.count(1, 4));
	}

	/**
	 * Twelve possible permutation exist
	 *  - [1,2]
	 *  - [1,3]
	 *  - [1,4]
	 *  - [2,1]
	 *  - [2,3]
	 *  - [2,4]
	 *  - [3,1]
	 *  - [3,2]
	 *  - [3,4]
	 *  - [4,1]
	 *  - [4,2]
	 *  - [4,3]
	 */
	@Test
	void count_2_of_4 () {
		assertEquals(12, Permutations.count(2, 4));
	}

	/**
	 * 24 possible permutation exist
	 *  - [0,1,2]
	 *  - [0,1,3]
	 *  - [0,2,1]
	 *  - [0,2,3]
	 *  - [0,3,1]
	 *  - [0,3,2]
	 *  - [1,0,2]
	 *  - [1,0,3]
	 *  - [1,2,0]
	 *  - [1,2,3]
	 *  - [1,3,0]
	 *  - [1,3,2]
	 *  - [2,0,1]
	 *  - [2,0,3]
	 *  - [2,1,0]
	 *  - [2,1,3]
	 *  - [2,3,0]
	 *  - [2,3,1]
	 *  - [3,0,1]
	 *  - [3,0,2]
	 *  - [3,1,0]
	 *  - [3,1,2]
	 *  - [3,2,0]
	 *  - [3,2,1]
	 */
	@Test
	void count_3_of_4 () {
		assertEquals(24, Permutations.count(3, 4));
	}

	/**
	 * 24 possible permutation exist
	 *  - [0,1,2,3]
	 *  - [0,1,3,2]
	 *  - [0,2,1,3]
	 *  - [0,2,3,1]
	 *  - [0,3,1,2]
	 *  - [0,3,2,1]
	 *  - [1,0,2,3]
	 *  - [1,0,3,2]
	 *  - [1,2,0,3]
	 *  - [1,2,3,0]
	 *  - [1,3,0,2]
	 *  - [1,3,2,0]
	 *  - [2,0,1,3]
	 *  - [2,0,3,1]
	 *  - [2,1,0,3]
	 *  - [2,1,3,0]
	 *  - [2,3,0,1]
	 *  - [2,3,1,0]
	 *  - [3,0,1,2]
	 *  - [3,0,2,1]
	 *  - [3,1,0,2]
	 *  - [3,1,2,0]
	 *  - [3,2,0,1]
	 *  - [3,2,1,0]
	 */
	@Test
	void count_4_of_4 () {
		assertEquals(24, Permutations.count(4, 4));
	}

	/**
	 * generate permutations for no picks
	 * a single empty permutation exists
	 */
	@Test
	void of_empty () {
		int[][] permutations = Permutations.of(0, 2);
		int[][] expect = {{}};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for no values
	 * no permutation exist
	 */
	@Test
	void of_nothing () {
		int[][] permutations = Permutations.of(2, 0);
		int[][] expect = {};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for indexes
	 */
	@Test
	void of () {
		int[][] permutations = Permutations.of(2, 3);
		int[][] expect = {
			{0, 1},
			{0, 2},
			{1, 0},
			{1, 2},
			{2, 0},
			{2, 1}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for strings
	 */
	@Test
	void of_string () {
		String[][] permutations = Permutations.of(2, new String[] {"A", "B", "C"}, String.class);
		String[][] expect = {
			{"A", "B"},
			{"A", "C"},
			{"B", "A"},
			{"B", "C"},
			{"C", "A"},
			{"C", "B"}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for integers
	 */
	@Test
	void of_int () {
		int[][] permutations = Permutations.of(2, new int[] {1, 2, 3});
		int[][] expect = {
			{1, 2},
			{1, 3},
			{2, 1},
			{2, 3},
			{3, 1},
			{3, 2}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for long integers
	 */
	@Test
	void of_long () {
		long[][] permutations = Permutations.of(2, new long[] {100000000001L, 100000000002L, 100000000003L});
		long[][] expect = {
			{100000000001L, 100000000002L},
			{100000000001L, 100000000003L},
			{100000000002L, 100000000001L},
			{100000000002L, 100000000003L},
			{100000000003L, 100000000001L},
			{100000000003L, 100000000002L}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for characters
	 */
	@Test
	void of_char () {
		char[][] permutations = Permutations.of(2, new char[] {'A', 'B', 'C'});
		char[][] expect = {
			{'A', 'B'},
			{'A', 'C'},
			{'B', 'A'},
			{'B', 'C'},
			{'C', 'A'},
			{'C', 'B'}
		};

		assertArrayEquals(expect, permutations);
	}

	/**
	 * generate permutations for bytes
	 */
	@Test
	void of_byte () {
		int[][] permutations = Permutations.of(2, new int[] {4, 5, 6});
		int[][] expect = {
			{4, 5},
			{4, 6},
			{5, 4},
			{5, 6},
			{6, 4},
			{6, 5}
		};

		assertArrayEquals(expect, permutations);
	}

}



