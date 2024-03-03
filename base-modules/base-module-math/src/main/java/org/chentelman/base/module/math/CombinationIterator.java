package org.chentelman.base.module.math;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a simple iterator over the combinations for n selections
 * The values for the combination is in the range [0,n) to allow them to be
 * used as indexes to an other set of item
 */
public class CombinationIterator implements Iterator<int[]> {
	protected int[]   state;
	protected boolean valid;
	protected int     count;

	/**
	 * Initialize the iterator
	 *
	 * @param k the number of items to pick
	 * @param n the number of items to pick from
	 */
	public CombinationIterator (int k, int n) {
		this.count = n;
		this.state = Combinations.init(k, n);
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
			return Arrays.copyOf(state, state.length);
		} finally {
			valid = Combinations.next(count, state);
		}
	}

}



