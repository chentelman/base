package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for PermutationIterator class
 */
class PermutationObjectIteratorTest {
	private String[] items = new String[] {"Dog", "Cat", "Bat"};

	/**
	 * instantiate an iterator with a list of default items
	 *
	 * @param k number of items to pick
	 * @param results the expected results
	 */
	private void permutations (int k, String[][] results) {
		PermutationObjectIterator<String> iterator = new PermutationObjectIterator<>(k, items, String.class);
		int i = 0;

		while (iterator.hasNext()) {
			assertTrue(i < results.length, String.format("iterator returned more than %d results", results.length));
			assertArrayEquals(results[i], iterator.next());
			i += 1;
		}

		assertEquals(i, results.length);
	}

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
		permutations (3, new String[][] {
			{"Dog", "Cat", "Bat"},
			{"Dog", "Bat", "Cat"},
			{"Cat", "Dog", "Bat"},
			{"Cat", "Bat", "Dog"},
			{"Bat", "Dog", "Cat"},
			{"Bat", "Cat", "Dog"}
		});
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
		permutations (2, new String[][] {
			{"Dog", "Cat"},
			{"Dog", "Bat"},
			{"Cat", "Dog"},
			{"Cat", "Bat"},
			{"Bat", "Dog"},
			{"Bat", "Cat"}
		});
	}

	/**
	 * Three possible permutations exist
	 *  - [0]
	 *  - [1]
	 *  - [2]
	 */
	@Test
	void permutations_for_1_out_of_3 () {
		permutations (1, new String[][] {
			{"Dog"},
			{"Cat"},
			{"Bat"}
		});
	}
}



