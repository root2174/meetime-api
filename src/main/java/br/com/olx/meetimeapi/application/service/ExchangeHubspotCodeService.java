package br.com.olx.meetimeapi.application.service;

import br.com.olx.meetimeapi.application.dto.hubspot.auth.ExchangeHubspotCodeResponse;
import br.com.olx.meetimeapi.application.port.in.ExchangeHubspotCodeInteractor;
import br.com.olx.meetimeapi.application.port.out.rest.HubspotClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExchangeHubspotCodeService implements ExchangeHubspotCodeInteractor {

  private final HubspotClient hubspotClient;

  @Override
  public ExchangeHubspotCodeResponse execute(String code) {
    return hubspotClient.exchangeHubspotCodeResponse(code);
  }
}
