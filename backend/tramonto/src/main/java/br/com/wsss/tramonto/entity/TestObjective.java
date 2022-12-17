package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "TEST_OBJECTIVES")
@Getter
@Setter
public class TestObjective extends BaseEntity {
	
    @ManyToOne(fetch = FetchType.LAZY)
    private Test test;
    
	@Column(name= "OBJECTIVE", nullable= false)
	private String objective;
}
