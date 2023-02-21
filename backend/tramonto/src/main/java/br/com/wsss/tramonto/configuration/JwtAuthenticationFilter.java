package br.com.wsss.tramonto.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.wsss.tramonto.controller.v1.handle.TramontoExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private ObjectMapper objectMapper;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			String currentRoute = request.getRequestURI();
			if (!currentRoute.contains("/auth"))
				handleInvalidToken(request, response);
			filterChain.doFilter(request, response);
			return;
		}
		try {
			jwt = authHeader.substring(7);
			userEmail = jwtService.extractUsername(jwt);
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);				
				if (jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
			filterChain.doFilter(request, response);
		} 
		catch (ExpiredJwtException e) {
			handleExpirationToken(request, response);
			return;
		}
	}
	private void handleInvalidToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		objectMapper = JsonMapper.builder()
	            .addModule(new JavaTimeModule())
	            .build();
	    response.setContentType("application/json");
	    response.setStatus(HttpStatus.UNAUTHORIZED.value());
	    response.getWriter().write(objectMapper.writeValueAsString(TramontoExceptionHandler.generateInvalidTokenException(request)));
	}
	private void handleExpirationToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		objectMapper = JsonMapper.builder()
						            .addModule(new JavaTimeModule())
						            .build();
	    response.setContentType("application/json");
	    response.setStatus(HttpStatus.UNAUTHORIZED.value());
	    response.getWriter().write(objectMapper.writeValueAsString(TramontoExceptionHandler.generateJwtExpirationException(request)));
	}

}
