package br.com.olx.meetimeapi.application.service;

import br.com.olx.meetimeapi.application.dto.hubspot.webhook.HubspotWebhookRequest;
import br.com.olx.meetimeapi.application.dto.hubspot.webhook.HubspotWebhookResponse;
import br.com.olx.meetimeapi.application.port.in.HubspotWebhookInteractor;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class HubspotWebhookService implements HubspotWebhookInteractor {

  private final String clientSecret;
  private static final String HMAC_SHA256 = "HmacSHA256";

  @Override
  public HubspotWebhookResponse processWebhook(HubspotWebhookRequest request) {
    // Here should be the hubspot webhook processing, since the test didn't really specify what
    // I should do with the request, I'm just going to return that the process was successful if
    // it's the correct event we want to process.

    if (!"contact.creation".equals(request.subscriptionType())) {
      return HubspotWebhookResponse.builder().success(false).eventId(request.eventId()).build();
    }

    return HubspotWebhookResponse.builder().success(true).eventId(request.eventId()).build();
  }

  @Override
  public boolean isValidSignature(String signature, String requestBody) {
    try {
      Mac mac = Mac.getInstance(HMAC_SHA256);
      SecretKeySpec secretKeySpec = new SecretKeySpec(
          clientSecret.getBytes(StandardCharsets.UTF_8),
          HMAC_SHA256
      );
      mac.init(secretKeySpec);

      byte[] rawHmac = mac.doFinal(requestBody.getBytes(StandardCharsets.UTF_8));
      String calculatedSignature = Base64.getEncoder().encodeToString(rawHmac);

      return calculatedSignature.equals(signature);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      log.error("Error validating HubSpot webhook signature", e);
      return false;
    }
  }
}
