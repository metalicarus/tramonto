package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.mapper.contract.pk.TestTesterPkMapper;

@Mapper(componentModel = "spring", uses = { TestTesterPkMapper.class })
public interface TestTesterMapper {
}
