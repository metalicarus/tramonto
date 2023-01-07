package br.com.wsss.tramonto.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Transactional
	@Override
	public TestDto update(TestDto dto) {
		Test externalEntity = mapper.testDtoToTest(dto);
		repository.deleteAllObjectives(externalEntity.getId());
		repository.deleteAllChecklists(externalEntity.getId());
		Test databaseEntity = repository.findById(externalEntity.getId()).get();
		databaseEntity.setStatus(externalEntity.getStatus());
		databaseEntity.setInitialDate(externalEntity.getInitialDate());
		databaseEntity.setFinalDate(externalEntity.getFinalDate());
		databaseEntity.setEstimatedTime(externalEntity.getEstimatedTime());
		databaseEntity.setRetest(externalEntity.isRetest());
		databaseEntity.setRetestDate(externalEntity.getRetestDate());
		databaseEntity.setTitle(externalEntity.getTitle());
		databaseEntity.setDescription(externalEntity.getDescription());
		databaseEntity.setApproach(externalEntity.getApproach());
		databaseEntity.setAggression(externalEntity.getAggression());
		databaseEntity.setGeneralObservation(externalEntity.getGeneralObservation());
		databaseEntity.setChecklists(externalEntity.getChecklists());
		databaseEntity.addObjectives(externalEntity.getObjectives());
		repository.save(databaseEntity);
		return mapper.testToTestDto(externalEntity);
	}

	@Transactional
	@Override
	public TestDto save(TestDto dto) {
		Test entity = mapper.testDtoToTest(dto);
		if (entity.getIdentifier().isEmpty()) {
			entity.setStatus(Status.ACTIVE);
			String identifier = "TR" + String.format("%8s", repository.count() + 1).replace(' ', '0');
			entity.setIdentifier(identifier);
		}
		if (entity.getInitialDate() == null)
			entity.setInitialDate(new Date());
		Test dbEntity = repository.findById(entity.getId()).get();
		dbEntity.getChecklists().clear();
		dbEntity.setChecklists(entity.getChecklists());
		repository.save(dbEntity);
		return mapper.testToTestDto(dbEntity);
	}

	@Override
	public PageResponse<TestDto> paginate(String filter, Integer page, Integer perPage, String sortBy,
			Direction direction) {
		Pageable request = PageRequest.of(page, perPage, Sort.by(Direction.DESC, "createdAt"));
		Page<Test> pages = repository.findByTitleContaining(filter, request);
		return new PageResponse<TestDto>(pages.getContent().stream().map(x -> {
			return mapper.toPage(x);
		}).collect(Collectors.toList()), request, pages.getTotalElements(), pages.getNumber());
	}

	@Override
	public void active(UUID id, Status status) {
		// TODO Auto-generated method stub

	}

}
