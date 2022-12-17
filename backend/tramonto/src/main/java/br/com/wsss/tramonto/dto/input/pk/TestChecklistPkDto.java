package br.com.wsss.tramonto.dto.input.pk;

import br.com.wsss.tramonto.dto.input.ChecklistDto;
import br.com.wsss.tramonto.dto.input.TestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestChecklistPkDto {

	private TestDto test;
	private ChecklistDto checklist;
}
