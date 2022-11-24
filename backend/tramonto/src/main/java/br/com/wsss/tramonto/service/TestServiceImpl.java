package br.com.wsss.tramonto.service;

import java.util.Date;
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
import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.entity.Test;
import br.com.wsss.tramonto.mapper.contract.TestMapper;
import br.com.wsss.tramonto.repository.contract.jpa.TestRepository;
import br.com.wsss.tramonto.service.contract.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository repository;
	
	@Autowired
	private TestMapper mapper;

	@Override
	public Set<TestDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestDto findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestDto update(TestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestDto save(TestDto dto) {
		Test entity = mapper.testDtoToTest(dto);
		String identifier = "TR" + String.format("%8s", repository.count() + 1).replace(' ', '0');
		entity.setIdentifier(identifier);
		if (entity.getInitialDate() == null)
			entity.setInitialDate(new Date());
		return mapper.testToTestDto(repository.save(entity));
	}
	
	@Override
	public PageResponse<TestDto> paginate(String filter, Integer page, Integer perPage, String sortBy, Direction direction) {
		Pageable request = PageRequest.of(page, perPage);
		Page<Test> pages = repository.findByTitleContaining(filter, request);
		return new PageResponse<TestDto>(pages.getContent().stream().map(x -> {
			return mapper.testToTestDto(x);
		}).collect(Collectors.toList()), request, pages.getTotalElements(), 
				pages.getNumber());
	}

	@Override
	public void active(UUID id, Status status) {
		// TODO Auto-generated method stub
		
	}
	
	
}
