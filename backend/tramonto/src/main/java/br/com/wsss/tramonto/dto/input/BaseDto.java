package br.com.wsss.tramonto.dto.input;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class BaseDto {
	protected UUID id;
	protected String status;
	protected Date createdAt;
	protected Date updatedAt;
}
