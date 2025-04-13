package br.com.olx.meetimeapi.application.dto.hubspot.auth;

import lombok.Builder;

@Builder
public record GetHubspotAuthUrlResponse(
    String authUrl
) {}
