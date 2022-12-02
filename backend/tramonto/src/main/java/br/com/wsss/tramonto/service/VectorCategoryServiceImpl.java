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
import br.com.wsss.tramonto.dto.input.VectorCategoryDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.entity.VectorCategory;
import br.com.wsss.tramonto.mapper.contract.VectorCategoryMapper;
import br.com.wsss.tramonto.repository.contract.jpa.VectorCategoryRepository;
import br.com.wsss.tramonto.service.contract.BaseService;

@Service
public class VectorCategoryServiceImpl implements BaseService<VectorCategoryDto, UUID> {
	
	@Autowired
	private VectorCategoryRepository repository;
	
	@Autowired
	private VectorCategoryMapper mapper;

	@Override
	public Set<VectorCategoryDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorCategoryDto findById(UUID id) {
		return mapper.vectorCategoryToDto(repository.findById(id).get());
	}

	@Override
	public VectorCategoryDto update(VectorCategoryDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorCategoryDto save(VectorCategoryDto dto) {
		return mapper.vectorCategoryToDto(repository.save(mapper.vectorCategoryDtoToEntity(dto)));
	}

	@Override
	public void active(UUID id, Status status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageResponse<VectorCategoryDto> paginate(String filter, Integer page, Integer perPage, String sortBy,
			Direction direction) {
		Pageable request = PageRequest.of(page, perPage);
		Page<VectorCategory> pages = repository.findByVectorCategoryContaining(filter, request);
		return new PageResponse<VectorCategoryDto>(pages.getContent().stream().map(x -> {
			return mapper.vectorCategoryToDto(x);
		}).collect(Collectors.toList()), request, pages.getTotalElements(), pages.getNumber());
	}
}
