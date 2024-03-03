package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for PermutationIterator class
 */
class PermutationIteratorTest {

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
		PermutationIterator iterator = new PermutationIterator(3, 3);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0, 1, 2}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0, 2, 1}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {1, 0, 2}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {1, 2, 0}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {2, 0, 1}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {2, 1, 0}, iterator.next());
		assertFalse(iterator.hasNext());
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
		PermutationIterator iterator = new PermutationIterator(2, 3);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0, 1}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0, 2}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {1, 0}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {1, 2}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {2, 0}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {2, 1}, iterator.next());
		assertFalse(iterator.hasNext());
	}

	/**
	 * Three possible permutations exist
	 *  - [0]
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void permutations_for_1_out_of_3 () {
		PermutationIterator iterator = new PermutationIterator(1, 3);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {0}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {1}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new int[] {2}, iterator.next());
		assertFalse(iterator.hasNext());
	}
}



