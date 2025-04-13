package br.com.olx.meetimeapi.infra.client.hubspot;

import br.com.olx.meetimeapi.infra.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hubspot", url = "${hubspot.hub-api-base-url}", configuration = FeignConfig.class)
public interface HubspotFeignClient {
  @PostMapping(value = "${hubspot.token-endpoint}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  ExchangeHubspotCodeForTokenResponse exchangeCodeForToken(@RequestBody ExchangeHubspotCodeForTokenRequest form);
}