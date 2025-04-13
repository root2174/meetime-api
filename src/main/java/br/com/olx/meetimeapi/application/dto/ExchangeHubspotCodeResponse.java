package br.com.olx.meetimeapi.application.dto;

import lombok.Builder;

@Builder
public record ExchangeHubspotCodeResponse(
    String refreshToken,
    String accessToken,
    Integer expiresIn
) {}
