package com.hutlabs.sigma.util;

import com.hutlabs.sigma.repository.Repository;

public interface SampleBeanRepository extends Repository<SampleBean, Long> {

	SampleBean findByName(final String name);

}
