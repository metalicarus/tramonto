package br.com.wsss.tramonto.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.wsss.tramonto.entity.pk.TestChecklistPk;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TestChecklist extends RecordEntity {

    @EmbeddedId
    @JsonIgnore
    private TestChecklistPk pk;
    
    
    @Override
    public boolean equals(Object o) {
    	TestChecklist tc = (TestChecklist) o;
    	if (pk.getTest() == null)
    		return pk.getChecklist().getId().equals(tc.getPk().getChecklist().getId());
    	return pk.getChecklist().getId().equals(tc.getPk().getChecklist().getId()) && pk.getTest().getId().equals(tc.getPk().getTest().getId());
    }
}
