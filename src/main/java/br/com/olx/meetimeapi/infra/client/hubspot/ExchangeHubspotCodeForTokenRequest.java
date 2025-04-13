package br.com.olx.meetimeapi.infra.client.hubspot;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExchangeHubspotCodeForTokenRequest {
  @FormProperty("grant_type")
  private String grantType;

  @FormProperty("client_id")
  private String clientId;

  @FormProperty("client_secret")
  private String clientSecret;

  @FormProperty("redirect_uri")
  private String redirectUri;

  @FormProperty("code")
  private String code;
}
