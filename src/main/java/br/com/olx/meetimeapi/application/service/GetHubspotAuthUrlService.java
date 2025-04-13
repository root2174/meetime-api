package br.com.olx.meetimeapi.application.service;

import br.com.olx.meetimeapi.application.dto.GetHubspotAuthUrlResponse;
import br.com.olx.meetimeapi.application.port.in.GetHubspotAuthUrlInteractor;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetHubspotAuthUrlService implements GetHubspotAuthUrlInteractor {

  private final String hubspotBaseUrl;
  private final String hubspotAuthEndpoint;
  private final String clientId;
  private final String scopes;
  private final String authRedirectUri;

  @Override
  public GetHubspotAuthUrlResponse execute() {
    String authUrl = hubspotBaseUrl + hubspotAuthEndpoint +
        "?client_id=" + encode(clientId) +
        "&scope=" + encode(scopes) +
        "&redirect_uri=" + encode(authRedirectUri);

    return new GetHubspotAuthUrlResponse(authUrl);

  }

  private String encode(String value) {
    return URLEncoder.encode(value, StandardCharsets.UTF_8);
  }
}
