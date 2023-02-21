package br.com.wsss.tramonto.controller.v1.handle;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.wsss.tramonto.exception.TramontoException;

@RestControllerAdvice
public class TramontoExceptionHandler {

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<TramontoStandardError> tramontoJwt(HttpMessageNotReadableException exception,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(TramontoStandardError.builder().status(HttpStatus.BAD_REQUEST.value())
						.message(exception.getMessage()).error(exception.getCause().getMessage())
						.timestamp(Instant.now()).build());
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<TramontoStandardError> tramontoJwt(Exception exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(TramontoStandardError.builder().status(HttpStatus.BAD_REQUEST.value())
						.message(exception.getMessage()).error(exception.getCause().getMessage())
						.timestamp(Instant.now()).build());
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<TramontoStandardError> tramontoJwt(DataIntegrityViolationException exception,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(TramontoStandardError.builder().status(HttpStatus.BAD_REQUEST.value())
						.message(exception.getMessage()).error(exception.getCause().getMessage())
						.timestamp(Instant.now()).build());
	}

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
