package br.com.wsss.tramonto.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestObjectiveDto extends BaseDto {

	private String objective;

	@Override
	public boolean equals(Object o) {
		TestObjectiveDto dto = (TestObjectiveDto) o;
		return objective.equals(dto.getObjective());
	}
}
