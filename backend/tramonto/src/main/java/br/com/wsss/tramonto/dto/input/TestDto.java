package br.com.wsss.tramonto.dto.input;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDto extends BaseDto {

	private String identifier;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date initialDate;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date finalDate;
	
	@JsonFormat(pattern="HH:mm:ss")
	private Date estimatedTime;
	
	private boolean retest;
	private Date retestDate;
	private String title;
	private String description;
	private String approach;
	private String aggression;
	private String generalObservation;
	private TestTypeDto type;
	private List<ChecklistDto> checklists = new ArrayList<>();
	private List<TestObjectiveDto> objectives = new ArrayList<>();
	
	
	@Getter
	@Setter
	public class TestDtoPage {}
	
}
