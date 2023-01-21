package br.com.wsss.tramonto.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.wsss.tramonto.entity.pk.TestStrategyPk;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TEST_STRATEGIES")
@Getter
@Setter
public class TestStrategy extends RecordEntity {

    @EmbeddedId
    @JsonIgnore
    private TestStrategyPk pk;
    
    @Override
    public boolean equals(Object o) {
    	TestStrategy ts = (TestStrategy) o;
    	if (pk.getTest() == null)
    		return pk.getStrategy().getId().equals(ts.getPk().getStrategy().getId());
    	return pk.getStrategy().getId().equals(ts.getPk().getStrategy().getId()) && pk.getTest().getId().equals(ts.getPk().getTest().getId()); 
    }
}
