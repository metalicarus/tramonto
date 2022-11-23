package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "TESTS_TYPES")
public class TestType extends BaseEntity {
	
	@Column(name= "NAME", updatable= true, unique= true, nullable= false)
	private String type;
	
	@Column(name= "DESCRIPTION", updatable= true, unique= false, nullable= true)
	private String description;

}
