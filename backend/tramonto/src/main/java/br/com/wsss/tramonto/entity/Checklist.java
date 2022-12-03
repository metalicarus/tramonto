package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.wsss.tramonto.domain.type.CheckListType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "CHECKLISTS")
@Getter
@Setter
public class Checklist extends BaseEntity {

	@Column(name= "CHECKLIST", nullable= false, unique= true, length = 35)
	private String checklist;
	
	@Column(name= "DESCRIPTION", nullable= false, unique= true)
	private String description;
	
    @Enumerated(EnumType.STRING)
	@Column(name= "TYPE")
    private CheckListType type;

	@Column(name= "IS_DEFAULT", nullable= false)
	private boolean isDefault;
}
