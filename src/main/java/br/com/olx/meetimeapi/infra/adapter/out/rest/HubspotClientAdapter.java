package br.com.olx.meetimeapi.infra.adapter.out.rest;

import br.com.olx.meetimeapi.application.dto.hubspot.auth.ExchangeHubspotCodeResponse;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactResponse;
import br.com.olx.meetimeapi.application.port.out.rest.HubspotClient;
import br.com.olx.meetimeapi.infra.client.hubspot.ExchangeHubspotCodeForTokenRequest;
import br.com.olx.meetimeapi.infra.client.hubspot.HubspotFeignClient;
import br.com.olx.meetimeapi.infra.exceptions.HubspotApiException;
import br.com.olx.meetimeapi.infra.service.RateLimiterService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class HubspotClientAdapter implements HubspotClient {
  private final HubspotFeignClient hubspotFeignClient;

  @Value("${hubspot.token-grant-type}")
  private String tokenGrantType;

  @Value("${hubspot.client-secret}")
  private String clientSecret;

  @Value("${hubspot.client-id}")
  private String clientId;

  @Value("${hubspot.token-redirect-uri}")
  private String tokenRedirectUri;

  private final RateLimiterService rateLimitService;

  @Override
  public ExchangeHubspotCodeResponse exchangeHubspotCodeResponse(String code) {
    var request = ExchangeHubspotCodeForTokenRequest.builder()
        .code(code)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .redirectUri(tokenRedirectUri)
        .grantType(tokenGrantType)
        .build();

    try{
      log.info("Making a request to the Hubspot API to exchange code {}", code);
      var response = hubspotFeignClient.exchangeCodeForToken(request);
      log.info("Code {} exchanged successfully!", code);

      return ExchangeHubspotCodeResponse.builder()
          .accessToken(response.accessToken())
          .refreshToken(response.refreshToken())
          .expiresIn(response.expiresIn())
          .build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CreateContactResponse createContact(CreateContactRequest request) {
    try {
      return rateLimitService.executeWithRateLimit(
          () -> {
            try {
              var response = hubspotFeignClient.createContact(request.authorization(), request);
              log.info("Created a new Hubspot contact {}", response.id());
              return response;
            } catch (FeignException.Unauthorized e) {
              log.error("Unauthorized access to HubSpot API. Please check your access token.");
              throw new HubspotApiException(401, "Invalid or expired access token");
            } catch (FeignException e) {
              log.error("Error calling HubSpot API: {}", e.getMessage());
              throw new HubspotApiException(e.status(), e.getMessage());
            }
          });
    } catch (Exception e) {
      log.error("Error creating HubSpot contact", e);
      throw new RuntimeException("Failed to create contact in HubSpot", e);
    }
  }
}
