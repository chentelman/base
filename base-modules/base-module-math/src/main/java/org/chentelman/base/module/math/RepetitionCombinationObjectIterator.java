package org.chentelman.base.module.math;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a simple iterator over the combinations for a set of items with repetition
 */
public class RepetitionCombinationObjectIterator<T> implements Iterator<T[]> {
	protected int[]   state;
	protected boolean valid;
	protected int     count;
	private T[]       items;
	private Class<T>  clazz;

	/**
	 * Initialize the iterator
	 *
	 * @param k the number of items to pick
	 * @param values the values to pick from
	 * @param clazz the type of the values to pick from
	 */
	public RepetitionCombinationObjectIterator (int k, T[] values, Class<T> clazz) {
		this.count = values.length;
		this.state = RepetitionCombinations.init(k, values.length);
		this.valid = this.state != null;
		this.items = values;
		this.clazz = clazz;
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
	public T[] next() {
		if (!valid) {
			throw new NoSuchElementException();
		}

		try {
			return TranslateUtils.copy(state, items, clazz);
		} finally {
			valid = RepetitionCombinations.next(count, state);
		}
	}
}



