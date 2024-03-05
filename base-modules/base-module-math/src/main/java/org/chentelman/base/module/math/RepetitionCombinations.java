package org.chentelman.base.module.math;

import java.lang.reflect.Array;

/**
 * Base class for combinations with repetitions.
 *
 * This is a utility class able to initialize a state
 * as well as extract iteratively each combination from the state
 *
 * The size of the state for the combinations is equal to k,
 * the items to pick. Value for n has to be provided each time
 * to the iterative method to calculate the next value.
 *
 * If that value is modified between calls for the same state
 * it will lead to unexpected results
 *
 * <n> the key type of the base entity
 * <k> the entity used for the summary
 */
public class RepetitionCombinations {

	/**
	 * do not allow instantiation of RepetitionCombinations class
	 * only holds static methods
	 */
	private RepetitionCombinations () {
		// avoid instantiation
	}

	/**
	 * Initialize an array of values to be the first combination.
	 * the number of items to pick is deduced to be the length of the values array
	 * the number of items to pick from is assumed to be greater or equal than the items to pick
	 *
	 * @param values the array to initialize
	 */
	public static void init (int[] values) {
		for (int i = 0; i < values.length; i += 1) {
			values[i] = 0;
		}
	}

	/**
	 * Create and initialize an array for values to be the the first combination
	 *
	 * @param k the number of items to pick
	 * @param n the number of items to pick from
	 * @return the initialized state of the combination
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
	 * Update the state to be the next combination in the list
	 *
	 * @param n the number of items to pick from
	 * @param state the current state of the combination
	 * @return true in case a next state does exist, false otherwise
	 */
	public static boolean next (int n, int[] state) {
		int k;
		for (k = state.length - 1; k >= 0; k -= 1) {
			if (state[k] < n - 1) {
				state[k] += 1;
				for (k += 1; k < state.length; k += 1) {
					state[k] = state[k-1];
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Update the state to be the next combination in the list
	 *
	 * @param k the number of items to pick
	 * @param n the number of items to pick from
	 * @param state the current state of the combination
	 * @return true in case a next state does exist, false otherwise
	 */
	public static boolean next (int k, int n, int[] state) {
		if (state != null && state.length == k) {
			return next (n, state);
		}
		return false;
	}

	/**
	 * This is (n+k-1)! / ((n-1)! * k!)
	 *
	 * @param k number of items to pick
	 * @param n number of items to pick from
	 * @return the amount of combinations to pick
	 */
	public static long count (int k, int n) {
		return Combinations.count(k, n + k - 1);
	}

	/**
	 * Calculate and return all possible combinations
	 *
	 * @param k the number of items to pick
	 * @param n the number of items to pick from
	 * @return an array of the combinations
	 */
	public static int[][] of (int k, int n) {
		int i = 0;
		int c = (int)count(k, n);
		int[] state = init (k, n);
		int[][] combis = new int[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			System.arraycopy(state, 0, combis[i], 0, k);
			i += 1;
			hasNext = next(n, state);
		}

		return i == c && !hasNext ? combis : new int[0][];
	}

	/**
	 * Calculate and return all possible combinations for the provided values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the combinations
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[][] of (int k, T[] values, Class<T> clazz) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		T[][] combis = (T[][]) Array.newInstance(clazz, c, k);

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, combis[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? combis : (T[][]) Array.newInstance(Array.newInstance(clazz, 0).getClass(), 0);
	}

	/**
	 * Calculate and return all possible combinations for the provided int values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the combinations
	 */
	public static int[][] of (int k, int[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		int[][] combis = new int[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, combis[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? combis : new int[0][];
	}

	/**
	 * Calculate and return all possible combinations for the provided long values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the combinations
	 */
	public static long[][] of (int k, long[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		long[][] combis = new long[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, combis[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? combis : new long[0][];
	}

	/**
	 * Calculate and return all possible combinations for the provided char values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the combinations
	 */
	public static char[][] of (int k, char[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		char[][] combis = new char[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, combis[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? combis : new char[0][];
	}

	/**
	 * Calculate and return all possible combinations for the provided byte values
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from, assumed distinct
	 * @return an array of the combinations
	 */
	public static byte[][] of (int k, byte[] values) {
		int i = 0;
		int c = (int)count(k, values.length);
		int[] state = init (k, values.length);
		byte[][] combis = new byte[c][k];

		boolean hasNext = state != null;

		while (hasNext && i < c) {
			TranslateUtils.copy(state, values, combis[i]);
			i += 1;
			hasNext = next(values.length, state);
		}

		return i == c && !hasNext ? combis : new byte[0][];
	}
}



