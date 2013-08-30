package com.hutlabs.sigma.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchParams {

	private final Integer maxRecords;
	private final Integer startingAt;
	private final OrderType orderType;
	private final String orderField;

}
