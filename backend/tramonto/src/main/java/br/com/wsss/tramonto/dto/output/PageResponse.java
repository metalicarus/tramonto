package br.com.wsss.tramonto.dto.output;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Getter;

@Getter
public class PageResponse<T> {

	private List<T> content;
	private Pageable request;
	private Long totalItems;
	private int number;

	public PageResponse(List<T> content, Pageable request, Long totalItems, int number) {
		this.content = content;
		this.request = request;
		this.totalItems = totalItems;
		this.number = number;
	}
}
