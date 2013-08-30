package com.hutlabs.sigma.repository.database.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.hutlabs.sigma.repository.filter.Filter;
import com.hutlabs.sigma.repository.filter.FilterType;

@Component
public class DatabaseFilterProcessor {

	private static Map<FilterType, Filtering> filteringMap;

	static {
		filteringMap = new HashMap<>();
		filteringMap.put(FilterType.EQUAL, new EqualFiltering());
		filteringMap.put(FilterType.LIKE, new LikeFiltering());
	}

	@PersistenceContext
	private EntityManager entityManager;

	public <T> Predicate translateFilter(final Filter filter, final Root<T> root) {
		if (filter == Filter.BLANK_FILTER) return getBuilder().and();
		return filteringMap.get(filter.getType()).generateClause(getBuilder(), root, filter);
	}

	public <T> Predicate translateFilter(final List<Filter> filters, final Root<T> root) {
		Predicate predicate = getBuilder().and();
		for (final Filter filter : filters) predicate = getBuilder().and(predicate, translateFilter(filter, root));

		return predicate;
	}

	public CriteriaBuilder getBuilder() {
		return entityManager.getCriteriaBuilder();
	}

}