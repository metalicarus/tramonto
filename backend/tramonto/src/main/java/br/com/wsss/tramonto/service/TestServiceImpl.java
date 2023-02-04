package br.com.wsss.tramonto.service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.wsss.tramonto.domain.type.Status;
import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.dto.input.TestVectorDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.entity.Test;
import br.com.wsss.tramonto.entity.TestVector;
import br.com.wsss.tramonto.entity.User;
import br.com.wsss.tramonto.mapper.contract.TestMapper;
import br.com.wsss.tramonto.mapper.contract.TestVectorMapper;
import br.com.wsss.tramonto.repository.contract.jpa.TestChecklistRepository;
import br.com.wsss.tramonto.repository.contract.jpa.TestObjectiveRepository;
import br.com.wsss.tramonto.repository.contract.jpa.TestRepository;
import br.com.wsss.tramonto.repository.contract.jpa.TestStrategyRepository;
import br.com.wsss.tramonto.repository.contract.jpa.TestTesterRepository;
import br.com.wsss.tramonto.repository.contract.jpa.TestVectorRepository;
import br.com.wsss.tramonto.service.contract.TestService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl<C> implements TestService {

	private final UserService userService;
	private final TestObjectiveRepository objectRepository;
	private final TestChecklistRepository checklistRepository;
	private final TestStrategyRepository strategyRepository;
	private final TestVectorRepository vectorRepository;
	private final TestTesterRepository testerRepository;
	private final TestRepository repository;
	private final TestMapper mapper;
	private final TestVectorMapper vectorMapper;

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
		externalEntity.preUpdate();
		Test dbEntity = repository.findById(dto.getId()).get();
		deleteOrphanRelationships(dbEntity.getChecklists(), externalEntity.getChecklists(), checklistRepository);
		deleteOrphanRelationships(dbEntity.getStrategies(), externalEntity.getStrategies(), strategyRepository);
		deleteOrphanRelationships(dbEntity.getObjectives(), externalEntity.getObjectives(), objectRepository);
		deleteOrphanRelationships(dbEntity.getTesters(), externalEntity.getTesters(), testerRepository);
		deleteOrphanRelationships(dbEntity.getVectors(), externalEntity.getVectors(), vectorRepository);
		externalEntity.setOwner(userService.getCurrentUser());
		externalEntity.getVectors().forEach(x -> {
			if (x.getOwner() == null) {
				x.setOwner(userService.getCurrentUser());
			}
		});
		repository.save(externalEntity);
		return mapper.testToTestDto(externalEntity);
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
		entity.setOwner(userService.getCurrentUser());
		entity.getVectors().forEach(x -> {
			x.setOwner(userService.getCurrentUser());
		});
		repository.save(entity);
		return mapper.testToTestDto(entity);
	}

	@Override
	public PageResponse<TestDto> paginate(String filter, Integer page, Integer perPage, String sortBy,
			Direction direction) {
		User currentUser = userService.getCurrentUser();
		Pageable request = PageRequest.of(page, perPage, Sort.by(direction, sortBy));
		Page<Test> pages = repository.findByUser(currentUser.getId().toString(), filter, request);
		return new PageResponse<TestDto>(pages.getContent().stream().map(x -> {
			x.setBelongsToCurrentUser(x.getOwner().getId().equals(currentUser.getId()));
			return mapper.toPage(x);
		}).collect(Collectors.toList()), request, pages.getTotalElements(), pages.getNumber());
	}

	@Override
	public void active(UUID id, Status status) {
		// TODO Auto-generated method stub

	}

	private <T, K> void deleteOrphanRelationships(Set<T> databaseEntities, Set<T> externalEntities,
			JpaRepository<T, K> repos) {
		databaseEntities.forEach(db -> {
			if (!externalEntities.contains(db)) {
				repos.delete(db);
			}
		});
	}

	@Override
	public TestVectorDto addTestVector(UUID testId, TestVectorDto dto) {
		Test t = repository.findById(testId).orElseThrow(() -> new EntityNotFoundException("Not found Test with Identification: " + testId));
		TestVector entity = vectorMapper.dtoToEntity(dto);
		entity.setOwner(userService.getCurrentUser());
		entity.setTest(t);
		return vectorMapper.entityToDto(vectorRepository.save(entity));
	}
}
