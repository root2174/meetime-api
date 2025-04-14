package br.com.olx.meetimeapi.infra.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {
  @Bean
  public RateLimiter hubspotRateLimiter() {
    RateLimiterConfig config = RateLimiterConfig.custom()
        .limitForPeriod(100)
        .limitRefreshPeriod(Duration.ofSeconds(10))
        .timeoutDuration(Duration.ofSeconds(5))
        .build();

    return RateLimiterRegistry.of(config).rateLimiter("hubspotRateLimiter");
  }
}
