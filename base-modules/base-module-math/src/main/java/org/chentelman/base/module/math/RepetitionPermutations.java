package org.chentelman.base.module.math;

import java.lang.reflect.Array;

/**
 * Base class for permutations with repetition.
 *
 * This is a utility class able to initialize a state
 * as well as extract iteratively each permutation from the state
 *
 * The size of the state for the permutations is equal to n,
 * the items to pick from. Value for k has to be provided only
 * during the initialization and the extraction of the copy
 *
 * <n> the number of items to pick from
 * <k> the number of items to pick
 */
public class RepetitionPermutations {

	/**
	 * do not allow instantiation of Permutations class
	 * only holds static methods
	 */
	private RepetitionPermutations () {
		// avoid instantiation
	}

	/**
	 * Initialize an array of values to be the first permutation.
	 * the number of items to pick is deduced to be the length of the values array
	 * the number of items to pick from is assumed to be greater or equal than the items to pick
	 *
	 * @param values the array to initialize
	 */
	public static void init (int[] values) {
		int i;
		for (i = 0; i < values.length; i += 1) {
			values[i] = 0;
		}
	}

	/**
	 * Create and initialize an array for values to be the the first permutation
	 *
	 * @param k the number of items to pick
	 * @param n the number of items to pick from
	 * @return the initialized state of the permutation
	 */
	public static int[] init (int k, int n) {
		// 0 < k, 0 <= n
		if (k < 0 || n <= 0) {
			return null;
		}

		int[] state = new int[k];

		init (state);

		return state;
	}

	/**
	 * Update the state to be the next permutation in the list
	 *
	 * @param n the number of items to pick from
	 * @param state the current state of the permutation
	 * @return true in case a next state does exist, false otherwise
	 */
	public static boolean next (int n, int[] state) {
		int i;

		// starting from the end of the array find the first non "capped" value
		// a capped value is considered the maximum allowed value in the array
		// and is equal to n - 1
		//
		// this is because for n amount of picks the valid options are
		// 0, 1, 2, ..., n - 1
		for (i = state.length - 1; i >= 0 && state[i] >= n - 1; i -= 1);

		// if we have reached the start of the array and not found an
		// applicable value, then all values are "capped" and therefore
		// no other states exist
		if (i < 0) {
			return false;
		}

		// increase the identified value by one,
		// and set all subsequent values equal to zero
		state[i] += 1;

		for (i += 1; i < state.length; i += 1) {
			state[i] = 0;
		}

		return true;
	}

	/**
	 * This is n ^ k
	 *
	 * @param k number of items to pick
	 * @param n number of items to pick from
	 * @return the amount of permutations to pick
	 */
	public static long count (int k, int n) {
		long r = 1;

		if (k < 0 || n < 0) {
			return 0;
		}

		for (; k > 0; k -= 1) {
			r *= n;
		}

		return r;
	}

	/**
	 * Calculate and return all possible permutations
	 *
	 * @param k the number of items to pick
	 * @param n the number of items to pick from
	 * @return an array of the permutations
	 */
	public static int[][] of (int k, int n) {
		int i = 0;
		int c = (int)count(k, n);
		int[] state = init (k, n);
		int[][] perms = new int[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			System.arraycopy(state, 0, perms[i], 0, k);
			i += 1;
			hasNext = next(n, state);
		}

		return i == c && !hasNext ? perms : new int[0][];
	}

	/**
	 * Calculate and return all possible permutations for the provided values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the permutations
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[][] of (int k, T[] values, Class<T> clazz) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		T[][] perms = (T[][]) Array.newInstance(clazz, c, k);

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy (state, values, perms[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? perms : (T[][]) Array.newInstance(clazz, 0, 0);
	}

	/**
	 * Calculate and return all possible permutations for the provided int values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the permutations
	 */
	public static int[][] of (int k, int[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		int[][] perms = new int[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, perms[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? perms : new int[0][];
	}

	/**
	 * Calculate and return all possible permutations for the provided long values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the permutations
	 */
	public static long[][] of (int k, long[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		long[][] perms = new long[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, perms[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? perms : new long[0][];
	}

	/**
	 * Calculate and return all possible permutations for the provided char values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the permutations
	 */
	public static char[][] of (int k, char[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		char[][] perms = new char[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, perms[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? perms : new char[0][];
	}

	/**
	 * Calculate and return all possible permutations for the provided byte values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the permutations
	 */
	public static byte[][] of (int k, byte[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		byte[][] perms = new byte[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, perms[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? perms : new byte[0][];
	}

}



