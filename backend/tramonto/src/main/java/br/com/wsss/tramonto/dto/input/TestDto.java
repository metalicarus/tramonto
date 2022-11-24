package br.com.wsss.tramonto.dto.input;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDto extends BaseDto {

	private String identifier;
	private Date initialDate;
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
}
