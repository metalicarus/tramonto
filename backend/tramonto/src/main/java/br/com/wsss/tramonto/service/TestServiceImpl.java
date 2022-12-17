package br.com.wsss.tramonto.service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import br.com.wsss.tramonto.repository.contract.jpa.TestChecklistRepository;
import br.com.wsss.tramonto.repository.contract.jpa.TestObjectiveRepository;
import br.com.wsss.tramonto.repository.contract.jpa.TestRepository;
import br.com.wsss.tramonto.service.contract.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestChecklistRepository checklistRepository;
	
	@Autowired
	private TestObjectiveRepository objetiveRepository;

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
		return mapper.testToTestDto(repository.findById(id).get());
	}

	@Override
	public TestDto update(TestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public TestDto save(TestDto dto) {
		Test entity = mapper.testDtoToTest(dto);
		entity.setStatus(Status.ACTIVE);
		String identifier = "TR" + String.format("%8s", repository.count() + 1).replace(' ', '0');
		entity.setIdentifier(identifier);
		if (entity.getInitialDate() == null)
			entity.setInitialDate(new Date());
		repository.save(entity);
		entity.getObjectives().forEach(x -> {
			x.setStatus(Status.ACTIVE);
			x.setTest(entity);
			x = objetiveRepository.save(x);
		});
		entity.getChecklists().forEach(x -> {
			x.getPk().setTest(entity);
			x = checklistRepository.save(x);
		});
		return mapper.testToTestDto(entity);
	}
	
	@Override
	public PageResponse<TestDto> paginate(String filter, Integer page, Integer perPage, String sortBy, Direction direction) {
		Pageable request = PageRequest.of(page, perPage);
		Page<Test> pages = repository.findByTitleContaining(filter, request);
		return new PageResponse<TestDto>(pages.getContent().stream().map(x -> {
			return mapper.toPage(x);
		}).collect(Collectors.toList()), request, pages.getTotalElements(), 
				pages.getNumber());
	}

	@Override
	public void active(UUID id, Status status) {
		// TODO Auto-generated method stub
		
	}
	
	
}
