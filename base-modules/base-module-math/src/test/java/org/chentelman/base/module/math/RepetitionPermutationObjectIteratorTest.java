package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for RepetitionPermutationIterator class
 */
class RepetitionPermutationObjectIteratorTest {
	private String[] items = new String[] {"Dog", "Cat", "Bat"};

	/**
	 * instantiate an iterator with a list of default items
	 *
	 * @param k number of items to pick
	 * @param results the expected results
	 */
	private void permutations (int k, String[][] results) {
		RepetitionPermutationObjectIterator<String> iterator = new RepetitionPermutationObjectIterator<>(k, items, String.class);
		int i = 0;

		while (iterator.hasNext()) {
			assertTrue(i < results.length, String.format("iterator returned more than %d results", results.length));
			assertArrayEquals(results[i], iterator.next());
			i += 1;
		}

		assertEquals(i, results.length);
	}

	/**
	 * 27 possible permutations exist
	 */
	@Test
	void permutations_for_3_out_of_3 () {
		permutations (3, new String[][] {
			{"Dog", "Dog", "Dog"},
			{"Dog", "Dog", "Cat"},
			{"Dog", "Dog", "Bat"},
			{"Dog", "Cat", "Dog"},
			{"Dog", "Cat", "Cat"},
			{"Dog", "Cat", "Bat"},
			{"Dog", "Bat", "Dog"},
			{"Dog", "Bat", "Cat"},
			{"Dog", "Bat", "Bat"},
			{"Cat", "Dog", "Dog"},
			{"Cat", "Dog", "Cat"},
			{"Cat", "Dog", "Bat"},
			{"Cat", "Cat", "Dog"},
			{"Cat", "Cat", "Cat"},
			{"Cat", "Cat", "Bat"},
			{"Cat", "Bat", "Dog"},
			{"Cat", "Bat", "Cat"},
			{"Cat", "Bat", "Bat"},
			{"Bat", "Dog", "Dog"},
			{"Bat", "Dog", "Cat"},
			{"Bat", "Dog", "Bat"},
			{"Bat", "Cat", "Dog"},
			{"Bat", "Cat", "Cat"},
			{"Bat", "Cat", "Bat"},
			{"Bat", "Bat", "Dog"},
			{"Bat", "Bat", "Cat"},
			{"Bat", "Bat", "Bat"}
		});
	}

	/**
	 * 9 possible permutations exist
	 */
	@Test
	void permutations_for_2_out_of_3 () {
		permutations (2, new String[][] {
			{"Dog", "Dog"},
			{"Dog", "Cat"},
			{"Dog", "Bat"},
			{"Cat", "Dog"},
			{"Cat", "Cat"},
			{"Cat", "Bat"},
			{"Bat", "Dog"},
			{"Bat", "Cat"},
			{"Bat", "Bat"}
		});
	}

	/**
	 * Three possible permutations exist
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



