package com.hutlabs.sigma.repository.database.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hutlabs.sigma.repository.filter.Filter;

interface Filtering {
	<T> Predicate generateClause(final CriteriaBuilder builder, final Root<T> root, final Filter filter);
}