package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.TestObjectiveDto;
import br.com.wsss.tramonto.entity.TestObjective;

@Mapper(componentModel = "spring")
public interface TestObjectiveMapper {
	TestObjective testObjectiveDtoToEntity(TestObjectiveDto dto);
	TestObjectiveDto testObjectiveToDto(TestObjective entity);
}
