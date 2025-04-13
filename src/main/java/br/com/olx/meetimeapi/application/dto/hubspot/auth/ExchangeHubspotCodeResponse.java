package br.com.olx.meetimeapi.application.dto.hubspot.auth;

import lombok.Builder;

@Builder
public record ExchangeHubspotCodeResponse(
    String refreshToken,
    String accessToken,
    Integer expiresIn
) {}
