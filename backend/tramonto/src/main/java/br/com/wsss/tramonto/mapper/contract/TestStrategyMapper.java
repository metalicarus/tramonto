package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.TestStrategyDto;
import br.com.wsss.tramonto.entity.TestStrategy;
import br.com.wsss.tramonto.mapper.contract.pk.TestStrategyPkMapper;

@Mapper(componentModel = "spring", uses = { TestStrategyPkMapper.class })
public interface TestStrategyMapper {
	TestStrategyDto testStrategyToDto(TestStrategy entity);
	TestStrategy testStrategyDtoToEntity(TestStrategyDto dto);
}
