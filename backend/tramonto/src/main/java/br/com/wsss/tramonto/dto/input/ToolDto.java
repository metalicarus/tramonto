package br.com.wsss.tramonto.dto.input;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToolDto extends BaseDto {

	private String tool;
	private String description;
	private String link;
	
	public ToolDto(UUID id,
			String status,
			Date createdAt,
			Date updatedAt,
			String tool,
			String description,
			String link) {
		super();
		this.id = id;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.tool = tool;
		this.description = description;
		this.link = link;
	}
}
