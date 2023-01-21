package br.com.wsss.tramonto.dto.input.pk;

import br.com.wsss.tramonto.dto.input.StrategyDto;
import br.com.wsss.tramonto.dto.input.TestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestStrategyPkDto {

	private TestDto test;
	private StrategyDto strategy;
}
