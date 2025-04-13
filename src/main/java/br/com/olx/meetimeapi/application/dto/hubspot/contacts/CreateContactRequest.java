package br.com.olx.meetimeapi.application.dto.hubspot.contacts;

import java.util.List;
import java.util.Map;

public record CreateContactRequest(
    String authorization,
    List<Association> associations,
    Map<String, String> properties
) {
  public record Association(
      List<AssociationType> types,
      AssociationTo to
  ) {}

  public record AssociationType(
      String associationCategory,
      Integer associationTypeId
  ) {}

  public record AssociationTo(String id) {}
}
