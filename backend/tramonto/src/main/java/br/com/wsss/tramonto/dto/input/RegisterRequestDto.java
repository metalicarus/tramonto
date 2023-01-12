package br.com.wsss.tramonto.dto.input;

import java.util.Set;

import br.com.wsss.tramonto.domain.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Set<Role> roles;
}
