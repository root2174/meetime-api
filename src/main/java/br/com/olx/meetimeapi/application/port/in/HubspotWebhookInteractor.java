package br.com.olx.meetimeapi.application.port.in;

import br.com.olx.meetimeapi.application.dto.hubspot.webhook.HubspotWebhookRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.webhook.HubspotWebhookResponse;

public interface HubspotWebhookInteractor {
  HubspotWebhookResponse processWebhook(HubspotWebhookRequest request);
  boolean isValidSignature(String signature, String requestBody);
}
