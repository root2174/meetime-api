package br.com.olx.meetimeapi.infra.config;

import br.com.olx.meetimeapi.application.port.in.CreateHubspotContactInteractor;
import br.com.olx.meetimeapi.application.port.in.ExchangeHubspotCodeInteractor;
import br.com.olx.meetimeapi.application.port.in.GetHubspotAuthUrlInteractor;
import br.com.olx.meetimeapi.application.port.in.HubspotWebhookInteractor;
import br.com.olx.meetimeapi.application.service.CreateHubspotContactService;
import br.com.olx.meetimeapi.application.service.ExchangeHubspotCodeService;
import br.com.olx.meetimeapi.application.service.GetHubspotAuthUrlService;
import br.com.olx.meetimeapi.application.service.HubspotWebhookService;
import br.com.olx.meetimeapi.infra.adapter.out.rest.HubspotClientAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HubspotConfig {
  @Value("${hubspot.base-url}")
  private String hubspotBaseUrl;

  @Value("${hubspot.auth-endpoint}")
  private String hubspotAuthEndpoint;

  @Value("${hubspot.client-id}")
  private String clientId;

  @Value("${hubspot.auth-scopes}")
  private String scopes;

  @Value("${hubspot.auth-redirect-uri}")
  private String authRedirectUri;

  @Value("${hubspot.client-secret}")
  private String clientSecret;

  private final HubspotClientAdapter hubspotClientAdapter;

  @Bean
  public GetHubspotAuthUrlInteractor getHubspotAuthUrlInteractor() {
    return new GetHubspotAuthUrlService(hubspotBaseUrl, hubspotAuthEndpoint, clientId, scopes, authRedirectUri);
  }

  @Bean
  public ExchangeHubspotCodeInteractor getExchangeHubspotCodeInteractor() {
    return new ExchangeHubspotCodeService(hubspotClientAdapter);
  }

  @Bean
  public CreateHubspotContactInteractor getCreateHubspotContactInteractor() {
    return new CreateHubspotContactService(hubspotClientAdapter);
  }

  @Bean
  public HubspotWebhookInteractor getHubspotWebhookInteractor() {
    return new HubspotWebhookService(clientSecret);
  }
}
