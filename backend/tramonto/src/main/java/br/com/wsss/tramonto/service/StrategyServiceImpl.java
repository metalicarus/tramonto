package br.com.wsss.tramonto.service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.wsss.tramonto.domain.type.Status;
import br.com.wsss.tramonto.dto.input.StrategyDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.entity.Strategy;
import br.com.wsss.tramonto.mapper.contract.StrategyMapper;
import br.com.wsss.tramonto.repository.contract.jpa.StrategyRepository;
import br.com.wsss.tramonto.service.contract.StrategyService;

@Service
public class StrategyServiceImpl implements StrategyService {
	
	@Autowired
	private StrategyRepository repository;
	
	@Autowired
	private StrategyMapper mapper;

	@Override
	public Set<StrategyDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StrategyDto findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StrategyDto update(StrategyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StrategyDto save(StrategyDto dto) {
		return mapper.strategyToDto(repository.save(mapper.strategyDtoToStrategy(dto)));
	}

	@Override
	public void active(UUID id, Status status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageResponse<StrategyDto> paginate(String filter, Integer page, Integer perPage, String sortBy,
			Direction direction) {
		Pageable request = PageRequest.of(page, perPage);
		Page<Strategy> pages = repository.findByStrategyContaining(filter, request);
		return new PageResponse<StrategyDto>(pages.getContent().stream().map(x -> {
			return mapper.strategyToDto(x);
		}).collect(Collectors.toList()), request, pages.getTotalElements(), pages.getNumber());
	}
	
}
