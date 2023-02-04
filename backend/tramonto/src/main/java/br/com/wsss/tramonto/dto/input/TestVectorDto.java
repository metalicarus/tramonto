package br.com.wsss.tramonto.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestVectorDto extends BaseDto {

	private VectorCategoryDto category;
	private String approval;
	private UserDto owner;
	private String title;
	private String description;
	private String expectedResults;
	private String resultsObtained;
	private String observationApproval;
	private Integer reproducibility;
	private Integer impact;
	
}
