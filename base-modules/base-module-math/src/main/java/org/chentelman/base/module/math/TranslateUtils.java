package org.chentelman.base.module.math;

import java.lang.reflect.Array;

/**
 * Base class array translation
 *
 * This is a utility class able to convert an array of values
 * along with an array of indexes to the values array (state)
 * to a new copy
 */
public class TranslateUtils {

	/**
	 * do not allow instantiation of TranslateUtils class
	 * only holds static methods
	 */
	private TranslateUtils () {
		// avoid instantiation
	}

	/**
	 * Translate a generic type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param <T> the type of the values to combine
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static <T> void copy (int[] state, T[] values, T[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate an int type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static void copy (int[] state, int[] values, int[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a long type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static void copy (int[] state, long[] values, long[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a short type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static void copy (int[] state, short[] values, short[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a float type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static void copy (int[] state, float[] values, float[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a double type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static void copy (int[] state, double[] values, double[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a char type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static void copy (int[] state, char[] values, char[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Translate a byte type to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *  - size of the copy array exceeds the size of the state array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @param copy an array corresponding of the current combination of the values
	 */
	public static void copy (int[] state, byte[] values, byte[] copy) {
		for (int i = copy.length - 1; i >= 0; i -= 1) {
			copy[i] = values[state[i]];
		}
	}

	/**
	 * Instantiate a new generic type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] copy (int[] state, T[] values, Class<T> clazz) {
		T[] copy = null;

		if (state != null) {
			copy = (T[]) Array.newInstance(clazz, state.length);
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new int type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static int[] copy (int[] state, int[] values) {
		int[] copy = null;

		if (state != null) {
			copy = new int[state.length];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new long type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static long[] copy (int[] state, long[] values) {
		long[] copy = null;

		if (state != null) {
			copy = new long[state.length];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new short type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static short[] copy (int[] state, short[] values) {
		short[] copy = null;

		if (state != null) {
			copy = new short[state.length];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new float type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static float[] copy (int[] state, float[] values) {
		float[] copy = null;

		if (state != null) {
			copy = new float[state.length];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new double type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static double[] copy (int[] state, double[] values) {
		double[] copy = null;

		if (state != null) {
			copy = new double[state.length];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new long type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static char[] copy (int[] state, char[] values) {
		char[] copy = null;

		if (state != null) {
			copy = new char[state.length];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new long type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static byte[] copy (int[] state, byte[] values) {
		byte[] copy = null;

		if (state != null) {
			copy = new byte[state.length];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new generic type array and translate the values
	 * to the combination defined by the state.
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
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
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
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
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
	 * Instantiate a new short type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static short[] copy (int k, int[] state, short[] values) {
		short[] copy = null;

		if (state != null) {
			copy = new short[k];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new float type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static float[] copy (int k, int[] state, float[] values) {
		float[] copy = null;

		if (state != null) {
			copy = new float[k];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new double type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static double[] copy (int k, int[] state, double[] values) {
		double[] copy = null;

		if (state != null) {
			copy = new double[k];
			copy (state, values, copy);
		}

		return copy;
	}

	/**
	 * Instantiate a new long type array and translate the values
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
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
	 * to the combination defined by the state.
	 * No checks regarding the array sizes or values is done.
	 *
	 * Possible reasons for array out of bounds cases
	 *  - values in state exceed the size of the values array
	 *
	 * @param k the number of items to pick
	 * @param state the current combination as computed by init and next methods
	 * @param values the values to combine over
	 * @return a new array corresponding of the current combination of the values
	 */
	public static byte[] copy (int k, int[] state, byte[] values) {
		byte[] copy = null;

		if (state != null) {
			copy = new byte[k];
			copy (state, values, copy);
		}

		return copy;
	}

}



