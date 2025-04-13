package br.com.olx.meetimeapi.application.port.in;

import br.com.olx.meetimeapi.application.dto.hubspot.auth.GetHubspotAuthUrlResponse;

public interface GetHubspotAuthUrlInteractor  {
  GetHubspotAuthUrlResponse execute();
}
