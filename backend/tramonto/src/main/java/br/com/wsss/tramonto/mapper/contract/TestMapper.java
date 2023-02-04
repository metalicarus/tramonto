package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.entity.Test;
import br.com.wsss.tramonto.mapper.contract.pk.TestChecklistPkMapper;
import br.com.wsss.tramonto.mapper.contract.pk.TestStrategyPkMapper;
import br.com.wsss.tramonto.mapper.contract.pk.TestTesterPkMapper;

@Mapper(componentModel = "spring", uses = { TestChecklistPkMapper.class, ChecklistMapper.class,
		TestStrategyPkMapper.class, StrategyMapper.class, TestObjectiveMapper.class, TestTesterPkMapper.class })
public interface TestMapper {

	@Mapping(source = "strategies", target = "strategies")
	@Mapping(source = "checklists", target = "checklists")
	@Mapping(source = "testers", target = "testers")
	TestDto testToTestDto(Test entity);

	@Mapping(source = "strategies", target = "strategies")
	@Mapping(source = "checklists", target = "checklists")
	@Mapping(source = "testers", target = "testers")
	Test testDtoToTest(TestDto dto);

	@Mapping(source = "type", ignore = true, target = "type")
	@Mapping(source = "objectives", ignore = true, target = "objectives")
	@Mapping(source = "checklists", ignore = true, target = "checklists")
	@Mapping(source = "strategies", ignore = true, target = "strategies")
	@Mapping(source = "testers", ignore = true, target = "testers")
	@Mapping(source = "vectors", ignore = true, target = "vectors")
	TestDto toPage(Test entity);
}