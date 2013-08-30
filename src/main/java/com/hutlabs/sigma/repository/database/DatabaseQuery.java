package com.hutlabs.sigma.repository.database;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hutlabs.sigma.model.database.AbstractEntity;
import com.hutlabs.sigma.repository.Search;
import com.hutlabs.sigma.repository.SearchParams;
import com.hutlabs.sigma.repository.database.filter.DatabaseFilterProcessor;
import com.hutlabs.sigma.repository.database.filter.DatabaseParamProcessor;
import com.hutlabs.sigma.repository.filter.Filter;
import com.hutlabs.sigma.util.OnlyOneException;

@Component
public class DatabaseQuery<T extends AbstractEntity> implements Search<T> {

	@Autowired
	private DatabaseFilterProcessor filterProcessor;

	@Autowired
	private DatabaseParamProcessor paramProcessor;

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> clazz;

	@Override
	public List<T> search(final Filter filter, final SearchParams params) {
		return search(Arrays.asList(filter), params);
	}

	@Override
	public List<T> search(final List<Filter> filters, final SearchParams params) {
		return buildQuery(filters, params).getResultList();
	}

	@Override
	public T searchUnique(final Filter filter) {
		return searchUnique(Arrays.asList(filter));
	}

	@Override
	public T searchUnique(final List<Filter> filters) {
		final List<T> results = buildQuery(filters, null).getResultList();
		if (results.size() > 1) throw new OnlyOneException();
		return results.get(0);
	}

	private TypedQuery<T> buildQuery(final List<Filter> filters, final SearchParams params) {
		final CriteriaQuery<T> query = getCriteriaBuilder().createQuery(getClazz());
		final Root<T> root = query.from(getClazz());
		query.select(root).where(filterProcessor.translateFilter(filters, root));

		return paramProcessor.buildTypedQuery(query, root, params);
	}

	@Override
	public Long count(final Filter filter) {
		return count(Arrays.asList(filter));
	}

	@Override
	public Long count(final List<Filter> filters) {
		final CriteriaQuery<Long> query = getCriteriaBuilder().createQuery(Long.class);
		final Root<T> root = query.from(getClazz());
		query.select(getCriteriaBuilder().count(root)).where(filterProcessor.translateFilter(filters, root));
		return entityManager.createQuery(query).getSingleResult();
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	private Class<T> getClazz() {
		return clazz;
	}

	void setClazz(final Class<T> clazz) {
		this.clazz = clazz;
	}

}