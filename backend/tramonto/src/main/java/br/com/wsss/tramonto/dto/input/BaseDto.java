package br.com.wsss.tramonto.dto.input;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseDto {
	
	protected UUID id;
	
	@NotBlank
	@NotNull
	protected String status;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	protected Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	protected Date updatedAt;
	
}
