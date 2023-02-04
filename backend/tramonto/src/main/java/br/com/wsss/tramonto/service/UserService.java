package br.com.wsss.tramonto.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.wsss.tramonto.dto.input.UserDto;
import br.com.wsss.tramonto.entity.User;
import br.com.wsss.tramonto.mapper.contract.UserMapper;
import br.com.wsss.tramonto.repository.contract.jpa.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final UserMapper mapper;

	public List<UserDto> findDifferentUsers() {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repository.findDifferentTesters(currentUser.getId().toString(), "TESTER").stream().map(x -> {
			return mapper.entityToUserDto(x);
		}).toList();
	}
	
	public User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
