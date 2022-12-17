package br.com.wsss.tramonto.mapper.contract.pk;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.wsss.tramonto.dto.input.ChecklistDto;
import br.com.wsss.tramonto.dto.input.pk.TestChecklistPkDto;
import br.com.wsss.tramonto.entity.Checklist;
import br.com.wsss.tramonto.entity.TestChecklist;
import br.com.wsss.tramonto.entity.pk.TestChecklistPk;
import br.com.wsss.tramonto.mapper.contract.TestChecklistMapper;
import br.com.wsss.tramonto.mapper.contract.TestMapper;

@Mapper(componentModel = "spring", uses = { TestMapper.class, TestChecklistMapper.class })
public interface TestChecklistPkMapper {
	
	@Mapping(source = "pk.checklist.id", target= "id")
	@Mapping(source = "pk.checklist.status", target= "status")
	@Mapping(source = "pk.checklist.createdAt", target= "createdAt")
	@Mapping(source = "pk.checklist.updatedAt", target= "updatedAt")
	@Mapping(source = "pk.checklist.checklist", target= "checklist")
	@Mapping(source = "pk.checklist.description", target= "description")
	@Mapping(source = "pk.checklist.type", target= "type")
	@Mapping(source = "pk.checklist.default", target= "default")
	ChecklistDto toChecklist(TestChecklist entity);
	
	@Mapping(target = "pk.checklist.id", source= "id")
	@Mapping(target = "pk.checklist.status", source= "status")
	@Mapping(target = "pk.checklist.createdAt", source= "createdAt")
	@Mapping(target = "pk.checklist.updatedAt", source= "updatedAt")
	@Mapping(target = "pk.checklist.checklist", source= "checklist")
	@Mapping(target = "pk.checklist.description", source= "description")
	@Mapping(target = "pk.checklist.type", source= "type")
	@Mapping(target = "pk.checklist.default", source= "default")
	TestChecklist toTestChecklist(ChecklistDto dto);

	@Mapping(source = "test", ignore = true, target = "test")
	@Mapping(source = "checklist", ignore = true, target = "checklist")
	TestChecklistPkDto testChecklistPkToDto(TestChecklistPk entity);

	@Mapping(source = "test", ignore = true, target = "test")
	TestChecklistPk testChecklistPkDtoToEntity(TestChecklistPkDto dto);
	
	default TestChecklistPk fromId(UUID id) {
		if (id == null) return null;
		TestChecklistPk pk = new TestChecklistPk();
		Checklist cl = new Checklist();
		cl.setId(id);
		pk.setChecklist(cl);
		return pk;
	}
}
