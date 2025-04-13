package br.com.olx.meetimeapi.infra.client.hubspot;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExchangeHubspotCodeForTokenResponse(
    @JsonProperty("refresh_token")
    String refreshToken,

    @JsonProperty("access_token")
    String accessToken,

    @JsonProperty("expires_in")
    Integer expiresIn
) {}
