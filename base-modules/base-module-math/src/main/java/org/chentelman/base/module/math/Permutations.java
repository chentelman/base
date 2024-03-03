package org.chentelman.base.module.math;

import java.lang.reflect.Array;

/**
 * Base class for permutations.
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
public class Permutations {

	/**
	 * do not allow instantiation of Permutations class
	 * only holds static methods
	 */
	private Permutations () {
		// avoid instantiation
	}

	/**
	 * Initialize an array of values to be the first permutation.
	 * the number of items to pick is deduced to be the length of the values array
	 * the number of items to pick from is assumed to be greater or equal than the items to pick
	 *
	 * @param values the array to initialize
	 */
	public static void init (int k, int[] values) {
		int i;
		for (i = 0; i < k; i += 1) {
			values[i] = i;
		}
		for (; i < values.length; i += 1) {
			values[i] = values.length - 1 + k - i;
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
		// 0 < k <= n
		if (k < 0 || n < k) {
			return null;
		}

		int[] state = new int[n];

		init (k, state);

		return state;
	}

	/**
	 * Update the state to be the next permutation in the list
	 *
	 * @param k the number of items to pick
	 * @param state the current state of the combination
	 * @return true in case a next state does exist, false otherwise
	 */
	public static boolean next (int k, int[] state) {
		int l;
		int pivot;

		// traverse the state array from the end backwards
		// until the first change from a smaller to a greater number is found
		// to identify the pivot point
		//
		// in the initial state this will be just a pointer to the
		// item just after the size of the array
		//
		//           pivot
		//             v
		//  [ 0, 1, 2, 3, 6, 5, 4 ]
		//                ^
		//                l
		//
		// in case this has been further updated
		//
		//        pivot
		//          v
		//  [ 0, 1, 2, 6, 5, 4, 3 ]
		//             ^
		//             l
		for (l = state.length - 1; l > 0 && state[l-1] >= state[l]; l -= 1);

		// in case no such case is found there is no next state
		if (l <= 0) {
			return false;
		}

		// the pivot point is the element to be updated
		pivot = l - 1;

		// update l to point to the smallest value after the pivot point
		// that is also greater than the pivot's point value
		//
		// in the initial state this will move the l value to the last element
		// in the state
		//
		//           pivot
		//             v
		//  [ 0, 1, 2, 3, 6, 5, 4 ]
		//                      ^
		//                      l
		for (int m = l + 1; m < state.length; m += 1) {
			if (state[pivot] < state[m] && state[m] < state[l]) {
				l = m;
			}
		}

		// swap the pivot point with the identified l item
		int s = state[pivot];
		state[pivot] = state[l];
		state[l] = s;

		// if the pivot point is not the last item to pick
		// we need to reset the values right after it
		//
		// as the smallest values will be on the right
		// we do swap all of the items on the right to
		// bring the smallest values first
		//
		// but also swap all values after the k threshold
		// to avoid generating multiple values of the same state
		//
		// with a starting point of
		//
		//        pivot
		//          v
		//  [ 0, 1, 2, 6, 5, 4, 3 ]
		//                ^
		//                k
		//
		// the first flip will bring the state as
		//
		//        pivot
		//          v
		//  [ 0, 1, 2, 3, 4, 5, 6 ]
		//                ^
		//                k
		//
		// and the second flip as
		//
		//        pivot
		//          v
		//  [ 0, 1, 2, 3, 6, 5, 4 ]
		//                ^
		//                k
		//
		// the last flip will not affect the
		// items in the first part of the array
		// that will be the next permutation
		// but will skip states such as
		//
		//   [ 0, 1, 2, 3, 4, 6, 5 ]
		//
		// that would yield a duplicate permutation
		if (k - 1 > pivot) {
			flip (pivot + 1, state.length - 1, state);
			flip (k,         state.length - 1, state);
		}

		return true;
	}

	/**
	 * Utility method to flip a section of an array
	 * swapping the start and end elements while
	 * incrementing the start element and decrementing
	 * the end element by one
	 *
	 * @param start the starting position to flip
	 * @param end the ending position to flip
	 * @param state the array to flip
	 */
	private static void flip (int start, int end, int[] state) {
		int swap;
		for (; start < end; start += 1, end -= 1) {
			swap         = state[start];
			state[start] = state[end];
			state[end]   = swap;
		}
	}

	/**
	 * This is n! / (n-k)!
	 *
	 * @param k number of items to pick
	 * @param n number of items to pick from
	 * @return the amount of permutations to pick
	 */
	public static long count (int k, int n) {
		long r = 1;

		for (k = n - k + 1; k <= n; k += 1) {
			r *= k;
		}

		return r;
	}

	/**
	 * Translate a generic type to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param <T> the type of the values to permute
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @param copy an array corresponding of the current permutation of the values
	 */
	public static <T> void copy (int[] state, T[] values, T[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate an int type to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @param copy an array corresponding of the current permutation of the values
	 */
	public static void copy (int[] state, int[] values, int[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a long type to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @param copy an array corresponding of the current permutation of the values
	 */
	public static void copy (int[] state, long[] values, long[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a char type to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @param copy an array corresponding of the current permutation of the values
	 */
	public static void copy (int[] state, char[] values, char[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a byte type to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @param copy an array corresponding of the current permutation of the values
	 */
	public static void copy (int[] state, byte[] values, byte[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Instantiate a new generic type array and translate the values
	 * to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @return a new array corresponding of the current permutation of the values
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] copy (int k, int[] state, T[] values, Class<T> clazz) {
		T[] copy = null;

		if (state != null) {
			copy = (T[]) Array.newInstance(clazz, k);
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new int type array and translate the values
	 * to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @return a new array corresponding of the current permutation of the values
	 */
	public static int[] copy (int k, int[] state, int[] values) {
		int[] copy = null;

		if (state != null) {
			copy = new int[k];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new long type array and translate the values
	 * to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @return a new array corresponding of the current permutation of the values
	 */
	public static long[] copy (int k, int[] state, long[] values) {
		long[] copy = null;

		if (state != null) {
			copy = new long[k];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new long type array and translate the values
	 * to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @return a new array corresponding of the current permutation of the values
	 */
	public static char[] copy (int k, int[] state, char[] values) {
		char[] copy = null;

		if (state != null) {
			copy = new char[k];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new long type array and translate the values
	 * to the permutation defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current permutation as computed by init and next methods
	 * @param values the values to permute over
	 * @return a new array corresponding of the current permutation of the values
	 */
	public static byte[] copy (int k, int[] state, byte[] values) {
		byte[] copy = null;

		if (state != null) {
			copy = new byte[k];
			copy (state, values, copy);
		}

		return copy;
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
			hasNext = next(k, state);
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
			copy (state, values, perms[i]);
			i += 1;
			hasNext = next(k, state);
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
			copy(state, values, perms[i]);
			i += 1;
			hasNext = next(k, state);
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
			copy(state, values, perms[i]);
			i += 1;
			hasNext = next(k, state);
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
			copy(state, values, perms[i]);
			i += 1;
			hasNext = next(k, state);
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
			copy(state, values, perms[i]);
			i += 1;
			hasNext = next(k, state);
		}

		return i == c && !hasNext ? perms : new byte[0][];
	}
}



