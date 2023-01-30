package br.com.wsss.tramonto.dto.input;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseDto {

	private String firstName;
	private String lastName;
	private String email;
	private Set<RoleDto> roles;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

}
