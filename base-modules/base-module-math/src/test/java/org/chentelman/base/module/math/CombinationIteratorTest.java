package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for CombinationIterator class
 */
class CombinationIteratorTest {

	/**
	 * Only one possible combinations exist
	 *  - [0,1,2]
	 */
	@Test
	void combinations_for_3_out_of_3 () {
		CombinationIterator iterator = new CombinationIterator(3, 3);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0, 1, 2}, iterator.next());
		assertFalse(iterator.hasNext());
	}

	/**
	 * Three possible combinations exist
	 *  - [0,1]
	 *  - [0,2]
	 *  - [1,2]
	 */
	@Test
	void combinations_for_2_out_of_3 () {
		CombinationIterator iterator = new CombinationIterator(2, 3);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0,1}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0,2}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {1,2}, iterator.next());
		assertFalse(iterator.hasNext());
	}

	/**
	 * Three possible combinations exist
	 *  - [0]
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void combinations_for_1_out_of_3 () {
		CombinationIterator iterator = new CombinationIterator(1, 3);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {1}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {2}, iterator.next());
		assertFalse(iterator.hasNext());
	}
}



