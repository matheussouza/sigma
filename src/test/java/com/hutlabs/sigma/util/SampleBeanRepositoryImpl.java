package com.hutlabs.sigma.util;

import org.springframework.stereotype.Repository;

import com.hutlabs.sigma.repository.database.DatabaseRepository;
import com.hutlabs.sigma.repository.filter.Filter;
import com.hutlabs.sigma.repository.filter.FilterType;

@Repository
public class SampleBeanRepositoryImpl extends DatabaseRepository<SampleBean> implements SampleBeanRepository {

	public SampleBean findByName(final String name) {
		return getQuery().searchUnique(new Filter("name", name, FilterType.EQUAL));
	}

}