package br.com.olx.meetimeapi.infra.handlers;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RequestNotPermitted.class)
  public ResponseEntity<ErrorResponse> handleRateLimitExceeded(RequestNotPermitted ex) {
    return ResponseEntity
        .status(HttpStatus.TOO_MANY_REQUESTS)
        .body(new ErrorResponse("Rate limit exceeded. Please try again later."));
  }

  public record ErrorResponse(String message) {}
}
