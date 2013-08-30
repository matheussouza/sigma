package com.hutlabs.sigma.repository;

import javax.annotation.Resource;

import org.junit.Test;

import com.hutlabs.sigma.TestClass;
import com.hutlabs.sigma.util.OnlyOneException;
import com.hutlabs.sigma.util.SampleBean;
import com.hutlabs.sigma.util.SampleBeanRepository;

public class SampleBeanRepositoryTest extends TestClass {

	@Resource
	private SampleBeanRepository sampleBeanRepository;

	@Test(expected=OnlyOneException.class)
	public void tryToDoWrongSearch(){
		final SampleBean bean = new SampleBean("name");

		sampleBeanRepository.add(bean);
		sampleBeanRepository.add(bean);

		sampleBeanRepository.findByName("name");
	}

}