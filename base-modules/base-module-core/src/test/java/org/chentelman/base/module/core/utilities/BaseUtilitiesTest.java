package org.chentelman.base.module.core.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class BaseUtilitiesTest {

	@Test
	void testIterableToList () {
		String[] values = {"value1", "value2", "value3", "value4"};
		List<String> list = BaseUtilities.toList(Arrays.stream(values).collect(Collectors.toList()));

		assertEquals ("value1", list.get(0));
		assertEquals ("value2", list.get(1));
		assertEquals ("value3", list.get(2));
		assertEquals ("value4", list.get(3));
		assertThrows (IndexOutOfBoundsException.class, () -> list.get(4));
	}

	@Test
	void testIterableToListWithConvertor () {
		String[] values = {"123", "234", "345", "456"};
		List<Integer> list = BaseUtilities.toList(Arrays.stream(values).collect(Collectors.toList()), Integer::valueOf);

		assertEquals (123, list.get(0));
		assertEquals (234, list.get(1));
		assertEquals (345, list.get(2));
		assertEquals (456, list.get(3));
		assertThrows (IndexOutOfBoundsException.class, () -> list.get(4));
	}

	private void pageToList (int pageSize) {
		List<String> values = List.of ("value1", "value2");
		Pageable pageable = PageRequest.of(2, pageSize);
		List<String> list = BaseUtilities.toList(new PageImpl<String>(values, pageable, values.size()));

		assertEquals (2, list.size());
		assertEquals ("value1", list.get(0));
		assertEquals ("value2", list.get(1));
		assertThrows (IndexOutOfBoundsException.class, () -> list.get(3));
	}

	@Test
	void testPageToList () {
		pageToList (2);
	}

	@Test
	void testLastPageToList () {
		pageToList (3);
	}

	@Test
	void testOverflownPageToList () {
		pageToList (1);
	}
}



