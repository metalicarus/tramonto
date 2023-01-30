package br.com.wsss.tramonto.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.wsss.tramonto.entity.Test;
import br.com.wsss.tramonto.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class TestTesterPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6238036791260612782L;
	
	@JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_ID")
    private Test test;
	
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTER_ID")
    private User tester;

}
