package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "VECTOR_CATEGORIES")
@Getter
@Setter	
public class VectorCategory extends BaseEntity {

	@Column(name= "VECTOR_CATEGORY", updatable= true, unique= true, nullable= false)
	private String vectorCategory;
	
	@Column(name= "DESCRIPTION", updatable= true, unique= false, nullable= true)
	private String description;	
}
