package br.com.wsss.tramonto.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.wsss.tramonto.dto.output.View;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestVectorDto extends BaseDto {

	@JsonView(View.Crud.class)
	private VectorCategoryDto category;
	
	private String approval;
	private UserDto owner;
	
	@JsonView(View.Crud.class)
	private String title;
	
	@JsonView(View.Crud.class)
	private String description;
	
	@JsonView(View.Crud.class)
	private String expectedResults;
	
	@JsonView(View.Crud.class)
	private String resultsObtained;
	
	@JsonView(View.Crud.class)
	private String observationApproval;
	
	@JsonView(View.Crud.class)
	private Integer reproducibility;

	@JsonView(View.Crud.class)
	private Integer impact;
	
}
