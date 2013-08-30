package com.hutlabs.sigma.model.database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.Getter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hutlabs.sigma.model.Storable;

@Getter
@MappedSuperclass
public abstract class AbstractEntity implements Storable<Long> {

	@Id
	@SequenceGenerator(name = "sigmaseq")
	@GeneratedValue(generator = "sigmaseq", strategy = GenerationType.AUTO)
	private Long key;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(key).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final AbstractEntity other = (AbstractEntity) obj;
		return new EqualsBuilder().append(key, other.key).isEquals();
	}

}