package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "TESTS_TYPES")
@Getter
@Setter
public class TestType extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name= "TYPE", updatable= true, unique= true, nullable= false)
	private String type;
	
	@Column(name= "DESCRIPTION", updatable= true, unique= false, nullable= true)
	private String description;	
}
