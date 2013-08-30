package com.hutlabs.sigma.repository.database.filter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.hutlabs.sigma.repository.OrderType;
import com.hutlabs.sigma.repository.SearchParams;

@Component
public class DatabaseParamProcessor {

	@PersistenceContext
	private EntityManager entityManager;

	public <T> TypedQuery<T> buildTypedQuery(final CriteriaQuery<T> query, final Root<T> root, final SearchParams params) {
		final TypedQuery<T> finalQuery = entityManager.createQuery(query);
		if (params == null) return finalQuery;

		addOrderByClause(query, root, params);
		if (params.getStartingAt() != null) finalQuery.setFirstResult(params.getStartingAt());
		if (params.getMaxRecords() != null) finalQuery.setMaxResults(params.getMaxRecords());

		return finalQuery;
	}

	private <T> void addOrderByClause(final CriteriaQuery<T> query, final Root<T> root, final SearchParams params) {
		if (params.getOrderField() == null) return;
		final OrderType type = params.getOrderType() == null ? OrderType.ASC : params.getOrderType();
		if (type == OrderType.ASC) query.orderBy(getBuilder().asc(root.get(params.getOrderField())));
		else query.orderBy(getBuilder().desc(root.get(params.getOrderField())));
	}

	public CriteriaBuilder getBuilder() {
		return entityManager.getCriteriaBuilder();
	}

}
