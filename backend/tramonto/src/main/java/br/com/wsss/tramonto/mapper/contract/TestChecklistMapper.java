package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.TestChecklistDto;
import br.com.wsss.tramonto.entity.TestChecklist;
import br.com.wsss.tramonto.mapper.contract.pk.TestChecklistPkMapper;

@Mapper(componentModel = "spring", uses = { TestChecklistPkMapper.class })
public interface TestChecklistMapper {
	TestChecklistDto testChecklistToDto(TestChecklist entity);

	TestChecklist testChecklistDtoToEntity(TestChecklistDto dto);
}
