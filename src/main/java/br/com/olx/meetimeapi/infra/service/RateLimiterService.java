package br.com.olx.meetimeapi.infra.service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateLimiterService {
  private final RateLimiter rateLimiter;

  public <T> T executeWithRateLimit(Supplier<T> operation) {
    try {
      return RateLimiter.decorateSupplier(rateLimiter, operation).get();
    } catch (RequestNotPermitted e) {
      log.warn("Rate limit exceeded. Waiting for next refresh period.");
      throw new RuntimeException("Rate limit exceeded. Please try again later.", e);
    }
  }
}
