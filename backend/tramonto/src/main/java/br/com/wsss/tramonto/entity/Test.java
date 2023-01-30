package br.com.wsss.tramonto.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.wsss.tramonto.domain.type.TestAggression;
import br.com.wsss.tramonto.domain.type.TestApproach;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TESTS")
@Getter
@Setter
public class Test extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "IDENTIFIER", nullable = false, unique = true, updatable = false)
	private String identifier;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INITIAL_DATE", nullable = false, updatable = false)
	private Date initialDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FINAL_DATE", nullable = true)
	private Date finalDate;

	@Temporal(TemporalType.TIME)
	@Column(name = "ESTIMATED_TIME", nullable = false)
	private Date estimatedTime;

	@Column(name = "RETEST", nullable = false)
	private boolean retest;

	@Temporal(TemporalType.DATE)
	@Column(name = "RETEST_DATE")
	private Date retestDate;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "APPROACH")
	private TestApproach approach;

	@Enumerated(EnumType.STRING)
	@Column(name = "AGGRESSION", nullable = false)
	private TestAggression aggression;

	@Column(name = "GENERAL_OBSERVATION")
	private String generalObservation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE", referencedColumnName = "ID", nullable = false)
	private TestType type;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER", referencedColumnName = "ID", nullable = false)
	private User owner;

	@OneToMany(mappedBy = "pk.test", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TestChecklist> checklists = new HashSet<>();

	@OneToMany(mappedBy = "test", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TestObjective> objectives = new HashSet<>();

	@OneToMany(mappedBy = "pk.test", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TestStrategy> strategies = new HashSet<>();
	
	@OneToMany(mappedBy = "pk.test", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TestTester> testers = new HashSet<>();

	public void addObjectives(Set<TestObjective> objs) {
		this.objectives.clear();
		this.objectives.addAll(objs);
		this.setTestInObjectives();
	}

	@PrePersist
	public void prePersist() {
		this.setTestInPkChecklist();
		this.setTestInObjectives();
		this.setTestInPkStrategies();
	}

	@PreUpdate
	public void preUpdate() {
		this.setTestInPkChecklist();
		this.setTestInObjectives();
		this.setTestInPkStrategies();
		this.setTestInPkTesters();
	}

	private void setTestInPkTesters() {
		testers.forEach(x -> {
			x.getPk().setTest(this);
		});
	}
	
	private void setTestInPkStrategies() {
		strategies.forEach(x -> {
			x.getPk().setTest(this);
		});
	}

	private void setTestInPkChecklist() {
		checklists.forEach(x -> {
			x.getPk().setTest(this);
		});
	}

	private void setTestInObjectives() {
		objectives.forEach(x -> {
			x.setTest(this);
		});
	}
}
