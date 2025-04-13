package br.com.olx.meetimeapi.application.port.out.rest;

import br.com.olx.meetimeapi.application.dto.ExchangeHubspotCodeResponse;

public interface HubspotClient {
  ExchangeHubspotCodeResponse exchangeHubspotCodeResponse(String code);
}
