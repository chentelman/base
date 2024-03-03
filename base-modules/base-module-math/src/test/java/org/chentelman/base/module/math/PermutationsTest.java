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
		int[] perms = new int[] {2, 3, 4, 5};

		Permutations.init(2, perms);

		assertEquals(0, perms[0]);
		assertEquals(1, perms[1]);
		assertEquals(3, perms[2]);
		assertEquals(2, perms[3]);
	}

	/**
	 * verify the array is created and initialized
	 * using the correct dimentions (k value)
	 */
	@Test
	void instantiation () {
		int[] perms = Permutations.init(3, 6);

		assertEquals(6, perms.length);
		assertEquals(0, perms[0]);
		assertEquals(1, perms[1]);
		assertEquals(2, perms[2]);
		assertEquals(5, perms[3]);
		assertEquals(4, perms[4]);
		assertEquals(3, perms[5]);
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
		int[] perms = Permutations.init(0, 6);

		assertEquals(6, perms.length);
		assertEquals(5, perms[0]);
		assertEquals(4, perms[1]);
		assertEquals(3, perms[2]);
		assertEquals(2, perms[3]);
		assertEquals(1, perms[4]);
		assertEquals(0, perms[5]);
		assertFalse(Permutations.next(0, perms));
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
		int[] perms = {0, 1, 2, 5, 4, 3};

		assertTrue(Permutations.next(3, perms));

		assertEquals(0, perms[0]);
		assertEquals(1, perms[1]);
		assertEquals(3, perms[2]);
		assertEquals(5, perms[3]);
		assertEquals(4, perms[4]);
		assertEquals(2, perms[5]);
	}

	/**
	 * simple case for incrementing the next permutation
	 * only the last value is updated and no flip is required
	 */
	@Test
	void nextnoflipnolast () {
		int[] perms = {0, 1, 3, 5, 4, 2};

		assertTrue(Permutations.next(3, perms));

		assertEquals(0, perms[0]);
		assertEquals(1, perms[1]);
		assertEquals(4, perms[2]);
		assertEquals(5, perms[3]);
		assertEquals(3, perms[4]);
		assertEquals(2, perms[5]);
	}

	/**
	 * generic case for incrementing the next permutation
	 * if the non last element is incremented by one
	 * a flip will be required
	 */
	@Test
	void nextflip () {
		int[] perms = {0, 1, 5, 4, 3, 2};

		assertTrue(Permutations.next(3, perms));

		assertEquals(0, perms[0]);
		assertEquals(2, perms[1]);
		assertEquals(1, perms[2]);
		assertEquals(5, perms[3]);
		assertEquals(4, perms[4]);
		assertEquals(3, perms[5]);
	}

	/**
	 * edge case: last permutation for the setup
	 * state is not updated and the next operation
	 * returns false
	 */
	@Test
	void nextend () {
		int[] perms = {5, 4, 3, 2, 1, 0};

		assertFalse(Permutations.next(3, perms));

		assertEquals(5, perms[0]);
		assertEquals(4, perms[1]);
		assertEquals(3, perms[2]);
		assertEquals(2, perms[3]);
		assertEquals(1, perms[4]);
		assertEquals(0, perms[5]);
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
	 * Too many Permutations exists to list here
	 * just verify a large calculation
	 */
	@Test
	void count_15_of_15 () {
		assertEquals(1307674368000L, Permutations.count(15, 15));
	}

	/**
	 * copy a string permutation
	 */
	@Test
	void copy_string () {
		int[] state    = new int[]{2, 3, 4, 1, 0};
		String[] value = new String[] {"One", "Two", "Three", "Four", "Five"};
		String[] copy  = Permutations.copy(3, state, value, String.class);

		assertArrayEquals(new String[] {"Three", "Four", "Five"}, copy);
	}

	/**
	 * copy a int permutation
	 */
	@Test
	void copy_int () {
		int[] state = new int[] {0, 2, 3, 4, 1, 5};
		int[] value = new int[] {10, 11, 12, 13, 14, 15};
		int[] copy  = Permutations.copy(4, state, value);

		assertArrayEquals(new int[] {10, 12, 13, 14}, copy);
	}

	/**
	 * copy a long permutation
	 */
	@Test
	void copy_long () {
		int[] state  = new int[]{1, 2, 5, 4, 3, 0};
		long[] value = new long[] {100000000000L, 100000000001L, 100000000002L, 100000000003L, 100000000004L, 100000000005L};
		long[] copy  = Permutations.copy(2, state, value);

		assertArrayEquals(new long[] {100000000001L, 100000000002L}, copy);
	}

	/**
	 * copy a byte permutation
	 */
	@Test
	void copy_byte () {
		int[] state  = new int[]{0, 3, 2, 1};
		byte[] value = new byte[] {1, 2, 3, 4};
		byte[] copy  = Permutations.copy(2, state, value);

		assertArrayEquals(new byte[] {1, 4}, copy);
	}

	/**
	 * copy a char permutation
	 */
	@Test
	void copy_char () {
		int[] state  = new int[]{1, 2, 0, 4};
		char[] value = new char[] {1, 2, 3, 4};
		char[] copy  = Permutations.copy(2, state, value);

		assertArrayEquals(new char[] {2, 3}, copy);
	}

	/**
	 * generate permutations for indexes
	 */
	@Test
	void of () {
		int[][] permutations = Permutations.of(2, 3);

		assertEquals(6, permutations.length);
		assertEquals(2, permutations[0].length);
		assertEquals(0, permutations[0][0]);
		assertEquals(1, permutations[0][1]);
		assertEquals(2, permutations[1].length);
		assertEquals(0, permutations[1][0]);
		assertEquals(2, permutations[1][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(1, permutations[2][0]);
		assertEquals(0, permutations[2][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(1, permutations[3][0]);
		assertEquals(2, permutations[3][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(2, permutations[4][0]);
		assertEquals(0, permutations[4][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(2, permutations[5][0]);
		assertEquals(1, permutations[5][1]);
	}

	/**
	 * generate permutations for strings
	 */
	@Test
	void of_string () {
		String[][] permutations = Permutations.of(2, new String[] {"A", "B", "C"}, String.class);

		assertEquals(6, permutations.length);
		assertEquals(2, permutations[0].length);
		assertEquals("A", permutations[0][0]);
		assertEquals("B", permutations[0][1]);
		assertEquals(2, permutations[1].length);
		assertEquals("A", permutations[1][0]);
		assertEquals("C", permutations[1][1]);
		assertEquals(2, permutations[2].length);
		assertEquals("B", permutations[2][0]);
		assertEquals("A", permutations[2][1]);
		assertEquals(2, permutations[2].length);
		assertEquals("B", permutations[3][0]);
		assertEquals("C", permutations[3][1]);
		assertEquals(2, permutations[2].length);
		assertEquals("C", permutations[4][0]);
		assertEquals("A", permutations[4][1]);
		assertEquals(2, permutations[2].length);
		assertEquals("C", permutations[5][0]);
		assertEquals("B", permutations[5][1]);
	}

	/**
	 * generate permutations for integers
	 */
	@Test
	void of_int () {
		int[][] permutations = Permutations.of(2, new int[] {1, 2, 3});

		assertEquals(6, permutations.length);
		assertEquals(2, permutations[0].length);
		assertEquals(1, permutations[0][0]);
		assertEquals(2, permutations[0][1]);
		assertEquals(2, permutations[1].length);
		assertEquals(1, permutations[1][0]);
		assertEquals(3, permutations[1][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(2, permutations[2][0]);
		assertEquals(1, permutations[2][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(2, permutations[3][0]);
		assertEquals(3, permutations[3][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(3, permutations[4][0]);
		assertEquals(1, permutations[4][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(3, permutations[5][0]);
		assertEquals(2, permutations[5][1]);
	}

	/**
	 * generate permutations for long integers
	 */
	@Test
	void of_long () {
		long[][] permutations = Permutations.of(2, new long[] {100000000001L, 100000000002L, 100000000003L});

		assertEquals(6, permutations.length);
		assertEquals(2, permutations[0].length);
		assertEquals(100000000001L, permutations[0][0]);
		assertEquals(100000000002L, permutations[0][1]);
		assertEquals(2, permutations[1].length);
		assertEquals(100000000001L, permutations[1][0]);
		assertEquals(100000000003L, permutations[1][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(100000000002L, permutations[2][0]);
		assertEquals(100000000001L, permutations[2][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(100000000002L, permutations[3][0]);
		assertEquals(100000000003L, permutations[3][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(100000000003L, permutations[4][0]);
		assertEquals(100000000001L, permutations[4][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(100000000003L, permutations[5][0]);
		assertEquals(100000000002L, permutations[5][1]);
	}

	/**
	 * generate permutations for characters
	 */
	@Test
	void of_char () {
		char[][] permutations = Permutations.of(2, new char[] {'A', 'B', 'C'});

		assertEquals(6, permutations.length);
		assertEquals(2, permutations[0].length);
		assertEquals('A', permutations[0][0]);
		assertEquals('B', permutations[0][1]);
		assertEquals(2, permutations[1].length);
		assertEquals('A', permutations[1][0]);
		assertEquals('C', permutations[1][1]);
		assertEquals(2, permutations[2].length);
		assertEquals('B', permutations[2][0]);
		assertEquals('A', permutations[2][1]);
		assertEquals(2, permutations[2].length);
		assertEquals('B', permutations[3][0]);
		assertEquals('C', permutations[3][1]);
		assertEquals(2, permutations[2].length);
		assertEquals('C', permutations[4][0]);
		assertEquals('A', permutations[4][1]);
		assertEquals(2, permutations[2].length);
		assertEquals('C', permutations[5][0]);
		assertEquals('B', permutations[5][1]);
	}

	/**
	 * generate permutations for bytes
	 */
	@Test
	void of_byte () {
		int[][] permutations = Permutations.of(2, new int[] {4, 5, 6});

		assertEquals(6, permutations.length);
		assertEquals(2, permutations[0].length);
		assertEquals(4, permutations[0][0]);
		assertEquals(5, permutations[0][1]);
		assertEquals(2, permutations[1].length);
		assertEquals(4, permutations[1][0]);
		assertEquals(6, permutations[1][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(5, permutations[2][0]);
		assertEquals(4, permutations[2][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(5, permutations[3][0]);
		assertEquals(6, permutations[3][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(6, permutations[4][0]);
		assertEquals(4, permutations[4][1]);
		assertEquals(2, permutations[2].length);
		assertEquals(6, permutations[5][0]);
		assertEquals(5, permutations[5][1]);
	}

}



