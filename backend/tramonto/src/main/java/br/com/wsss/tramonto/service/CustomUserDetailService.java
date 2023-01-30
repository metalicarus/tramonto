package br.com.wsss.tramonto.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.wsss.tramonto.entity.CustomUserDetails;
import br.com.wsss.tramonto.entity.User;
import br.com.wsss.tramonto.repository.contract.jpa.UserRepository;
import lombok.RequiredArgsConstructor;

@Service("customUserDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
        if (user.isPresent()) {
        	return new CustomUserDetails(user.get());     
        }
        throw new UsernameNotFoundException("No user present with username: " + username);
	}
}
