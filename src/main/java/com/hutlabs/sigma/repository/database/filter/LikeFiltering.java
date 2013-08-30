package com.hutlabs.sigma.repository.database.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hutlabs.sigma.repository.filter.Filter;

class LikeFiltering implements Filtering {

	@Override
	public <T> Predicate generateClause(final CriteriaBuilder builder, final Root<T> root, final Filter filter) {
		final String value = filter.getValue().toString();
		final Predicate predicate = builder.like(root.<String> get(filter.getFieldName()), "%" + value + "%");
		return predicate;
	}

}