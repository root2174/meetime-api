package br.com.olx.meetimeapi.application.dto.hubspot.contacts;

import java.util.Map;

public record CreateContactResponse(
    String id,
    String createdAt,
    String updatedAt,
    Map<String, String> properties
) {}
