package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.StrategyDto;
import br.com.wsss.tramonto.entity.Strategy;

@Mapper(componentModel= "spring")
public interface StrategyMapper extends BaseMapper<StrategyDto, Strategy>{
	StrategyDto strategyToDto(Strategy entity);
	Strategy strategyDtoToStrategy(StrategyDto dto);
}
