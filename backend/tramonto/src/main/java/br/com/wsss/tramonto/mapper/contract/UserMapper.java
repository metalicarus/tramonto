package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.UserDto;
import br.com.wsss.tramonto.entity.CustomUserDetails;
import br.com.wsss.tramonto.entity.User;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper {
	User userToUserDto(UserDto dto);
	UserDto entityToUserDto(User entity);
	UserDto customToUserDto(CustomUserDetails details);
}
