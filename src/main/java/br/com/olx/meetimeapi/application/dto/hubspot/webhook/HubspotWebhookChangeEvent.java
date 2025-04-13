package br.com.olx.meetimeapi.application.dto.hubspot.webhook;

public record HubspotWebhookChangeEvent(
    String type,
    String changeType,
    Long timestamp
) {}
