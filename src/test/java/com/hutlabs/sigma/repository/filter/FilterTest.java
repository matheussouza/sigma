package com.hutlabs.sigma.repository.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

public class FilterTest {

	@Test
	public void testFilter() {
		final Filter filter = Filter.BLANK_FILTER;

		assertNull(filter.getFieldName());
		assertNull(filter.getType());
		assertNull(filter.getValue());
	}

	@Test
	public void testFilterType() {
		assertEquals("[EQUAL, LIKE]", Arrays.asList(FilterType.values()).toString());

		assertEquals(FilterType.EQUAL, FilterType.valueOf("EQUAL"));
		assertEquals(FilterType.LIKE, FilterType.valueOf("LIKE"));
	}

}
