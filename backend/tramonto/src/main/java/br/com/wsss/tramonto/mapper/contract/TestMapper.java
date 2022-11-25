package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.dto.input.TestTypeDto;
import br.com.wsss.tramonto.entity.Test;

@Mapper(componentModel = "spring", uses = {TestTypeDto.class})
public interface TestMapper extends BaseMapper<TestDto, Test>{
	TestDto testToTestDto(Test entity);
	Test testDtoToTest(TestDto dto);
	
	@Mapping(source = "type", ignore = true, target = "type")
	TestDto toPage(Test entity);

}
