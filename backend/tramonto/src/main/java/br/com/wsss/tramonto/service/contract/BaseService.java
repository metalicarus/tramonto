package br.com.wsss.tramonto.service.contract;

import org.springframework.data.domain.Sort.Direction;

import br.com.wsss.tramonto.dto.output.PageResponse;

public interface BaseService<T, U> extends RootService<T, U>{
	PageResponse<T> paginate(String filter, Integer page, Integer perPage, String sortBy, Direction direction);
}
