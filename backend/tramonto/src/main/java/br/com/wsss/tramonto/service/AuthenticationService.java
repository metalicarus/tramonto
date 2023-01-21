package br.com.wsss.tramonto.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wsss.tramonto.configuration.JwtService;
import br.com.wsss.tramonto.dto.input.AuthenticationRequestDto;
import br.com.wsss.tramonto.dto.input.AuthenticationResponseDto;
import br.com.wsss.tramonto.dto.input.RegisterRequestDto;
import br.com.wsss.tramonto.entity.Role;
import br.com.wsss.tramonto.entity.User;
import br.com.wsss.tramonto.repository.contract.jpa.RoleRepository;
import br.com.wsss.tramonto.repository.contract.jpa.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public User findUserByToken(String token) {
		String jwt = (token.contains("Bearer")) ? token.substring(7) : token;
		String userEmail = jwtService.extractUsername(jwt);	
		return userRepository.findByEmail(userEmail).get();
	}

	public AuthenticationResponseDto register(RegisterRequestDto request) {
		Set<Role> roles = new HashSet<>();
		request.getRoles().forEach(row -> {
			Optional<Role> role = roleRepository.findByRole(row);
			if (role.isPresent())
				roles.add(role.get());
		});
		User user = User.builder()
						.firstName(request.getFirstname())
						.lastName(request.getLastname())
						.email(request.getEmail())
						.password(passwordEncoder.encode(request.getPassword()))
						.roles(roles).build();
		userRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponseDto.builder().token(jwtToken).build();
	}
	public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponseDto.builder().token(jwtToken).build();
	}
}
