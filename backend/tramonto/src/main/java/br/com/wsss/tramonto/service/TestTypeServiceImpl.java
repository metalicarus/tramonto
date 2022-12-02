package br.com.wsss.tramonto.service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.wsss.tramonto.domain.type.Status;
import br.com.wsss.tramonto.dto.input.TestTypeDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.entity.TestType;
import br.com.wsss.tramonto.mapper.contract.TestTypeMapper;
import br.com.wsss.tramonto.repository.contract.jpa.TestTypeRepository;
import br.com.wsss.tramonto.service.contract.TestTypeService;

@Service
public class TestTypeServiceImpl implements TestTypeService {
	
	@Autowired
	private TestTypeRepository repository;
	
	@Autowired
	private TestTypeMapper mapper;

	@Override
	public TestTypeDto findById(UUID id) {
		return mapper.testTypeToTestTypeDto(repository.findById(id).get());
	}

	@Override
	public TestTypeDto update(TestTypeDto dto) {
		return mapper.testTypeToTestTypeDto(repository.save(mapper.testTypeDtoToTestType(dto)));
	}

	@Override
	public TestTypeDto save(TestTypeDto dto) {
		return mapper.testTypeToTestTypeDto(repository.save(mapper.testTypeDtoToTestType(dto)));
	}

	@Override
	public void active(UUID id, Status status) {
		TestType entity = repository.findById(id).get();
		entity.setStatus(status);
		repository.save(entity);
	}
	
	@Override
	public Set<TestTypeDto> findAll() {
		return repository.findAll().stream().map(x -> {
			return mapper.testTypeToTestTypeDto(x);
		}).collect(Collectors.toSet());
	}

	@Override
	public PageResponse<TestTypeDto> paginate(String filter, Integer page, Integer perPage, String sortBy,
			Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}
}
