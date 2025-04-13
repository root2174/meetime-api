package br.com.olx.meetimeapi.application.port.in;

import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactResponse;

public interface CreateHubspotContactInteractor {
  CreateContactResponse execute(CreateContactRequest request);
}
