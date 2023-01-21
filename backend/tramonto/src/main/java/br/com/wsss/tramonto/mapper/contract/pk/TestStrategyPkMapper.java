package br.com.wsss.tramonto.mapper.contract.pk;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.wsss.tramonto.dto.input.StrategyDto;
import br.com.wsss.tramonto.dto.input.pk.TestStrategyPkDto;
import br.com.wsss.tramonto.entity.TestStrategy;
import br.com.wsss.tramonto.entity.pk.TestStrategyPk;
import br.com.wsss.tramonto.mapper.contract.TestMapper;
import br.com.wsss.tramonto.mapper.contract.TestStrategyMapper;

@Mapper(componentModel = "spring", uses = { TestMapper.class, TestStrategyMapper.class })
public interface TestStrategyPkMapper {
	
	@Mapping(source = "pk.strategy.id", target= "id")
	@Mapping(source = "pk.strategy.strategy", target= "strategy")
	@Mapping(source = "pk.strategy.description", target= "description")
	@Mapping(source = "pk.strategy.status", target= "status")
	@Mapping(source = "pk.strategy.createdAt", target= "createdAt")
	@Mapping(source = "pk.strategy.updatedAt", target= "updatedAt")
	StrategyDto toStrategy(TestStrategy entity);
	
	@Mapping(target = "pk.strategy.id", source= "id")
	@Mapping(target = "pk.strategy.strategy", source= "strategy")
	@Mapping(target = "pk.strategy.description", source= "description")
	@Mapping(target = "pk.strategy.status", source= "status")
	@Mapping(target = "pk.strategy.createdAt", source= "createdAt")
	@Mapping(target = "pk.strategy.updatedAt", source= "updatedAt")
	TestStrategy toTestStrategy(StrategyDto dto);
	
	@Mapping(source = "test", ignore = true, target = "test")
	@Mapping(source = "strategy", ignore = true, target = "strategy")
	TestStrategyPkDto testStrategyPkToDto(TestStrategyPk entity);
	
	@Mapping(source = "test", ignore = true, target = "test")
	TestStrategyPk testStrategyPkDtoToEntity(TestStrategyPkDto dto);

}
