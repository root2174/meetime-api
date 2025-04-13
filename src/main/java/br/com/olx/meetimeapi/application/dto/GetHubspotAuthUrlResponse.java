package br.com.olx.meetimeapi.application.dto;

import lombok.Builder;

@Builder
public record GetHubspotAuthUrlResponse(
    String authUrl
) {}
