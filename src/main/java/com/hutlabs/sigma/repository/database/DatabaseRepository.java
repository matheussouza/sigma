package com.hutlabs.sigma.repository.database;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hutlabs.sigma.model.database.AbstractEntity;
import com.hutlabs.sigma.repository.Repository;
import com.hutlabs.sigma.repository.filter.Filter;
import com.hutlabs.sigma.repository.filter.FilterType;

public abstract class DatabaseRepository<T extends AbstractEntity> implements Repository<T, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private DatabaseQuery<T> query;

	@Override
	public T add(final T bean) {
		return entityManager.merge(bean);
	}

	@Override
	public Long size() {
		return query.count(Filter.BLANK_FILTER);
	}

	@Override
	public T get(final Long key) {
		return query.searchUnique(new Filter("key", key, FilterType.EQUAL));
	}

	@Override
	public List<T> all() {
		return query.search(Filter.BLANK_FILTER, null);
	}

	@Override
	public void remove(final Long key) {
		final T object = get(key);
		entityManager.remove(object);
		entityManager.flush();
	}

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void initialize() {
		query.setClazz((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	protected DatabaseQuery<T> getQuery() {
		return query;
	}

}