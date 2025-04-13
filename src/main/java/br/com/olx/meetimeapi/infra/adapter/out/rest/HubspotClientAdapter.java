package br.com.olx.meetimeapi.infra.adapter.out.rest;

import br.com.olx.meetimeapi.application.dto.ExchangeHubspotCodeResponse;
import br.com.olx.meetimeapi.application.port.out.rest.HubspotClient;
import br.com.olx.meetimeapi.infra.client.hubspot.ExchangeHubspotCodeForTokenRequest;
import br.com.olx.meetimeapi.infra.client.hubspot.HubspotFeignClient;
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
      throw new RuntimeException(e);
    }
  }
}
