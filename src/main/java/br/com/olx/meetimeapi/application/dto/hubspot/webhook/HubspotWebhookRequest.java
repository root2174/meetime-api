package br.com.olx.meetimeapi.application.dto.hubspot.webhook;

import java.util.List;

public record HubspotWebhookRequest (
    String eventId,
    String subscriptionType,
    Long portalId,
    Long appId,
    String occurredAt,
    List<HubspotWebhookChangeEvent> changeSource,
    String objectId,
    String propertyName,
    String propertyValue
) {}

