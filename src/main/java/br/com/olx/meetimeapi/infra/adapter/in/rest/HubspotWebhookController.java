package br.com.olx.meetimeapi.infra.adapter.in.rest;

import br.com.olx.meetimeapi.application.dto.hubspot.webhook.HubspotWebhookRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.webhook.HubspotWebhookResponse;
import br.com.olx.meetimeapi.application.port.in.HubspotWebhookInteractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hubspot/webhook")
public class HubspotWebhookController {
  private final HubspotWebhookInteractor hubspotWebhookInteractor;
  private final ObjectMapper objectMapper;


  public ResponseEntity<HubspotWebhookResponse> processWebhook(
      @RequestHeader("X-HubSpot-Signature") String signature,
      HttpServletRequest request) {

    try {
      String requestBody = request.getReader().lines().collect(Collectors.joining());

      if (!hubspotWebhookInteractor.isValidSignature(requestBody, signature)) {
        log.warn("Invalid webhook signature received");
        return ResponseEntity.badRequest().build();
      }

      HubspotWebhookRequest event = objectMapper.readValue(requestBody, HubspotWebhookRequest.class);
      HubspotWebhookResponse response = hubspotWebhookInteractor.processWebhook(event);

      if (response.success()) {
        return ResponseEntity.ok(response);
      }

      return ResponseEntity
          .status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(response);
    } catch (Exception e) {
      log.error("Error processing webhook", e);
    }
    return null;
  }
}
