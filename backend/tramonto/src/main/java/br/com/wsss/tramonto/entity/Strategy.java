package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "STRATEGIES")
@Getter
@Setter
public class Strategy extends BaseEntity {

	@Column(name= "STRATEGY", nullable= false, unique= true, length = 35)
	private String strategy;
	
	@Column(name= "DESCRIPTION", nullable= false, unique= true)
	private String description;
}
