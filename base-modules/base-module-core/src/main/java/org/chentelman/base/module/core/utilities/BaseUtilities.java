package org.chentelman.base.module.core.utilities;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Base utilities class
 */
public class BaseUtilities {

	private BaseUtilities () {
		// cannot instantiate
	}

	/**
	 * Base test method to convert an iterable to a list
	 */
	public static <E> List<E> toList (Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (List<E>) iterable;
		}

		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}

	/**
	 * Base test method to convert an iterable to a list
	 */
	public static <E, D> List<E> toList (Iterable<D> iterable, Function<D, E> convert) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.map(convert)
			.collect(Collectors.toList());
	}

	/**
	 * Base test method to convert a page to a list
	 */
	public static <E> List<E> toList (Page<E> page) {
		return page.get().collect(Collectors.toList());
	}

	/**
	 * Base test method to get a subset of a list
	 */
	public static <E> List<E> getSubset (List<E> list, Pageable pageable) {
		int size = list.size();
		int page = pageable.getPageSize();
		int start = pageable.getPageNumber() * page;
		int end   = start + page;

		if (start > size) {
			start = size;
		}

		if (end > size) {
			end = size;
		}

		return list.subList(start, end);
	}

	/**
	 * Base test method to convert a list to a page
	 */
	public static <E> Page<E> toPage (List<E> list, Pageable pageable) {
		return new PageImpl<> (getSubset(list, pageable), pageable, list.size());
	}
}



