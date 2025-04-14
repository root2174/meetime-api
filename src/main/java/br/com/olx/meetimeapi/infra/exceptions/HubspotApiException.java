package br.com.olx.meetimeapi.infra.exceptions;

public class HubspotApiException extends RuntimeException {
  private final int statusCode;
  private final String errorMessage;

  public HubspotApiException(int statusCode, String errorMessage) {
    super(errorMessage);
    this.statusCode = statusCode;
    this.errorMessage = errorMessage;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}