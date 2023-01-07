package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.entity.Test;
import br.com.wsss.tramonto.mapper.contract.pk.TestChecklistPkMapper;

@Mapper(componentModel = "spring", uses = { TestChecklistPkMapper.class, ChecklistMapper.class,
		TestObjectiveMapper.class })
public interface TestMapper {

	@Mapping(source = "checklists", target = "checklists")
	TestDto testToTestDto(Test entity);
	
	@Mapping(source = "checklists", target = "checklists")
	Test testDtoToTest(TestDto dto);

	@Mapping(source = "type", ignore = true, target = "type")
	@Mapping(source = "objectives", ignore = true, target = "objectives")
	@Mapping(source = "checklists", ignore = true, target = "checklists")
	TestDto toPage(Test entity);
}