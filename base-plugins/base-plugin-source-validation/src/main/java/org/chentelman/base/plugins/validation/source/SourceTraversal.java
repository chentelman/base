package org.chentelman.base.plugins.validation.source;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

public class SourceTraversal implements Iterator<File> {

	private File next;
	private final LinkedList<File> files;
	private final Set<String> exludePaths;

	public SourceTraversal (String[] excludePaths) {
		this.next  = null;
		this.files = new LinkedList<> ();
		this.exludePaths = new HashSet<>(Arrays.asList(excludePaths));
	}

	public void register (File file) {
		files.add(file);
	}

	@Override
	public boolean hasNext () {
		if (Objects.isNull(next)) {
			next = calculateNext ();
		}
		return Objects.nonNull(next);
	}

	@Override
	public File next () {
		if (Objects.isNull(next)) {
			next = calculateNext ();
		}

		if (Objects.isNull(next)) {
			throw new NoSuchElementException();
		}

		try {
			return next;
		} finally {
			next = calculateNext ();
		}
	}

	private File calculateNext () {
		File candidate;

		do {
			candidate = files.pop();

			if (candidate == null || isValid(candidate)) {
				return candidate;
			}
		} while (true);
	}

	private boolean isValid (File file) {
		return true;
	}

}



