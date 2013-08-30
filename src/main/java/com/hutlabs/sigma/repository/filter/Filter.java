package com.hutlabs.sigma.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Filter {

	public static Filter BLANK_FILTER = new Filter();

	private Filter() {}

	private String fieldName;
	private Object value;
	private FilterType type;

}