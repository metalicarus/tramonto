package br.com.wsss.tramonto.service.contract;

import java.util.Set;

import org.springframework.data.domain.Sort.Direction;

import br.com.wsss.tramonto.domain.type.Status;
import br.com.wsss.tramonto.dto.output.PageResponse;

public interface BaseService<T, U> {
	Set<T> findAll();
	T findById(U id);
	T update(T dto);
	T save(T dto);
	void active(U id, Status status);
	PageResponse<T> paginate(String filter, Integer page, Integer perPage, String sortBy, Direction direction);

}
