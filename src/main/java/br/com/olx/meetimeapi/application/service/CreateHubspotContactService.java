package br.com.olx.meetimeapi.application.service;

import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactResponse;
import br.com.olx.meetimeapi.application.port.in.CreateHubspotContactInteractor;
import br.com.olx.meetimeapi.application.port.out.rest.HubspotClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateHubspotContactService implements CreateHubspotContactInteractor {

  private final HubspotClient hubspotClient;

  @Override
  public CreateContactResponse execute(CreateContactRequest request) {
    return hubspotClient.createContact(request);
  }
}
