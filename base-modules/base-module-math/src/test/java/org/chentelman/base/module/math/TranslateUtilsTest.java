package org.chentelman.base.module.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * Test cases for TranslateUtils class
 */
class TranslateUtilsTest {

	/**
	 * copy an empty string combination
	 */
	@Test
	void raw_copy_string_empty () {
		int[]    state  = {};
		String[] value  = {"One", "Two", "Three", "Four", "Five"};
		String[] expect = {};
		String[] actual = new String[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an empty int combination
	 */
	@Test
	void raw_copy_int_empty () {
		int[] state  = {};
		int[] value  = {1, 2, 3, 4, 5};
		int[] expect = {};
		int[] actual = new int[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an empty long combination
	 */
	@Test
	void raw_copy_long_empty () {
		int[]  state  = {};
		long[] value  = {100000000000L, 100000000001L, 100000000002L, 100000000003L, 100000000004L, 100000000005L};
		long[] expect = {};
		long[] actual = new long[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an empty short combination
	 */
	@Test
	void raw_copy_short_empty () {
		int[]   state  = {};
		short[] value  = {11, 12, 13, 14, 15};
		short[] expect = {};
		short[] actual = new short[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an empty float combination
	 */
	@Test
	void raw_copy_float_empty () {
		int[]   state  = {};
		float[] value  = {1.23f, 2.34f, 3.45f, 4.56f, 5.67f};
		float[] expect = {};
		float[] actual = new float[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an empty double combination
	 */
	@Test
	void raw_copy_double_empty () {
		int[]    state  = {};
		double[] value  = {1.23, 2.34, 3.45, 4.56, 5.67};
		double[] expect = {};
		double[] actual = new double[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an empty byte combination
	 */
	@Test
	void raw_copy_byte_empty () {
		int[]  state  = {};
		byte[] value  = {1, 2, 3, 4, 5};
		byte[] expect = {};
		byte[] actual = new byte[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an empty char combination
	 */
	@Test
	void raw_copy_char_empty () {
		int[]  state  = {};
		char[] value  = {'a', 'b', 'c', 'd', 'e'};
		char[] expect = {};
		char[] actual = new char[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of a string combination
	 */
	@Test
	void raw_copy_string_edge () {
		int[]    state  = {0, 4};
		String[] value  = {"One", "Two", "Three", "Four", "Five"};
		String[] expect = {"One", "Five"};
		String[] actual = new String[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of an int combination
	 */
	@Test
	void raw_copy_int_edge () {
		int[] state  = {0, 4};
		int[] value  = {1, 2, 3, 4, 5};
		int[] expect = {1, 5};
		int[] actual = new int[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of a long combination
	 */
	@Test
	void raw_copy_long_edge () {
		int[]  state  = {0, 4};
		long[] value  = {100000000001L, 100000000002L, 100000000003L, 100000000004L, 100000000005L};
		long[] expect = {100000000001L, 100000000005L};
		long[] actual = new long[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of a short combination
	 */
	@Test
	void raw_copy_short_edge () {
		int[]   state  = {0, 4};
		short[] value  = {11, 12, 13, 14, 15};
		short[] expect = {11, 15};
		short[] actual = new short[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of a float combination
	 */
	@Test
	void raw_copy_float_edge () {
		int[]   state  = {0, 4};
		float[] value  = {1.23f, 2.34f, 3.45f, 4.56f, 5.67f};
		float[] expect = {1.23f, 5.67f};
		float[] actual = new float[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of a double combination
	 */
	@Test
	void raw_copy_double_edge () {
		int[]    state  = {0, 4};
		double[] value  = {1.23, 2.34, 3.45, 4.56, 5.67};
		double[] expect = {1.23, 5.67};
		double[] actual = new double[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of a byte combination
	 */
	@Test
	void raw_copy_byte_edge () {
		int[]  state  = {0, 4};
		byte[] value  = {1, 2, 3, 4, 5};
		byte[] expect = {1, 5};
		byte[] actual = new byte[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy the edges of a char combination
	 */
	@Test
	void raw_copy_char_edge () {
		int[]  state  = {0, 4};
		char[] value  = {'a', 'b', 'c', 'd', 'e'};
		char[] expect = {'a', 'e'};
		char[] actual = new char[state.length];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a string combination with duplicates
	 */
	@Test
	void raw_copy_string_duplicates () {
		int[]    state  = {0, 0, 0, 4};
		String[] value  = {"One", "Two", "Three", "Four", "Five"};
		String[] expect = {"One", "One"};
		String[] actual = new String[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy an int combination with duplicates
	 */
	@Test
	void raw_copy_int_duplicates () {
		int[] state  = {0, 0, 0, 4};
		int[] value  = {1, 2, 3, 4, 5};
		int[] expect = {1, 1};
		int[] actual = new int[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a long combination with duplicates
	 */
	@Test
	void raw_copy_long_duplicates () {
		int[]  state  = {0, 0, 0, 4};
		long[] value  = {100000000001L, 100000000002L, 100000000003L, 100000000004L, 100000000005L};
		long[] expect = {100000000001L, 100000000001L};
		long[] actual = new long[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a short combination with duplicates
	 */
	@Test
	void raw_copy_short_duplicates () {
		int[]   state  = {0, 0, 0, 4};
		short[] value  = {11, 12, 13, 14, 15};
		short[] expect = {11, 11};
		short[] actual = new short[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a float combination with duplicates
	 */
	@Test
	void raw_copy_float_duplicates () {
		int[]   state  = {0, 0, 0, 4};
		float[] value  = {1.23f, 2.34f, 3.45f, 4.56f, 5.67f};
		float[] expect = {1.23f, 1.23f};
		float[] actual = new float[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a double combination with duplicates
	 */
	@Test
	void raw_copy_double_duplicates () {
		int[]    state  = {0, 0, 0, 4};
		double[] value  = {1.23, 2.34, 3.45, 4.56, 5.67};
		double[] expect = {1.23, 1.23};
		double[] actual = new double[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a byte combination with duplicates
	 */
	@Test
	void raw_copy_byte_duplicates () {
		int[]  state  = {0, 0, 0, 2};
		byte[] value  = {1, 2, 3, 4, 5};
		byte[] expect = {1, 1};
		byte[] actual = new byte[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a char combination with duplicates
	 */
	@Test
	void raw_copy_char_duplicates () {
		int[]  state  = {0, 0, 0, 4};
		char[] value  = {'a', 'b', 'c', 'd', 'e'};
		char[] expect = {'a', 'a'};
		char[] actual = new char[2];

		TranslateUtils.copy(state, value, actual);

		assertArrayEquals(expect, actual);
	}

	/**
	 * copy a string combination
	 */
	@Test
	void copy_string () {
		int[]    state  = {2, 3, 4};
		String[] value  = {"One", "Two", "Three", "Four", "Five"};
		String[] expect = {              "Three", "Four", "Five"};
		String[] copy   = TranslateUtils.copy(state, value, String.class);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a int combination
	 */
	@Test
	void copy_int () {
		int[] state  = {0,       2,  3,  4};
		int[] value  = {10, 11, 12, 13, 14, 15};
		int[] expect = {10,     12, 13, 14};
		int[] copy   = TranslateUtils.copy(state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a long combination
	 */
	@Test
	void copy_long () {
		int[]  state  = {                           1,             2};
		long[] value  = {100000000000L, 100000000001L, 100000000002L, 100000000003L, 100000000004L, 100000000005L};
		long[] expect = {               100000000001L, 100000000002L};
		long[] copy   = TranslateUtils.copy(state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a short combination
	 */
	@Test
	void copy_short () {
		int[]   state  = {         2,      4};
		short[] value  = {11, 12, 13, 14, 15, 16};
		short[] expect = {        13,     15};
		short[] copy   = TranslateUtils.copy(state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a float combination
	 */
	@Test
	void copy_float () {
		int[]   state  = {    0,            2,                          6};
		float[] value  = {1.23f, 2.34f, 3.45f, 4.56f, 5.67f, 6.78f, 7.89f};
		float[] expect = {1.23f,        3.45f,                      7.89f};
		float[] copy   = TranslateUtils.copy(state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a double combination
	 */
	@Test
	void copy_double () {
		int[]   state  = {    0,      1,                          5,     6};
		double[] value  = {10.23, 20.34, 30.45, 40.56, 50.67, 60.78, 70.89};
		double[] expect = {10.23, 20.34,                      60.78, 70.89};
		double[] copy   = TranslateUtils.copy(state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a byte combination
	 */
	@Test
	void copy_byte () {
		int[]  state  = {0, 3};
		byte[] value  = {1, 2, 3, 4};
		byte[] expect = {1,       4};
		byte[] copy   = TranslateUtils.copy(state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a char combination
	 */
	@Test
	void copy_char () {
		int[]  state  = {   1, 2};
		char[] value  = {1, 2, 3, 4};
		char[] expect = {   2, 3};
		char[] copy   = TranslateUtils.copy(state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a string combination partially
	 */
	@Test
	void partially_copy_string () {
		int[] state     = {2, 3, 4, 1, 0};
		String[] value  = {"One", "Two", "Three", "Four", "Five"};
		String[] expect = {              "Three", "Four", "Five"};
		String[] copy   = TranslateUtils.copy(3, state, value, String.class);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a int combination partially
	 */
	@Test
	void partially_copy_int () {
		int[] state  = {0,       2,  3,  4,     1, 5, 6, 7, 8};
		int[] value  = {10, 11, 12, 13, 14, 15};
		int[] expect = {10,     12, 13};
		int[] copy   = TranslateUtils.copy(3, state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a long combination partially
	 */
	@Test
	void partially_copy_long () {
		int[]  state  = {                           1,             2,             3,             4};
		long[] value  = {100000000000L, 100000000001L, 100000000002L, 100000000003L, 100000000004L, 100000000005L};
		long[] expect = {               100000000001L, 100000000002L};
		long[] copy   = TranslateUtils.copy(2, state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a short combination partially
	 */
	@Test
	void partially_copy_short () {
		int[]   state  = {         2,      4,  5};
		short[] value  = {11, 12, 13, 14, 15, 16};
		short[] expect = {        13,     15};
		short[] copy   = TranslateUtils.copy(2, state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a float combination partially
	 */
	@Test
	void partially_copy_float () {
		int[]   state  = {    0,            2,                          6, 1, 2, 3, 4};
		float[] value  = {1.23f, 2.34f, 3.45f, 4.56f, 5.67f, 6.78f, 7.89f};
		float[] expect = {1.23f,        3.45f,                      7.89f};
		float[] copy   = TranslateUtils.copy(3, state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a double combination partially
	 */
	@Test
	void partially_copy_double () {
		int[]   state  = {    0,      1,                          5,     6, 0, 1, 5, 6};
		double[] value  = {10.23, 20.34, 30.45, 40.56, 50.67, 60.78, 70.89};
		double[] expect = {10.23, 20.34,                      60.78, 70.89};
		double[] copy   = TranslateUtils.copy(4, state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a byte combination partially
	 */
	@Test
	void partially_copy_byte () {
		int[]  state  = {0, 3, 1, 2};
		byte[] value  = {1, 2, 3, 4};
		byte[] expect = {1,       4};
		byte[] copy   = TranslateUtils.copy(2, state, value);

		assertArrayEquals(expect, copy);
	}

	/**
	 * copy a char combination partially
	 */
	@Test
	void partially_copy_char () {
		int[]  state  = {   1, 2, 3};
		char[] value  = {1, 2, 3, 4};
		char[] expect = {   2, 3};
		char[] copy   = TranslateUtils.copy(2, state, value);

		assertArrayEquals(expect, copy);
	}

}



