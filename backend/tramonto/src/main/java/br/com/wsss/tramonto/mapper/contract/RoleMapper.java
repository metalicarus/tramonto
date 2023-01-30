package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.RoleDto;
import br.com.wsss.tramonto.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	Role roleDtoToEntity(RoleDto dto);
	RoleDto entityToRoleDto(Role entity);
}
