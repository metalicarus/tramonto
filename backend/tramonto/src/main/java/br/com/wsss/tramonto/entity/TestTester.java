package br.com.wsss.tramonto.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.wsss.tramonto.entity.pk.TestTesterPk;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TestTester extends RecordEntity {

	@EmbeddedId
	@JsonIgnore
	private TestTesterPk pk;
	
	@Override
	public boolean equals(Object o) {
		TestTester tt = (TestTester) o;
		if (pk.getTest() == null)
			return pk.getTester().getId().equals(tt.getPk().getTester().getId());
		return pk.getTester().getId().equals(tt.getPk().getTester().getId()) && pk.getTest().getId().equals(tt.getPk().getTest().getId());
	}
}
