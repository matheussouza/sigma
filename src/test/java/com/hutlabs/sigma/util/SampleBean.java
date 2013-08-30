package com.hutlabs.sigma.util;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.hutlabs.sigma.model.database.AbstractEntity;

@Getter
@Entity
@NoArgsConstructor
public class SampleBean extends AbstractEntity {

	private String name;

	public SampleBean(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}