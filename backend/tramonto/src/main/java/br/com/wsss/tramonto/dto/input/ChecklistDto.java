package br.com.wsss.tramonto.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChecklistDto extends BaseDto {
	
	private String checklist;
	private String description;
	private String type;
	private boolean isDefault;
}
