package com.hutlabs.sigma.repository;

import java.util.List;

import com.hutlabs.sigma.model.Storable;

public interface Repository<T extends Storable<K>, K> {

	T add(final T bean);

	Long size();

	T get(final K key);

	List<T> all();

	void remove(final K key);

}