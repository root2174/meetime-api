package br.com.olx.meetimeapi.infra.adapter.in.rest;

import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.contacts.CreateContactResponse;
import br.com.olx.meetimeapi.application.port.in.CreateHubspotContactInteractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hubspot/contacts")
public class HubspotContactsController {

  private final CreateHubspotContactInteractor createHubspotContactInteractor;

  @PostMapping
  public ResponseEntity<CreateContactResponse> createContact(@RequestBody CreateContactRequest request) {
    var response = createHubspotContactInteractor.execute(request);
    return ResponseEntity.ok(response);
  }
}
