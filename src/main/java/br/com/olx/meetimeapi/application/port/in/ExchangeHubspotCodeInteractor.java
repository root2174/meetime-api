package br.com.olx.meetimeapi.application.port.in;

import br.com.olx.meetimeapi.application.dto.ExchangeHubspotCodeResponse;

public interface ExchangeHubspotCodeInteractor {
  ExchangeHubspotCodeResponse execute(String code);
}
