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
import br.com.wsss.tramonto.dto.input.ChecklistDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.entity.Checklist;
import br.com.wsss.tramonto.mapper.contract.ChecklistMapper;
import br.com.wsss.tramonto.repository.contract.jpa.ChecklistRepository;
import br.com.wsss.tramonto.service.contract.BaseService;

@Service
public class ChecklistServiceImpl implements BaseService<ChecklistDto, UUID> {
	
	@Autowired
	private ChecklistRepository repository;
	
	@Autowired
	private ChecklistMapper mapper;

	@Override
	public Set<ChecklistDto> findAll() {
		return repository.findAll().stream().map(x -> {
			return mapper.checklistToDto(x);
		}).collect(Collectors.toSet());
	}

	@Override
	public ChecklistDto findById(UUID id) {
		return mapper.checklistToDto(repository.findById(id).get());
	}

	@Override
	public ChecklistDto update(ChecklistDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChecklistDto save(ChecklistDto dto) {
		return mapper.checklistToDto(repository.save(mapper.checklistDtoToEntity(dto)));
	}

	@Override
	public void active(UUID id, Status status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageResponse<ChecklistDto> paginate(String filter, Integer page, Integer perPage, String sortBy,
			Direction direction) {
		Pageable request = PageRequest.of(page, perPage);
		Page<Checklist> pages = repository.findByChecklistContaining(filter, request);
		return new PageResponse<ChecklistDto>(pages.getContent().stream().map(x -> {
			return mapper.checklistToDto(x);
		}).collect(Collectors.toList()), request, pages.getTotalElements(), pages.getNumber());
	}
}
