package br.com.olx.meetimeapi.infra.adapter.in.rest;

import br.com.olx.meetimeapi.application.dto.ExchangeHubspotCodeResponse;
import br.com.olx.meetimeapi.application.dto.GetHubspotAuthUrlResponse;
import br.com.olx.meetimeapi.application.port.in.ExchangeHubspotCodeInteractor;
import br.com.olx.meetimeapi.application.port.in.GetHubspotAuthUrlInteractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hubspot/auth")
public class HubspotAuthController {

  private final GetHubspotAuthUrlInteractor getHubspotAuthUrlInteractor;
  private final ExchangeHubspotCodeInteractor exchangeHubspotCodeInteractor;

  @GetMapping("/url")
  public ResponseEntity<GetHubspotAuthUrlResponse> getHubspotAuthUrl() {
    var uri = getHubspotAuthUrlInteractor.execute();
    return ResponseEntity.ok(uri);
  }

  @GetMapping("/callback")
  public ResponseEntity<ExchangeHubspotCodeResponse> hubspotAuthCallback(@RequestParam String code) {
    log.info("Hubspot auth callback code: {}", code);
    var output = exchangeHubspotCodeInteractor.execute(code);
    log.info("Hubspot code: {} exchanged successfully.", code);
    return ResponseEntity.ok(output);
  }
}
