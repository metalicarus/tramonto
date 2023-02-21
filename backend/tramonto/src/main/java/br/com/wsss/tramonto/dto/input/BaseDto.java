package br.com.wsss.tramonto.dto.input;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.wsss.tramonto.domain.type.Status;
import br.com.wsss.tramonto.dto.output.View;
import lombok.Data;

@Data
public class BaseDto {

	@JsonView({View.Pagination.class, View.Crud.class})
	protected UUID id;

	@JsonView({View.Pagination.class})
	protected Status status = Status.ACTIVE;

	@JsonView({View.Pagination.class})
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	protected Date createdAt;

	@JsonView({View.Pagination.class})
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	protected Date updatedAt;

}
