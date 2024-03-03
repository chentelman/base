package org.chentelman.base.module.math;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a simple iterator over the permutations for n selections
 * The values for the combination is in the range [0,n) to allow them to be
 * used as indexes to an other set of item
 */
public class PermutationIterator implements Iterator<int[]> {
	protected int[]   state;
	protected boolean valid;
	protected int     count;

	/**
	 * Initialize the iterator
	 *
	 * @param k the number of items to pick
	 * @param n the number of items to pick from
	 */
	public PermutationIterator (int k, int n) {
		this.count = k;
		this.state = Permutations.init(k, n);
		this.valid = this.state != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return valid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int[] next() {
		if (!valid) {
			throw new NoSuchElementException();
		}

		try {
			return Arrays.copyOf(state, count);
		} finally {
			valid = Permutations.next(count, state);
		}
	}

}



