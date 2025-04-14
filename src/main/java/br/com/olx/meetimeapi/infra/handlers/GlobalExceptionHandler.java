package br.com.olx.meetimeapi.infra.handlers;

import br.com.olx.meetimeapi.infra.exceptions.HubspotApiException;
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

  @ExceptionHandler(HubspotApiException.class)
  public ResponseEntity<ErrorResponse> handleHubspotApiException(HubspotApiException ex) {
    return ResponseEntity
        .status(ex.getStatusCode())
        .body(new ErrorResponse(ex.getErrorMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    return ResponseEntity
        .status(500)
        .body(new ErrorResponse("An unexpected error occurred"));
  }

  public record ErrorResponse(String message) {}
}
