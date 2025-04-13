package br.com.olx.meetimeapi.infra.client.hubspot;

import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactResponse;
import br.com.olx.meetimeapi.infra.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "hubspot", url = "${hubspot.hub-api-base-url}", configuration = FeignConfig.class)
public interface HubspotFeignClient {
  @PostMapping(value = "${hubspot.token-endpoint}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  ExchangeHubspotCodeForTokenResponse exchangeCodeForToken(@RequestBody ExchangeHubspotCodeForTokenRequest form);

  @PostMapping("/crm/v3/objects/contacts")
  CreateContactResponse createContact(
      @RequestHeader("Authorization") String authorization,
      @RequestBody CreateContactRequest request
  );
}