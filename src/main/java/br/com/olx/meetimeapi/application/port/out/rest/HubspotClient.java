package br.com.olx.meetimeapi.application.port.out.rest;

import br.com.olx.meetimeapi.application.dto.hubspot.auth.ExchangeHubspotCodeResponse;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactResponse;

public interface HubspotClient {
  ExchangeHubspotCodeResponse exchangeHubspotCodeResponse(String code);

  CreateContactResponse createContact(CreateContactRequest request);
}
