package com.hutlabs.sigma.repository;

import java.util.List;

import com.hutlabs.sigma.model.Storable;
import com.hutlabs.sigma.repository.filter.Filter;

public interface Search<T extends Storable<?>> {

	List<T> search(final Filter filter, final SearchParams params);

	List<T> search(final List<Filter> filters, final SearchParams params);

	T searchUnique(final Filter filter);

	T searchUnique(final List<Filter> filters);

	Long count(final Filter filter);

	Long count(final List<Filter> filter);

}