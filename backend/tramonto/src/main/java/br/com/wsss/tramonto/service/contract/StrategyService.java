package br.com.wsss.tramonto.service.contract;

import java.util.UUID;

import org.springframework.data.domain.Sort.Direction;

import br.com.wsss.tramonto.dto.input.StrategyDto;
import br.com.wsss.tramonto.dto.output.PageResponse;

public interface StrategyService extends BaseService<StrategyDto, UUID>{
	PageResponse<StrategyDto> paginate(String filter, Integer page, Integer perPage, String sortBy, Direction direction);
}
