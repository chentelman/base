package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for PermutationIterator class
 */
class PermutationValueIteratorTest {
	private String[] items = new String[] {"Dog", "Cat", "Bat"};

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
		PermutationValueIterator<String> iterator = new PermutationValueIterator<>(3, items, String.class);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog", "Cat", "Bat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog", "Bat", "Cat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Cat", "Dog", "Bat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Cat", "Bat", "Dog"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Bat", "Dog", "Cat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Bat", "Cat", "Dog"}, iterator.next());
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
		PermutationValueIterator<String> iterator = new PermutationValueIterator<>(2, items, String.class);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog", "Cat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog", "Bat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Cat", "Dog"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Cat", "Bat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Bat", "Dog"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Bat", "Cat"}, iterator.next());
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
		PermutationValueIterator<String> iterator = new PermutationValueIterator<>(1, items, String.class);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Cat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Bat"}, iterator.next());
		assertFalse(iterator.hasNext());
	}
}



