package br.com.wsss.tramonto.controller.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsss.tramonto.dto.input.AuthenticationRequestDto;
import br.com.wsss.tramonto.dto.input.AuthenticationResponseDto;
import br.com.wsss.tramonto.dto.input.RegisterRequestDto;
import br.com.wsss.tramonto.dto.input.UserDto;
import br.com.wsss.tramonto.service.AuthenticationService;
import br.com.wsss.tramonto.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;
	private final UserService userService;
	
	@GetMapping("/differentUsers")
	public ResponseEntity<List<UserDto>> findDifferentUsers() {
		return ResponseEntity.ok(userService.findDifferentUsers());
	}
	
	@GetMapping
	public ResponseEntity<UserDto> findUserByToken(@RequestParam("token") String token) {
		return ResponseEntity.ok(service.findUserByToken(token));
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request) {
		return ResponseEntity.ok(service.register(request));
	}
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
		return ResponseEntity.ok(service.authenticate(request));
	}
}
