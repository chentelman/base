package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for CombinationValueIterator class
 */
class CombinationValueIteratorTest {
	private String[] items = new String[] {"Dog", "Cat", "Bat"};

	/**
	 * Only one possible combinations exist
	 *  - [0,1,2]
	 */
	@Test
	void combinations_for_3_out_of_3 () {
		CombinationValueIterator<String> iterator = new CombinationValueIterator<>(3, items, String.class);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog", "Cat", "Bat"}, iterator.next());
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
		CombinationValueIterator<String> iterator = new CombinationValueIterator<>(2, items, String.class);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog", "Cat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog", "Bat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Cat", "Bat"}, iterator.next());
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
		CombinationValueIterator<String> iterator = new CombinationValueIterator<>(1, items, String.class);

		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Dog"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Cat"}, iterator.next());
		assertTrue(iterator.hasNext());
		assertArrayEquals(new String[] {"Bat"}, iterator.next());
		assertFalse(iterator.hasNext());
	}
}



