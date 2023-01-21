package br.com.wsss.tramonto.dto.input;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date retestDate;
	
	private String title;
	private String description;
	private String approach;
	private String aggression;
	private String generalObservation;
	private TestTypeDto type;
	private Set<ChecklistDto> checklists = new HashSet<>();
	private Set<TestObjectiveDto> objectives = new HashSet<>();
	private Set<StrategyDto> strategies = new HashSet<>();
	
	
	@Getter
	@Setter
	public class TestDtoPage {}
	
}
