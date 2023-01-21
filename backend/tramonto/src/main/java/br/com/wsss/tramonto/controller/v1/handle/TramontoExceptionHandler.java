package br.com.wsss.tramonto.controller.v1.handle;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.wsss.tramonto.exception.TramontoException;

@RestControllerAdvice
public class TramontoExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { TramontoException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<TramontoStandardError> tramontoJwt(TramontoException exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(TramontoStandardError.builder().status(HttpStatus.UNAUTHORIZED.value())
						.message("Expired credentials").error("Your credentials have expired, please log in again")
						.timestamp(Instant.now()).build());
	}

	public static TramontoStandardError generateInvalidTokenException(HttpServletRequest request) {
		return TramontoStandardError.builder().status(HttpStatus.UNAUTHORIZED.value()).message("Invalid credentials")
				.error("Your credentials are invalid or non-existent, please login again").timestamp(Instant.now())
				.path(request.getRequestURI()).build();
	}

	public static TramontoStandardError generateJwtExpirationException(HttpServletRequest request) {
		return TramontoStandardError.builder().status(HttpStatus.UNAUTHORIZED.value()).message("Expired credentials")
				.error("Your credentials have expired, please log in again").timestamp(Instant.now())
				.path(request.getRequestURI()).build();
	}
}
