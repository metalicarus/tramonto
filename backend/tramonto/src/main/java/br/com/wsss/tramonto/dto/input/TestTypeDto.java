package br.com.wsss.tramonto.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestTypeDto extends BaseDto {
	
	@NotNull
	private String type;
	private String description;
}
