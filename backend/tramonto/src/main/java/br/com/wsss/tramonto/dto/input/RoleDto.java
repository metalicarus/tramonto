package br.com.wsss.tramonto.dto.input;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto extends BaseDto {
	
	private String authority;
	private String role;
	private Set<String> routes;
}
