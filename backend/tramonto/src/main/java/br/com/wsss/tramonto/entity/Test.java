package br.com.wsss.tramonto.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.wsss.tramonto.domain.type.TestAggression;
import br.com.wsss.tramonto.domain.type.TestApproach;
import br.com.wsss.tramonto.domain.type.TestStatus;

@Entity
@Table(name = "TESTS")
public class Test extends BaseEntity {
	
	@Column(name= "IDENTIFIER", nullable = false, unique = true, updatable = false)
	private String identifier;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "INITIAL_DATE", nullable= false, updatable = false)
	private Date initialDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "FINAL_DATE", nullable= false)
	private Date finalDate;
	
	@Temporal(TemporalType.TIME)
	@Column(name= "ESTIMATED_TIME", nullable= false)
	private Date estimatedTime;
	
	@Column(name= "RETEST", nullable= false)
	private boolean retest;
	
	@Temporal(TemporalType.DATE)
	@Column(name= "RETEST_DATE")
	private Date retestDate;
	
	@Column(name= "TITLE", nullable= false)
	private String title;
	
	@Column(name= "DESCRIPTION", nullable= false)
	private String description;
	
	@Column(name= "STATUS", nullable= false)
	private TestStatus status;
	
	@Column(name= "APPROACH")
	private TestApproach approach;
	
	@Column(name= "AGGRESSION", nullable= false)
	private TestAggression aggression;
	
	@Column(name= "GENERAL_OBSERVATION")
	private String generalObservation;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TYPE", referencedColumnName = "ID", nullable = false)
	private TestType type;
	
}
