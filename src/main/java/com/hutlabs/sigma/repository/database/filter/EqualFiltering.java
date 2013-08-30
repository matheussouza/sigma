package com.hutlabs.sigma.repository.database.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hutlabs.sigma.repository.filter.Filter;

class EqualFiltering implements Filtering {

	@Override
	public <T> Predicate generateClause(final CriteriaBuilder builder, final Root<T> root, final Filter filter) {
		if (filter.getValue() == null) return builder.isNull(root.get(filter.getFieldName()));

		return builder.equal(root.<String>get(filter.getFieldName()), filter.getValue());
	}

}