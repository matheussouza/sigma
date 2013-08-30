package com.hutlabs.sigma.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import com.hutlabs.sigma.TestClass;
import com.hutlabs.sigma.util.SampleBean;

public class RepositoryTest extends TestClass {

	@Resource(name="sampleBeanRepositoryImpl")
	private Repository<SampleBean, Long> repo;

	@Test
	public void addObject() {
		final SampleBean bean = new SampleBean("name");
		repo.add(bean);

		assertEquals(Long.valueOf(1), repo.size());
	}

	@Test
	public void retrieveAll() {
		final SampleBean bean = new SampleBean("name");
		repo.add(bean);

		assertEquals("[name]", repo.all().toString());
	}

	@Test
	public void getByKey() {
		final SampleBean bean = new SampleBean("name");
		final Long key = repo.add(bean).getKey();

		final SampleBean stored = repo.get(key);

		assertEquals("name", stored.getName());
	}

	@Test
	public void removeObject() {
		final SampleBean bean = new SampleBean("name");
		final Long key = repo.add(bean).getKey();

		assertEquals(Long.valueOf(1), repo.size());

		repo.remove(key);

		assertEquals(Long.valueOf(0), repo.size());
	}

}