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
		int[] combi = new int[] {2, 3, 4, 5};

		Combinations.init(combi);

		assertEquals(0, combi[0]);
		assertEquals(1, combi[1]);
		assertEquals(2, combi[2]);
		assertEquals(3, combi[3]);
	}

	/**
	 * verify the array is created and initialized
	 * using the correct dimensions (k value)
	 */
	@Test
	void instantiation () {
		int[] combi = Combinations.init(3, 6);

		assertEquals(3, combi.length);
		assertEquals(0, combi[0]);
		assertEquals(1, combi[1]);
		assertEquals(2, combi[2]);
	}

	/**
	 * edge case: picking 0 items of a list
	 * a single case for this combination exist
	 * the empty array.
	 */
	@Test
	void instantiation_zero () {
		int[] combi = Combinations.init(0, 6);

		assertEquals(0, combi.length);
		assertFalse(Combinations.next(0, combi));
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
		int[] combi = {0, 1, 2, 4};

		assertTrue(Combinations.next(6, combi));

		assertEquals(0, combi[0]);
		assertEquals(1, combi[1]);
		assertEquals(2, combi[2]);
		assertEquals(5, combi[3]);
	}

	/**
	 * generic case for incrementing the next combination
	 * if the non last element is incremented by one
	 * the item on the right will be updated as well
	 */
	@Test
	void nextloop () {
		int[] combi = {0, 1, 4, 5};

		assertTrue(Combinations.next(6, combi));

		assertEquals(0, combi[0]);
		assertEquals(2, combi[1]);
		assertEquals(3, combi[2]);
		assertEquals(4, combi[3]);
	}

	/**
	 * edge case: last combination for the setup
	 * state is not updated and the next operation
	 * returns false
	 */
	@Test
	void nextend () {
		int[] combi = {2, 3, 4, 5};

		assertFalse(Combinations.next(4, 6, combi));

		assertEquals(2, combi[0]);
		assertEquals(3, combi[1]);
		assertEquals(4, combi[2]);
		assertEquals(5, combi[3]);
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
	 * copy a string combination
	 */
	@Test
	void copy_string () {
		int[] state    = new int[]{2, 3, 4};
		String[] value = new String[] {"One", "Two", "Three", "Four", "Five"};
		String[] copy  = Combinations.copy(state, value, String.class);

		assertArrayEquals(new String[] {"Three", "Four", "Five"}, copy);
	}

	/**
	 * copy a int combination
	 */
	@Test
	void copy_int () {
		int[] state = new int[]{0, 2, 3, 4};
		int[] value = new int[] {10, 11, 12, 13, 14, 15};
		int[] copy  = Combinations.copy(state, value);

		assertArrayEquals(new int[] {10, 12, 13, 14}, copy);
	}

	/**
	 * copy a long combination
	 */
	@Test
	void copy_long () {
		int[] state  = new int[]{1, 2};
		long[] value = new long[] {100000000000L, 100000000001L, 100000000002L, 100000000003L, 100000000004L, 100000000005L};
		long[] copy  = Combinations.copy(state, value);

		assertArrayEquals(new long[] {100000000001L, 100000000002L}, copy);
	}

	/**
	 * copy a byte combination
	 */
	@Test
	void copy_byte () {
		int[] state  = new int[]{0, 3};
		byte[] value = new byte[] {1, 2, 3, 4};
		byte[] copy  = Combinations.copy(state, value);

		assertArrayEquals(new byte[] {1, 4}, copy);
	}

	/**
	 * copy a char combination
	 */
	@Test
	void copy_char () {
		int[] state  = new int[]{1, 2};
		char[] value = new char[] {1, 2, 3, 4};
		char[] copy  = Combinations.copy(state, value);

		assertArrayEquals(new char[] {2, 3}, copy);
	}

	/**
	 * generate combinations for indexes
	 */
	@Test
	void of () {
		int[][] combinations = Combinations.of(2, 3);

		assertEquals(3, combinations.length);
		assertEquals(2, combinations[0].length);
		assertEquals(0, combinations[0][0]);
		assertEquals(1, combinations[0][1]);
		assertEquals(2, combinations[1].length);
		assertEquals(0, combinations[1][0]);
		assertEquals(2, combinations[1][1]);
		assertEquals(2, combinations[2].length);
		assertEquals(1, combinations[2][0]);
		assertEquals(2, combinations[2][1]);
	}

	/**
	 * generate combinations for strings
	 */
	@Test
	void of_string () {
		String[][] combinations = Combinations.of(2, new String[] {"A", "B", "C"}, String.class);

		assertEquals(3, combinations.length);
		assertEquals(2, combinations[0].length);
		assertEquals("A", combinations[0][0]);
		assertEquals("B", combinations[0][1]);
		assertEquals(2, combinations[1].length);
		assertEquals("A", combinations[1][0]);
		assertEquals("C", combinations[1][1]);
		assertEquals(2, combinations[2].length);
		assertEquals("B", combinations[2][0]);
		assertEquals("C", combinations[2][1]);
	}

	/**
	 * generate combinations for integers
	 */
	@Test
	void of_int () {
		int[][] combinations = Combinations.of(2, new int[] {1, 2, 3});

		assertEquals(3, combinations.length);
		assertEquals(2, combinations[0].length);
		assertEquals(1, combinations[0][0]);
		assertEquals(2, combinations[0][1]);
		assertEquals(2, combinations[1].length);
		assertEquals(1, combinations[1][0]);
		assertEquals(3, combinations[1][1]);
		assertEquals(2, combinations[2].length);
		assertEquals(2, combinations[2][0]);
		assertEquals(3, combinations[2][1]);
	}

	/**
	 * generate combinations for long integers
	 */
	@Test
	void of_long () {
		long[][] combinations = Combinations.of(2, new long[] {100000000001L, 100000000002L, 100000000003L});

		assertEquals(3, combinations.length);
		assertEquals(2, combinations[0].length);
		assertEquals(100000000001L, combinations[0][0]);
		assertEquals(100000000002L, combinations[0][1]);
		assertEquals(2, combinations[1].length);
		assertEquals(100000000001L, combinations[1][0]);
		assertEquals(100000000003L, combinations[1][1]);
		assertEquals(2, combinations[2].length);
		assertEquals(100000000002L, combinations[2][0]);
		assertEquals(100000000003L, combinations[2][1]);
	}

	/**
	 * generate combinations for characters
	 */
	@Test
	void of_char () {
		char[][] combinations = Combinations.of(2, new char[] {'A', 'B', 'C'});

		assertEquals(3, combinations.length);
		assertEquals(2, combinations[0].length);
		assertEquals('A', combinations[0][0]);
		assertEquals('B', combinations[0][1]);
		assertEquals(2, combinations[1].length);
		assertEquals('A', combinations[1][0]);
		assertEquals('C', combinations[1][1]);
		assertEquals(2, combinations[2].length);
		assertEquals('B', combinations[2][0]);
		assertEquals('C', combinations[2][1]);
	}

	/**
	 * generate combinations for bytes
	 */
	@Test
	void of_byte () {
		int[][] combinations = Combinations.of(2, new int[] {4, 5, 6});

		assertEquals(3, combinations.length);
		assertEquals(2, combinations[0].length);
		assertEquals(4, combinations[0][0]);
		assertEquals(5, combinations[0][1]);
		assertEquals(2, combinations[1].length);
		assertEquals(4, combinations[1][0]);
		assertEquals(6, combinations[1][1]);
		assertEquals(2, combinations[2].length);
		assertEquals(5, combinations[2][0]);
		assertEquals(6, combinations[2][1]);
	}

}



