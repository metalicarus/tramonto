package br.com.wsss.tramonto.dto.input;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wsss.tramonto.domain.type.Status;
import lombok.Data;

@Data
public class BaseDto {

	protected UUID id;

	protected Status status = Status.ACTIVE;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	protected Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	protected Date updatedAt;

}
