package br.com.wsss.tramonto.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.wsss.tramonto.entity.Strategy;
import br.com.wsss.tramonto.entity.Test;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class TestStrategyPk implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_ID")
    private Test test;
	
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "STRATEGY_ID")
    private Strategy strategy;
}
