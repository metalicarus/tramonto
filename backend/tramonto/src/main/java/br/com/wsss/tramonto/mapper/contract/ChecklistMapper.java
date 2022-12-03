package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.ChecklistDto;
import br.com.wsss.tramonto.entity.Checklist;

@Mapper(componentModel= "spring")
public interface ChecklistMapper extends BaseMapper<ChecklistDto, Checklist>{
	
	ChecklistDto checklistToDto(Checklist entity);
	Checklist checklistDtoToEntity(ChecklistDto dto);
}
