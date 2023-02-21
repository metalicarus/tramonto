package br.com.wsss.tramonto.dto.output;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;

@Getter
public class PageResponse<T> {

	@JsonView(View.Default.class)
	private List<T> content;
	
	@JsonView(View.Default.class)
	private Pageable request;
	
	@JsonView(View.Default.class)
	private Long totalItems;
	
	@JsonView(View.Default.class)
	private int number;

	public PageResponse(List<T> content, Pageable request, Long totalItems, int number) {
		this.content = content;
		this.request = request;
		this.totalItems = totalItems;
		this.number = number;
	}
}
