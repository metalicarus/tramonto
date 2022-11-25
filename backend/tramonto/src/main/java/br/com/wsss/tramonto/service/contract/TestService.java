package br.com.wsss.tramonto.service.contract;

import java.util.UUID;

import org.springframework.data.domain.Sort.Direction;

import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.dto.output.PageResponse;

public interface TestService extends BaseService<TestDto, UUID> {
	PageResponse<TestDto> paginate(String filter, Integer page, Integer perPage, String sortBy, Direction direction);
}
