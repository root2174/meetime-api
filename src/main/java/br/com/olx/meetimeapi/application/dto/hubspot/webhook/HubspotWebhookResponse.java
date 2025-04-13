package br.com.olx.meetimeapi.application.dto.hubspot.webhook;

import lombok.Builder;

@Builder
public record HubspotWebhookResponse(String eventId, boolean success) {}
