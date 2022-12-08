package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.wsss.tramonto.domain.type.LabelType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "LABELS")
@Getter
@Setter
public class Label extends BaseEntity {
	
	@Column(name= "LABEL", nullable= false, unique= true, length = 35)
	private String label;

	@Column(name= "DESCRIPTION", nullable= false, unique= true)
	private String description;
	
	@Column(name= "IS_DEFAULT", nullable= false)
	private boolean isDefault;
	
    @Enumerated(EnumType.STRING)
	@Column(name= "TYPE")
    private LabelType type;
}
