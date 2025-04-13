package br.com.olx.meetimeapi.application.port.in;

import br.com.olx.meetimeapi.application.dto.hubspot.auth.ExchangeHubspotCodeResponse;

public interface ExchangeHubspotCodeInteractor {
  ExchangeHubspotCodeResponse execute(String code);
}
