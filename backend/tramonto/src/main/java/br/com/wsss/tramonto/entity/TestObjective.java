package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TEST_OBJECTIVES")
@Getter
@Setter
public class TestObjective extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ID")
	private Test test;

	@Column(name = "OBJECTIVE", nullable = false)
	private String objective;

	@Override
	public boolean equals(Object o) {
		TestObjective to = (TestObjective) o;
		
		if (test == null)
			return objective.equals(to.getObjective());
		
		return objective.equals(to.getObjective()) && test.getId().equals(to.getTest().getId());
	}

}
